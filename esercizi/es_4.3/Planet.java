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

class Planet extends Group {
  
  protected float radius;
  protected float rotation;
  protected String texture;
  protected BoundingSphere bound;
  protected Appearance appearance;

  public Planet(float radius, float rotation, String texture, BoundingSphere bound) {
    this.radius = radius;
    this.rotation = rotation;
    this.texture = texture;
    this.bound = bound;
    // create appearance
    this.appearance = createAppearance();
    // create sphere
    addChild(createSphere());
  }

  // This function creates a new sphere used as planet.
  protected TransformGroup createSphere() {
    TransformGroup tg = new TransformGroup();
    Sphere sphere = new Sphere(
      this.radius,
      Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      100,
      this.appearance
    );
    // add sphere to tg
		tg.addChild(sphere);
    // add rotation to tg
    if (this.rotation > 0) {
      addRotation(tg);
    }
    // return tg
    return tg;
  }

  protected void addRotation(TransformGroup tg) {
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
      (float) Math.PI * this.rotation
    );
    // create bounding sphere and set to interpolator
		sphereRotationInterpolator.setSchedulingBounds(this.bound);
    // permit rotation after the creation
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // add sphere as tg child
    tg.addChild(sphereRotationInterpolator);
  }

  // This function creates a new appearance for the planet.
  protected Appearance createAppearance() {
    // initialize the appearance
    Appearance app = new Appearance();
    // create material
    Material material = new Material();
    material.setShininess(80.0f);
	  material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
    app.setMaterial(material);
    // add texture
    if (this.texture != "") {
      // load texture file
      TextureLoader textureLoader = new TextureLoader(this.texture, null);
      // initialize texture object
      Texture texture = textureLoader.getTexture();
      // add texture to the appearance
      app.setTexture(texture);
      // initialize texture attributes
      TextureAttributes textureAttributes = new TextureAttributes();
      textureAttributes.setTextureMode(TextureAttributes.COMBINE);
      app.setTextureAttributes(textureAttributes);
    }
    // return the appearance
    return app;
  }

}