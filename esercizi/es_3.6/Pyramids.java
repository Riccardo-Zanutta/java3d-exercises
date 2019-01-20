/*

Main.java VERSION 1.2

This class is a base main object for projects.

*/

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Background;
import javax.media.j3d.View;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.image.TextureLoader;

// import ColorCube as default tg child
import com.sun.j3d.utils.geometry.ColorCube;

class Pyramids {
  private BoundingSphere defaultBound = new BoundingSphere(new Point3d(), 10.0d);

  public Pyramids() {
    // initialize a new simple universe
    SimpleUniverse universe = new SimpleUniverse();
    universe.getViewingPlatform().setNominalViewingTransform();

    // create branch group
    BranchGroup branchGroup = createBranchGroup();
    
    // translate user position
    makeLookAt(universe, 0.0f, 0.0f, 15.0f);

    // add key movements to branchGroup
    addKeyMovementsToBranchGroup(universe, branchGroup);

    // add directional light to branchGroup
    addDirectionalLight(branchGroup);

    // add a background image to the branchGroup
    addBackground(branchGroup, "../00-resources/forest.jpg");

    // add branchgroup to universe
    branchGroup.compile();
    universe.addBranchGraph(branchGroup);
  }

  // This function creates and return a new branchgroup.
  private BranchGroup createBranchGroup() {
    // initialize bg
    BranchGroup bg = new BranchGroup();
    // create main tg
    TransformGroup tg = new TransformGroup();
    tg.addChild(new MayaPyramid(4.0f));
    // translate pyramid
    Transform3D translate = new Transform3D();
    translate.setTranslation(new Vector3d(0.0f, -0.5f, 0.0f));
    tg.setTransform(translate);
    // add tg to bg
    bg.addChild(tg);
    // return bg
    return bg;
  }

  // User movements:
  // *******************************************************************************************

  // This function translates the user position on the world.
  private void makeLookAt(SimpleUniverse universe, float x, float y, float z) {
    // find viewing platform
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    // find view
    View view = universe.getViewer().getView();
    // find transformgroup for view
    TransformGroup vptg = viewingPlatform.getViewPlatformTransform();
    // create lookat transormation
    Transform3D lookAtT3D = new Transform3D();
    lookAtT3D.lookAt(
      new Point3d (x, y, z),
      new Point3d (0.0, 0.0, 0.0),
      new Vector3d(0.0, 1.0, 0.0)
    );
    lookAtT3D.invert();
    // set lookat transformation to tg
    vptg.setTransform(lookAtT3D);
  }

  // This function adds the possibility to user to move the viewer inside the world.
  private void addKeyMovementsToBranchGroup(SimpleUniverse universe, BranchGroup branchGroup) {
    // find view platform transformgroup
    TransformGroup viewTg = universe.getViewingPlatform().getViewPlatformTransform();
    // create viewTg transformation
    Transform3D viewTransform = new Transform3D();
    // create behaviour to navigate with keys
    KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewTg);
    // create bound for key navigator
    keyNavBeh.setSchedulingBounds(this.defaultBound);

    // add behaviour to branchgroup
    branchGroup.addChild(keyNavBeh);
  }

  // World lights:
  // *******************************************************************************************

  // This function add a directional light to the world with the default direction.
  private void addDirectionalLight(BranchGroup branchGroup) {
    addDirectionalLight(branchGroup, -1.0f, -0.5f, 1.0f);
  }

  // This function add a directional light to the world with a custom direction.
  private void addDirectionalLight(BranchGroup branchGroup, float dirX, float dirY, float dirZ) {
    // create light
    DirectionalLight light = new DirectionalLight();
    light.setDirection(dirX, dirY, dirX);
    // add bounds to light
    light.setInfluencingBounds(this.defaultBound);
    // add light to tg
    branchGroup.addChild(light);
  }

  // World background:
  // *******************************************************************************************

  private void addBackground(BranchGroup branchGroup, String imagepath) {
    TextureLoader loader = new TextureLoader(imagepath, null);
    ImageComponent2D image = loader.getImage();
    Background background = new Background();

    background.setImage(image);
    background.setImageScaleMode(Background.SCALE_FIT_MAX);
    background.setApplicationBounds(this.defaultBound);
    branchGroup.addChild(background);
  }

  public static void main (String[] args) {
    new Pyramids();
  }
}