package kr.shadowrpg.creatures.client;
import kr.shadowrpg.creatures.CreatureEntity;
import net.minecraft.client.render.entity.*;
import net.minecraft.util.Identifier;
public final class CreatureRenderer extends MobEntityRenderer<CreatureEntity,CreatureModel> {
 private final Identifier texture;
 public CreatureRenderer(EntityRendererFactory.Context context,int kind){
  super(context,new CreatureModel(CreatureModel.make(kind).createModel(),kind),kind==2?.6f:.4f);
  texture=Identifier.of("shadow_creatures","textures/entity/"+new String[]{"meadow_wolf","meadow_slime","meadow_golem"}[kind]+".png");
 }
 @Override public Identifier getTexture(CreatureEntity e){return texture;}
}
