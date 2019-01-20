/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
SphereMatrix.java - v1.0

*/

import javax.vecmath.Point3d;
import javax.media.j3d.Group;
import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Material;
import javax.vecmath.Vector3d;

public class SphereMatrix extends Group {
  protected Appearance appearance;
  protected float radius;
  protected int number;

  public SphereMatrix(int sphereNumber) {
    if (sphereNumber < 1) {
      throw new RuntimeException("sphereNumber should be one or more");
    }
    this.number = sphereNumber;
    // initialize appearance
    this.appearance = createAppearance();
    // initialize spheres radius
    this.radius = createRadius();
    // create spheres
    createSpheres();
  }

  protected void createSpheres() {
    for (int y = 0; y < this.number; y++) {
      for (int x = 0; x < this.number; x++) {
        addChild(createSphereTG(x, y));
      }
    }
  }

  protected TransformGroup createSphereTG(int x, int y) {
    TransformGroup tg = new TransformGroup();
    Sphere s = new Sphere(
      this.radius,
      Primitive.GEOMETRY_NOT_SHARED|Primitive.GENERATE_NORMALS,
      this.appearance
    );
    Transform3D t3d = new Transform3D();
    t3d.setTranslation(calculateSphereTranslation(x, y));
    tg.setTransform(t3d);
    tg.addChild(s);
    return tg;
  }

  protected Vector3d calculateSphereTranslation(int x, int y) {
    float vx = (3 * x * this.radius) - ((this.number - 1) * this.radius * 1.5f);
    float vy = (3 * y * this.radius) - ((this.number - 1) * this.radius * 1.5f);
    // return vector
    return new Vector3d(vx, vy, 0);
  }

  protected float createRadius() {
    return (1.0f / ((float) this.number * 2.0f));
  }

  protected Appearance createAppearance() {
    Appearance app = new Appearance();
    Material material = new Material();
    app.setMaterial(material);
    return app;
  }
}
