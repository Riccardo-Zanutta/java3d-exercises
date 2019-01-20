// Import base dependencies.
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TexCoordGeneration;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

// Tempio class.
public class Tempio extends Group {

  protected float scalinataHeight;
  protected float tettoHeight;
  protected Appearance tempioAppearance;

  public Tempio() {
    // create shared appearance
    this.tempioAppearance = createAppearance();
    // create scalinata
    addChild(createScalinata());
    // create colonne
    addColonneChild();
    // create tetto
    addTettoChild();
    // create colonne internal
    addColonneInternalChild();
  }

  protected Appearance createAppearance() {
    Appearance appearance = new Appearance();
    // add material
    Material material = new Material();
    material.setAmbientColor(239/255f,224/255f,203/255f);
    material.setDiffuseColor(239/255f,224/255f,203/255f);
    appearance.setMaterial(material);
    // load texture file
    TextureLoader textureLoader = new TextureLoader("../00-resources/pietra.jpg", null);
    // initialize texture object
    Texture texture = textureLoader.getTexture();
    texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
    // add texture to the appearance
    appearance.setTexture(texture);
    // initialize texture attributes
    TextureAttributes textureAttributes = new TextureAttributes();
	  textureAttributes.setTextureMode(TextureAttributes.COMBINE);
    textureAttributes.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
    appearance.setTextureAttributes(textureAttributes);
    // initialize and add text coordinates generator
    TexCoordGeneration tcg = new TexCoordGeneration(
      TexCoordGeneration.OBJECT_LINEAR,
      TexCoordGeneration.TEXTURE_COORDINATE_3
    );
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

  // Colonne:
  // ********************************************************

  protected void addColonneChild() {
    // front
    for (int i = 0; i < 6; i++) {
      addChild(createColonna(i, "front"));
    }
    // back
    for (int i = 0; i < 6; i++) {
      addChild(createColonna(i, "back"));
    }
    // left
    for (int i = 0; i < 12; i++) {
      addChild(createColonna(i, "left"));
    }
    // right
    for (int i = 0; i < 12; i++) {
      addChild(createColonna(i, "right"));
    }
  }

  protected TransformGroup createColonna(int numColonna, String type) {
    TransformGroup tg = new TransformGroup();
    Colonna colonna = new Colonna(2.0f, this.tempioAppearance);
    tg.addChild(colonna);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    if (type == "front" || type == "back") {
      translate.setTranslation(new Vector3d(
        ((numColonna > 2) ? -(2.5 - numColonna) : (numColonna - 2.5)),
        (this.scalinataHeight + 1),
        (type == "front" ? 6.5f : -6.5f)
      ));
    } else {
      translate.setTranslation(new Vector3d(
        (type == "left" ? 2.5f : -2.5f),
        (this.scalinataHeight + 1),
        ((numColonna > 5) ? -(5.5 - numColonna) : (numColonna - 5.5))
      ));
    }
    tg.setTransform(translate);
    return tg;
  }

  // Colonne internal:
  // ********************************************************

  protected void addColonneInternalChild() {
    // front
    for (int i = 0; i < 4; i++) {
      addChild(createColonnaInternal(i, "front"));
    }
    // back
    for (int i = 0; i < 4; i++) {
      addChild(createColonnaInternal(i, "back"));
    }
    // left
    for (int i = 0; i < 8; i++) {
      addChild(createColonnaInternal(i, "left"));
    }
    // right
    for (int i = 0; i < 8; i++) {
      addChild(createColonnaInternal(i, "right"));
    }
  }

  protected TransformGroup createColonnaInternal(int numColonna, String type) {
    TransformGroup tg = new TransformGroup();
    float height = ((type == "front" || type == "back") ? 2.0f : 1.7f);
    Colonna colonna = new Colonna(height, this.tempioAppearance);
    tg.addChild(colonna);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    if (type == "front" || type == "back") {
      translate.setTranslation(new Vector3d(
        ((numColonna > 2) ? -(1.5 - numColonna) : (numColonna - 1.5)),
        (this.scalinataHeight + 1),
        (type == "front" ? 4f : -4f)
      ));
    } else {
      translate.setTranslation(new Vector3d(
        (type == "left" ? 1.5f : -1.5f),
        (this.scalinataHeight + 0.85f),
        ((numColonna > 3) ? -(1.75 - (numColonna / 1.95)) : ((numColonna / 1.95) - 1.75))
      ));
    }
    tg.setTransform(translate);
    return tg;
  }

  // Tetto:
  // ********************************************************

  protected void addTettoChild() {
    // front
    addChild(createTetto("front"));
    // back
    addChild(createTetto("back"));
    // left
    addChild(createTetto("left"));
    // right
    addChild(createTetto("right"));
  }

  protected TransformGroup createTetto(String type) {
    boolean showCopertura = (type == "front" || type == "back");
    float width = (showCopertura ? 3.0f : 0.5f);
    float length = (showCopertura ? 0.5f : 6.0f);
    TransformGroup tg = new TransformGroup();
    Tetto tetto = new Tetto(width, length, showCopertura, this.tempioAppearance);
    this.tettoHeight = tetto.getHeight();
    tg.addChild(tetto);
    // add correct transformation to tg
    Transform3D translate = new Transform3D();
    if (type == "front" || type == "back") {
      translate.setTranslation(new Vector3d(
        0.0f,
        (this.scalinataHeight + this.tettoHeight + 2),
        (type == "front" ? 6.5f : -6.5f)
      ));
    } else {
      translate.setTranslation(new Vector3d(
        (type == "left" ? 2.5f : -2.5f),
        (this.scalinataHeight + this.tettoHeight + 2),
        0.0f
      ));
    }
    tg.setTransform(translate);
    return tg;
  }

  // Scalinata:
  // ********************************************************

  protected TransformGroup createScalinata() {
    TransformGroup tg = new TransformGroup();
    Scalinata scalinata = new Scalinata(3.0f, 7.0f, 3, this.tempioAppearance);
    this.scalinataHeight = scalinata.getHeight();
    tg.addChild(scalinata);
    return tg;
  }

}