/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
Translation.java - v1.0

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.ColorCube;

class Translation {
  public Translation() {
    // inizializzo una nuova istanza dell'universo
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // creo un branch group
    BranchGroup branchGroup = createBranchGroup();

    // aggiungo il branchgroup all'universo
    branchGroup.compile();
    universe.addBranchGraph(branchGroup);
  }

  // crea un nuovo branchgroup
  private BranchGroup createBranchGroup() {
    BranchGroup bg = new BranchGroup();

    TransformGroup tg = new TransformGroup();
    tg.addChild(new ColorCube(0.3f));

    // creo una nuova istanza di Transform3D
    Transform3D translate = new Transform3D();
    // applico la traslazione al cubo
    translate.setTranslation(new Vector3d(0.1f, 0.1f, 0.1f));

    // applico la trasformazione al TransformGroup e la aggiungo al branchgroup
    tg.setTransform(translate); 
    bg.addChild(tg);

    return bg;
  }

  public static void main (String[] args) {
    new Translation();
  }
}
