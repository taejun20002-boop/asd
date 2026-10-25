package kr.shadowrpg.creatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public final class ShadowCreatures implements ModInitializer {
 public static final String ID="shadow_creatures";
 public static final EntityType<CreatureEntity> MEADOW_WOLF=register("meadow_wolf",0.95f,0.95f);
 public static final EntityType<CreatureEntity> MEADOW_SLIME=register("meadow_slime",0.95f,0.85f);
 public static final EntityType<CreatureEntity> MEADOW_GOLEM=register("meadow_golem",1.0f,1.8f);
 private static EntityType<CreatureEntity> register(String name,float w,float h) {
  return Registry.register(Registries.ENTITY_TYPE,Identifier.of(ID,name),EntityType.Builder.<CreatureEntity>create(CreatureEntity::new,SpawnGroup.MONSTER).dimensions(w,h).maxTrackingRange(8).build(name));
 }
 private static void egg(String name,EntityType<CreatureEntity> type,int a,int b){
  Item egg=Registry.register(Registries.ITEM,Identifier.of(ID,name+"_spawn_egg"),new SpawnEggItem(type,a,b,new Item.Settings()));
  ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries->entries.add(egg));
 }
 @Override public void onInitialize(){
  FabricDefaultAttributeRegistry.register(MEADOW_WOLF,HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,16).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,3).add(EntityAttributes.GENERIC_MOVEMENT_SPEED,.28).add(EntityAttributes.GENERIC_FOLLOW_RANGE,20));
  FabricDefaultAttributeRegistry.register(MEADOW_SLIME,HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,12).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,2).add(EntityAttributes.GENERIC_MOVEMENT_SPEED,.19).add(EntityAttributes.GENERIC_FOLLOW_RANGE,16));
  FabricDefaultAttributeRegistry.register(MEADOW_GOLEM,HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,24).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,4).add(EntityAttributes.GENERIC_MOVEMENT_SPEED,.16).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,.6).add(EntityAttributes.GENERIC_FOLLOW_RANGE,16));
  egg("meadow_wolf",MEADOW_WOLF,0x789478,0xdbcda8);egg("meadow_slime",MEADOW_SLIME,0x639e64,0xb8e28e);egg("meadow_golem",MEADOW_GOLEM,0x7d8672,0x9ab35b);
 }
}
