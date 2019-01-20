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
public class Scalinata extends Group {

  // dimensions
  protected float size;
  protected float baseWidth;
  protected float baseLength;
  protected int numScale;
  // elements
  protected Appearance scalaAppearance;

  public Scalinata(float x, float z, int numScale) {
    this(x, z, numScale, null);
  }

  public Scalinata(float x, float z, int numScale, Appearance appearance) {
    // set initial values
    if (numScale < 1) {
      throw new RuntimeException("numScale should be one or more");
    }
    this.numScale = numScale;
    this.baseWidth = x;
    this.baseLength = z;
    this.size = calculateSize();
    this.scalaAppearance = (appearance == null) ? createAppearance() : appearance;
    // create scale
    for (int i = 0; i < this.numScale; i++) {
      addChild(createScala(i));
    }
  }

  public float getSize() {
    return this.size;
  }

  public float getHeight() {
    return (this.size * this.numScale);
  }

  public float getScaleNumber() {
    return this.numScale;
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

  protected TransformGroup createScala(int numScala) {
    TransformGroup tg = new TransformGroup();
    Box scala = new Box(
      calculateWidth(numScala),
      this.size,
      calculateLength(numScala),
      -Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,
      this.scalaAppearance
    );
    tg.addChild(scala);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(
      0.0f,
      ((numScala) * this.size * 2),
      0.0f
    ));
    tg.setTransform(translate);
    return tg;
  }

  protected float calculateWidth(int numScala) {
    float width = (this.baseWidth + (this.size * (this.numScale - numScala - 1)));
    return width;
  }

  protected float calculateLength(int numScala) {
    float length = (this.baseLength + (this.size * (this.numScale - numScala - 1)));
    return length;
  }

  // This function calculate the correct size for a single scalino.
  protected float calculateSize() {
    float d = (float) Math.sqrt((this.baseLength * this.baseLength) + (this.baseWidth * this.baseWidth));
    return (d / 75);
  }

}