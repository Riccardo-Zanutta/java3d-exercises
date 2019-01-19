/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
Rotation.java - v1.0

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import com.sun.j3d.utils.geometry.ColorCube;

class Rotation {
  public Rotation() {
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

    // creo un'istanza Transform3D per ogni asse
    Transform3D rotationX = new Transform3D();
    Transform3D rotationY = new Transform3D();
    Transform3D rotationZ = new Transform3D();

    // applico le trasformazioni tramite "rotX", "rotY" e "rotZ"
    rotationX.rotX(Math.PI * 0.1d);
    rotationY.rotY(Math.PI * 0.1d);
    rotationY.mul(rotationX);
    rotationZ.rotZ(Math.PI * 0.1d);
    rotationZ.mul(rotationY);

    // applico la trasformazione al TransformGroup e la aggiungo al branchgroup
    tg.setTransform(rotationZ); 
    bg.addChild(tg);

    return bg;
  }

  public static void main (String[] args) {
    new Rotation();
  }
}