// Import base dependencies.
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;

// Scalinata class.
public class Tetto extends Group {

  // dimensions
  protected float size;
  protected float baseWidth;
  protected float baseLength;
  // elements
  protected Appearance tettoAppearance;

  public Tetto(float x, float z) {
    this(x, z, true, null);
  }

  public Tetto(float x, float z, boolean showCopertura, Appearance appearance) {
    // set initial values
    this.baseWidth = x;
    this.baseLength = z;
    this.size = calculateSize(showCopertura);
    this.tettoAppearance = (appearance == null) ? createAppearance() : appearance;
    // create componets
    addChild(createBase());
    addChild(createSopraBase(showCopertura));
    if (showCopertura) {
      addChild(createCopertura());
    }
  }

  public float getSize() {
    return this.size;
  }

  public float getHeight() {
    return (this.size * 1.5f);
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setAmbientColor(239/255f,224/255f,203/255f);
    material.setDiffuseColor(239/255f,224/255f,203/255f);
    appearance.setMaterial(material);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

  // This function calculate the correct size for a single scalino.
  protected float calculateSize(boolean showCopertura) {
    float d = (float) Math.sqrt((this.baseLength * this.baseLength) + (this.baseWidth * this.baseWidth));
    return (d / (showCopertura ? 30 : 60));
  }

  protected TransformGroup createBase() {
    TransformGroup tg = new TransformGroup();
    Box base = new Box(
      (this.baseWidth),
      this.size,
      (this.baseLength),
      -Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tettoAppearance
    );
    tg.addChild(base);
    return tg;
  }

  protected TransformGroup createSopraBase(boolean showCopertura) {
    TransformGroup tg = new TransformGroup();
    Box base = new Box(
      (this.baseWidth - (this.size / 2)),
      (this.size / 2),
      (this.baseLength - (showCopertura ? (this.size / 2) : -(this.size / 2))),
      -Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.tettoAppearance
    );
    tg.addChild(base);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      this.size + (this.size / 2),
      0.0f
    ));
    tg.setTransform(translate);
    return tg;
  }

  protected TransformGroup createCopertura() {
    TransformGroup tg = new TransformGroup();
    MyCopertura copertura = new MyCopertura(
      this.baseWidth,
      (this.size * 2),
      this.baseLength,
      this.tettoAppearance
    );
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      (this.size * 1.5) + (this.size / 2),
      0.0f
    ));
    tg.setTransform(translate);
    tg.addChild(copertura);
    return tg;
  }

}