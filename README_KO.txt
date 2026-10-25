Shadow RPG Creatures 0.1 — 개발 소스 / 미빌드

대상: Minecraft 1.21.1, Fabric Loader 0.16.5, Fabric API 0.102.1+1.21.1, Java 21.
이 ZIP을 mods에 넣어도 동작하지 않습니다. 컴파일된 설치 JAR가 아닙니다.
Gradle 8.8 + JDK21 환경에서 gradle build 필요. Gradle wrapper는 포함하지 않습니다.
정상 빌드 시 build/libs의 일반 JAR 사용 (sources JAR 제외).

별도 EntityType 3개와 공통 HostileEntity 동작 기반을 구현했습니다.
좀비를 상속하지 않으며 이미지 패널/승객 엔티티는 사용하지 않습니다.
독립 입체 큐브 모델, 늑대 4족 보행, 슬라임 도약과 몸체 수축, 골렘 팔다리 동작.
근접 공격 애니메이션, 추적, 피격, 바닐라 소리 재사용.
HP/피해: 늑대16/3, 슬라임12/2, 골렘24/4. Lv1 표시.
수치는 Minecraft 체력 단위이며 봇 능력치의 그대로 이식은 아닙니다.
생성 알 또는 아래 명령으로 생성. 자연 스폰/월드 연결은 아직 없음.
/summon shadow_creatures:meadow_wolf ~ ~ ~
/summon shadow_creatures:meadow_slime ~ ~ ~
/summon shadow_creatures:meadow_golem ~ ~ ~

원본 그림의 정확한 3D 복원이 아닌 개발용 블록 모델/텍스처입니다.
종족/직업 선택창, RPG EXP, 사냥터/던전 연동은 포함하지 않습니다.
컴파일과 Minecraft 실행 미검증. Yarn API 차이로 빌드 수정이 필요할 수 있습니다.
기존 0.4 mrpack과 자동 통합되지 않습니다.
