package kr.shadowrpg.creatures.client;
import kr.shadowrpg.creatures.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
public final class CreatureClient implements ClientModInitializer {
 public void onInitializeClient(){
  EntityRendererRegistry.register(ShadowCreatures.MEADOW_WOLF,c->new CreatureRenderer(c,0));
  EntityRendererRegistry.register(ShadowCreatures.MEADOW_SLIME,c->new CreatureRenderer(c,1));
  EntityRendererRegistry.register(ShadowCreatures.MEADOW_GOLEM,c->new CreatureRenderer(c,2));
 }
}
