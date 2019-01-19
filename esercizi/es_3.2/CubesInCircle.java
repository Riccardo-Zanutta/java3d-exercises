/*

Riccardo Zanutta - 120169 - zanutta.riccardo@spes.uniud.it
CubesInCircle.java - v1.0

*/

import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Group;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.ColorCube;

// Creo una classe che estenda 'Group'
public class CubesInCircle extends Group {
	protected int quantity;
	protected double theta;
	protected double size;
	
	public CubesInCircle(int quantity) {
    // istanzio un nuovo errore nel caso la quantità passata come parametro sia inferiore ad uno
    if (quantity < 1) {
      throw new RuntimeException("Aggiungere una quantità superiore ad 1.");
    }

		this.quantity = quantity;
    this.theta = getTheta();
    this.size = getSize();

    // ciclo la quantità e creo i cubi
    for (int i = 0; i < this.quantity; i++) {
      generateCube(i);
    }
	}

  protected double getTheta() {
    return (2 * Math.PI) / this.quantity;
  }

  protected double getSize() {
    return 1.0d / this.quantity;
  }

  protected void generateCube(int position) {
    TransformGroup tg = new TransformGroup();
    Transform3D translate = new Transform3D();

    // definisco un valore per l'angolo
    double thetaVal = position * this.theta;

		// definisco una traslazione tramite vettore
		translate.setTranslation(new Vector3d(
			Math.cos(thetaVal + Math.PI / 2) / 2,
			Math.sin(thetaVal + Math.PI / 2) / 2,
			0
    ));
  
    // applico la traslazione al gruppo
		tg.setTransform(translate);

    // aggiungo il ColorCube al TransformGroup
    tg.addChild(new ColorCube(this.size));

		addChild(tg);
  }
}
