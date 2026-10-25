"""Run after a successful Gradle build. Creates an importable development pack."""
from pathlib import Path
import json, zipfile, urllib.request, urllib.parse, hashlib

def create_pack(jar, api, destination):
    with zipfile.ZipFile(jar) as archive:
        assert archive.testzip() is None
        assert 'fabric.mod.json' in archive.namelist()
        assert 'kr/shadowrpg/creatures/ShadowCreatures.class' in archive.namelist()
    files=[{'path':'mods/'+api['filename'],'hashes':api['hashes'],'env':{'client':'required','server':'required'},'downloads':[api['url']],'fileSize':api['size']}]
    index={'formatVersion':1,'game':'minecraft','versionId':'0.1.0-development','name':'Shadow Creatures Development','summary':'Three custom modeled level-1 monsters. Runtime unverified. Creative spawn eggs; no RPG world integration.','files':files,'dependencies':{'minecraft':'1.21.1','fabric-loader':'0.16.5'}}
    with zipfile.ZipFile(destination,'w',zipfile.ZIP_DEFLATED) as z:
        z.writestr('modrinth.index.json',json.dumps(index))
        z.write(jar,'overrides/mods/'+jar.name)
        z.writestr('overrides/READ_ME_KO.txt','Minecraft 1.21.1 개발 테스트팩. 새 크리에이티브 월드를 열고 생성 알을 검색하거나 /summon shadow_creatures:meadow_wolf, meadow_slime, meadow_golem을 사용하세요. 자연 스폰·직업 선택·이전 RPG 맵 연결은 없습니다. 실제 게임 실행은 검증 전입니다.')
    with zipfile.ZipFile(destination) as z:
        assert z.testzip() is None
    return index

if __name__=='__main__':
    jars=[p for p in Path('build/libs').glob('*.jar') if not p.name.endswith('-sources.jar') and not p.name.endswith('-dev.jar')]
    assert len(jars)==1, jars
    query=urllib.parse.urlencode({'game_versions':json.dumps(['1.21.1']),'loaders':json.dumps(['fabric'])})
    request=urllib.request.Request('https://api.modrinth.com/v2/project/fabric-api/version?'+query,headers={'User-Agent':'ShadowCreatures-development-pack/0.1'})
    with urllib.request.urlopen(request,timeout=60) as r: versions=json.load(r)
    version=next(v for v in versions if v['version_number']=='0.102.1+1.21.1')
    api=next(f for f in version['files'] if f['primary'])
    output=Path('build/ShadowRPG-Creatures.mrpack')
    create_pack(jars[0],api,output)
    print('Created',output,'after successful compilation. Minecraft runtime not tested.')
