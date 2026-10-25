package kr.shadowrpg.creatures.client;
import kr.shadowrpg.creatures.CreatureEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

/** Independent cube meshes and articulated parts, not billboard sprites. */
public final class CreatureModel extends SinglePartEntityModel<CreatureEntity>{
 private final ModelPart root,body,head,a,b,c,d;private final int kind;
 public CreatureModel(ModelPart root,int kind){this.root=root;this.kind=kind;body=root.getChild("body");head=root.getChild("head");a=root.getChild("a");b=root.getChild("b");c=root.getChild("c");d=root.getChild("d");}
 public ModelPart getPart(){return root;}
 private static ModelPartData box(ModelPartData p,String name,float x,float y,float z,float w,float h,float depth,float px,float py,float pz,int u,int v){
  return p.addChild(name,ModelPartBuilder.create().uv(u,v).cuboid(x,y,z,w,h,depth),ModelTransform.pivot(px,py,pz));
 }
 public static TexturedModelData make(int kind){
  ModelData data=new ModelData();ModelPartData p=data.getRoot();
  if(kind==0){
   box(p,"body",-4,-4,-7,8,8,14,0,13,1,0,0);
   ModelPartData head=box(p,"head",-3,-3,-5,6,6,6,0,10,-7,0,24);
   box(head,"snout",-2,-1,-8,4,3,4,0,0,0,28,24);
   box(head,"ear_l",-3,-7,-2,2,5,2,0,0,0,48,24);box(head,"ear_r",1,-7,-2,2,5,2,0,0,0,48,24);
   box(head,"eye_l",-3.1f,-1.5f,-4.5f,1,1,1,0,0,0,112,0);box(head,"eye_r",2.1f,-1.5f,-4.5f,1,1,1,0,0,0,112,0);
   box(p,"a",-1.5f,0,-1.5f,3,8,3,-2.5f,16,-4,64,0);box(p,"b",-1.5f,0,-1.5f,3,8,3,2.5f,16,-4,64,0);
   box(p,"c",-1.5f,0,-1.5f,3,8,3,-2.5f,16,6,64,0);box(p,"d",-1.5f,0,-1.5f,3,8,3,2.5f,16,6,64,0);
   box(p,"tail",-1.5f,-1,0,3,3,9,0,12,7,80,0);
  }else if(kind==1){
   box(p,"body",-7,-6,-6,14,12,12,0,18,0,0,0);box(p,"head",-5,-2,-6.2f,10,4,1,0,16,0,0,32);
   box(p,"a",-1.5f,-1.5f,-.5f,3,3,1,-3,15,-6.5f,112,0);box(p,"b",-1.5f,-1.5f,-.5f,3,3,1,3,15,-6.5f,112,0);
   box(p,"c",-1,0,-.5f,2,1,1,-1,20,-6.5f,112,0);box(p,"d",-1,0,-.5f,2,1,1,1,20,-6.5f,112,0);
   box(p,"crown",-3,-3,-3,6,4,6,0,10,0,72,24);
  }else{
   box(p,"body",-6,-6,-3.5f,12,13,7,0,10,0,0,0);
   ModelPartData h=box(p,"head",-4,-5,-3.5f,8,7,7,0,3,0,0,28);
   box(h,"brow",-5,-3,-4.5f,10,2,2,0,0,0,64,28);
   box(h,"eye_l",-3,-1.5f,-3.8f,2,1,1,0,0,0,112,0);box(h,"eye_r",1,-1.5f,-3.8f,2,1,1,0,0,0,112,0);
   box(p,"a",-4,-2,-3,6,16,6,-7,6,0,48,0);box(p,"b",-2,-2,-3,6,16,6,7,6,0,48,0);
   box(p,"c",-2.5f,0,-2.5f,5,8,5,-3.5f,16,0,80,0);box(p,"d",-2.5f,0,-2.5f,5,8,5,3.5f,16,0,80,0);
   box(p,"core",-2,-2,-.5f,4,4,1,0,9,-4,112,8);
  }
  return TexturedModelData.of(data,128,64);
 }
 @Override public void setAngles(CreatureEntity entity,float limbAngle,float limbDistance,float age,float headYaw,float headPitch){
  root.traverse().forEach(ModelPart::resetTransform);
  head.yaw=headYaw*MathHelper.RADIANS_PER_DEGREE;head.pitch=headPitch*MathHelper.RADIANS_PER_DEGREE;
  float f=MathHelper.cos(limbAngle*.6662f)*1.2f*limbDistance;
  if(kind==0){a.pitch=f;b.pitch=-f;c.pitch=-f;d.pitch=f;root.getChild("tail").yaw=MathHelper.sin(age*.12f)*.25f;}
  else if(kind==1){float pulse=MathHelper.sin(age*.18f)*.08f;body.yScale=1+pulse;body.xScale=1-pulse;body.zScale=1-pulse;body.pivotY=18-MathHelper.abs(MathHelper.sin(limbAngle))*.7f*limbDistance;}
  else {a.pitch=-f;b.pitch=f;c.pitch=f;d.pitch=-f;}
  if(handSwingProgress>0){float swing=MathHelper.sin(handSwingProgress*MathHelper.PI);if(kind==0)head.pitch-=swing*.6f;else if(kind==1)body.zScale+=swing*.2f;else{a.pitch-=swing*1.8f;b.pitch-=swing*1.8f;}}
 }
}
