package kr.shadowrpg.creatures;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/** Native custom entity: no zombie base, picture overlay, or passenger entity. */
public class CreatureEntity extends HostileEntity {
 public CreatureEntity(EntityType<? extends HostileEntity> type,World world){
  super(type,world);
  setCustomName(Text.translatable(type.getTranslationKey()).append(" Lv.1"));
  setCustomNameVisible(true);
}
 @Override protected void initGoals(){
  goalSelector.add(0,new SwimGoal(this));
  goalSelector.add(2,new MeleeAttackGoal(this,1.05,false));
  goalSelector.add(5,new WanderAroundFarGoal(this,.7));
  goalSelector.add(6,new LookAtEntityGoal(this,PlayerEntity.class,8));
  goalSelector.add(7,new LookAroundGoal(this));
  targetSelector.add(1,new RevengeGoal(this));
  targetSelector.add(2,new ActiveTargetGoal<>(this,PlayerEntity.class,true));
 }
  @Override public void tick(){
  super.tick();
  if(!getWorld().isClient && getType()==ShadowCreatures.MEADOW_SLIME && isOnGround() && getTarget()!=null && age%18==0){jump();}
 }
 @Override protected SoundEvent getHurtSound(net.minecraft.entity.damage.DamageSource source){
  return getType()==ShadowCreatures.MEADOW_SLIME?SoundEvents.ENTITY_SLIME_HURT:getType()==ShadowCreatures.MEADOW_GOLEM?SoundEvents.ENTITY_IRON_GOLEM_HURT:SoundEvents.ENTITY_WOLF_HURT;
 }
 @Override protected SoundEvent getDeathSound(){return getType()==ShadowCreatures.MEADOW_SLIME?SoundEvents.ENTITY_SLIME_DEATH:getType()==ShadowCreatures.MEADOW_GOLEM?SoundEvents.ENTITY_IRON_GOLEM_DEATH:SoundEvents.ENTITY_WOLF_DEATH;}
}
