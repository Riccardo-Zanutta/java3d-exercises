/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
Perspectives.java - v1.0

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.View;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import com.sun.j3d.utils.geometry.ColorCube;

class Perspectives {
  public Perspectives() {
    // inizializzo una nuova istanza dell'universo
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // creo un branch group
    BranchGroup branchGroup = createBranchGroup();

    // apply projection
    applyProjection(universe);
    
    // translate user position
    makeLookAt(universe, 1.0f, 1.0f, 3.0f);

    // aggiungo il branchgroup all'universo
    branchGroup.compile();
    universe.addBranchGraph(branchGroup);
  }

  // crea un nuovo branchgroup
  private BranchGroup createBranchGroup() {
    BranchGroup bg = new BranchGroup();
    TransformGroup tg = new TransformGroup();

    tg.addChild(new ColorCube(0.3));
    bg.addChild(tg);

    return bg;
  }

  private void applyProjection(SimpleUniverse universe) {
    // find view of the universe
    View view = universe.getViewer().getView();
		view.setCompatibilityModeEnable(true);
    // create transformation for projection
		Transform3D otg = new Transform3D();
		double ratio = 1024.0/768.0;
    otg.perspective((Math.PI / 4), ratio, 0, 2.4);
    // use set left projection
		view.setLeftProjection(otg);
  }

  private void makeLookAt(SimpleUniverse universe, float x, float y, float z) {
    // ottengo la viewing platform
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    // ottengo il transformGroup per la view
    TransformGroup vptg = viewingPlatform.getViewPlatformTransform();
  
    Transform3D lookAtT3D = new Transform3D();
    lookAtT3D.lookAt(
      new Point3d (x, y, z),
      new Point3d (0.0, 0.0, 0.0),
      new Vector3d(0.0, 1.0, 0.0)
    );
    lookAtT3D.invert();

    vptg.setTransform(lookAtT3D);
  }

  public static void main (String[] args) {
    new Perspectives();
  }
}
