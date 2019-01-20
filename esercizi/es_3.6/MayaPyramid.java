/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
MayaPyramid.java - v1.0

*/

import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.PolygonAttributes;

import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Vector3d;

public class MayaPyramid extends Group {

  protected float unit;
  protected Appearance appearance;

  public MayaPyramid(float size) {
    this.unit = (size / 15);
    this.appearance = createAppearance();
  
    createLevels();
  }

  protected void createLevels() {
    for (int i = 0; i < 10; i++) {
      addChild(createLevel(i));
    }
  }

  protected TransformGroup createLevel(int counter) {
    TransformGroup tg = new TransformGroup();
    // create box (change size for the last box)
    float bottomWidth = (counter == 9) ? ((15 - counter - 2) * this.unit) : ((15 - counter) * this.unit);
    float topWidth = (counter == 9) ? bottomWidth : (bottomWidth - (this.unit / 2));
    float height = (counter == 9) ? (this.unit * 2) : this.unit;
    MyBox box = new MyBox(topWidth, topWidth, bottomWidth, bottomWidth, height, this.appearance);
    tg.addChild(box);
    // translate box
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, (counter * this.unit), 0.0f));
    tg.setTransform(translate);
    // return tg
    return tg;
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    appearance.setMaterial(material);
    // load texture file
    TextureLoader textureLoader = new TextureLoader("../00-resources/pietra.jpg", null);
    // initialize texture object
    Texture texture = textureLoader.getTexture();
    // add texture to the appearance
    appearance.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    appearance.setTextureAttributes(textureAttributes);
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_3);
	  appearance.setTexCoordGeneration(tcg);
    // add style
    appearance.setPolygonAttributes(new PolygonAttributes(
      PolygonAttributes.POLYGON_FILL,
      PolygonAttributes.CULL_NONE,
      0
    ));
    // return the appearance
    return appearance;
  }

}