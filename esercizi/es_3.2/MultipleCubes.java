/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
MultipleCubes.java - v1.0

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import com.sun.j3d.utils.geometry.ColorCube;

class MultipleCubes {
  public MultipleCubes() {
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

    // Aggiungo una nuova istanza di 'CubesInCircle'
    // il quale crea tanti cubi quanti sono quelli passati come parametro.
    tg.addChild(new CubesInCircle(20));
    bg.addChild(tg);

    return bg;
  }

  public static void main (String[] args) {
    new MultipleCubes();
  }
}