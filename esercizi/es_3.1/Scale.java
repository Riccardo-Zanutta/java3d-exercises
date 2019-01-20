/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
Scale.java - v1.0

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.ColorCube;

class Scale {
  public Scale() {
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
    Transform3D scale = new Transform3D();
    
    // imposto il fattore di scale
    scale.setScale(1.5d);

    // applico la trasformazione al TransformGroup e la aggiungo al branchgroup
    tg.setTransform(scale); 
    bg.addChild(tg);

    return bg;
  }

  public static void main (String[] args) {
    new Scale();
  }
}
