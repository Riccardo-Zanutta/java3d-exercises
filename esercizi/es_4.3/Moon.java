/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
Moon.java - v1.0

*/

import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.Material;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

class Moon extends Group {
  
  protected float radius;
  protected float translation;
  protected float selfRotation;
  protected float orbitRotation;
  protected BoundingSphere bound;

  public Moon(float radius, float translation, float selfRotation, float orbitRotation, BoundingSphere bound) {
    this.radius = radius;
    this.translation = translation;
    this.selfRotation = selfRotation;
    this.orbitRotation = orbitRotation;
    this.bound = bound;
    // create moon rotation
    addChild(createMoonRotation());
  }

  protected TransformGroup createMoonRotation() {
    TransformGroup tg;
    if (this.orbitRotation > 0) {
      tg = new TransformGroup();
      // create transformation for the sphere rotation
      Transform3D sphereRotation = new Transform3D();
      sphereRotation.rotY(- Math.PI / 4.0f);
      // create alpha
      Alpha alpha = new Alpha(-1, 50000);
      // create rotation interpolator
      RotationInterpolator sphereRotationInterpolator = new RotationInterpolator(
        alpha,
        tg,
        sphereRotation,
        0.0f,
        (float) Math.PI * this.orbitRotation
      );
      // create bounding sphere and set to interpolator
      sphereRotationInterpolator.setSchedulingBounds(this.bound);
      // permit rotation after the creation
      tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      // add sphere as tg child
      tg.addChild(sphereRotationInterpolator);
      tg.addChild(createMoon());
    } else {
      tg = createMoon();
    }
    return tg;
  }

  protected TransformGroup createMoon() {
    TransformGroup tg = new TransformGroup();
    tg.addChild(new Planet(this.radius, this.selfRotation, "../00-resources/moon.jpg", this.bound));
    if (this.translation > 0) {
      Transform3D translation = new Transform3D();
      translation.setTranslation(new Vector3d(this.translation, 0.0, 0.0));
      tg.setTransform(translation);
    }
    return tg;
  }

}