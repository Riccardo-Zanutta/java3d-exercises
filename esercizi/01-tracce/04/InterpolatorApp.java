package edu.uniud.labimm.esercizi.interpolazione;

import java.applet.Applet;
import java.awt.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;

import edu.uniud.labimm.esercizi.textures.TerraAvanzato;

import javax.swing.*;

//Renderizza un gruppo di automobili che subiscono dei cambiamenti in base
//all'interpolatore a cui sono collegate
public class InterpolatorApp extends Applet
{
  public InterpolatorApp()
  {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);

    //Crea la scena
    BranchGroup scene=createSceneGraph();

    //Crea il SimpleUniverse
    SimpleUniverse simpleU=new SimpleUniverse(canvas3D);

    //Questo comando serve per spostare leggermente indietro il sistema di riferimento
    simpleU.getViewingPlatform().setNominalViewingTransform();

    //Compila la scena (subito prima di renderla viva)
    scene.compile();

    //Aggiunge la scena all'universo
    simpleU.addBranchGraph(scene);
  }

  //Crea la scena
  private BranchGroup createSceneGraph()
  {
    BoundingSphere bounds=new BoundingSphere();

    //Crea la radice del branch graph
    BranchGroup objRoot=new BranchGroup();

    //Crea uno sfondo
    Background background=new Background();
    background.setColor(1,1,1);
    background.setApplicationBounds(bounds);
    objRoot.addChild(background);

    //Crea un timer
    Alpha alpha=new Alpha(-1,Alpha.INCREASING_ENABLE+Alpha.DECREASING_ENABLE,0,0,2000,0,1000,2000,0,1000);

    //Assembla la scena
   // objRoot.addChild(this.createMovingCar(new Vector3f(0.0f,0.3f,0.0f),alpha,bounds));
   // objRoot.addChild(this.createRotatingCar(new Vector3f(0.0f,0.3f,0.0f),alpha,bounds));
   // objRoot.addChild(this.createScalingCar(new Vector3f(0.0f,0.3f,0.0f),alpha,bounds));
    //objRoot.addChild(this.createColoringCar(new Vector3f(0.0f,0.3f,0.0f),alpha,bounds));
   objRoot.addChild(this.createSwitchingCar(new Vector3f(0.0f,0.3f,0.0f),alpha,bounds));

    //Crea una luce direzionale (maggiori dettagli nella lezione sulle luci)
    DirectionalLight lightD1=new DirectionalLight();
    lightD1.setInfluencingBounds(bounds);
    lightD1.setDirection(new Vector3f(-1.0f,-1.0f,-1.0f));
    lightD1.setColor(new Color3f(1.0f,1.0f,1.0f));
    objRoot.addChild(lightD1);

    return objRoot;
  }

  private TransformGroup createMovingCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un gruppo per lo spostamento orizzontale dell'automobile
    TransformGroup objMove=new TransformGroup();
    objMove.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    
    //COMPLETA IL CODICE
    //Crea un interpolatore per le posizioni collegato con il gruppo di trasformazione
    PositionInterpolator position= null;

    //Imposta un raggio d'azione all'interpolatore
    

    //Imposta la posizione iniziale
    

    //Assembla la scena
    objPos.addChild(objMove);
    objMove.addChild(this.createCar(true));
    objPos.addChild(position);

    return objPos;
  }

  private TransformGroup createRotatingCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un gruppo per la rotazione dell'automobile
    TransformGroup objRotate=new TransformGroup();
    objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    //Crea un interpolatore per le rotazioni collegato con il gruppo di trasformazione
    RotationInterpolator rotation=  null;//SOSTITUIRE QUESTO CODICE

    //Assembla la scena
    objPos.addChild(objRotate);
    objRotate.addChild(this.createCar(true));
    objPos.addChild(rotation);

    return objPos;
  }

  private TransformGroup createScalingCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un gruppo per il ridimensionamento dell'automobile
    TransformGroup objScale=new TransformGroup();
    objScale.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    
    //SCRIVI IL CODICE
    //Crea un interpolatore per i ridimensionamenti collegato con il gruppo di trasformazione
    ScaleInterpolator scaling=null;

    //Imposta un raggio d'azione all'interpolatore


    //Assembla la scena
    objPos.addChild(objScale);
    objScale.addChild(this.createCar(true));
    objPos.addChild(scaling);

    return objPos;
  }

  private TransformGroup createColoringCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un oggetto Material per la gestione del colore
    Material objColor=new Material();

    //Decide quale "tipo" di colore deve essere modificato
    objColor.setColorTarget(Material.DIFFUSE);
    objColor.setCapability(Material.ALLOW_COMPONENT_WRITE);
    objColor.setCapability(Material.ALLOW_COMPONENT_READ);

    //Crea un interpolatore per le colorazioni collegato con il gruppo di trasformazione
   
    //INSERIRE QUI IL CODICE
    //Crea un interpolatore per le colorazioni collegato con il gruppo di trasformazione
  //Crea un interpolatore per le colorazioni collegato con il gruppo di trasformazione
    ColorInterpolator coloring=new ColorInterpolator(alpha,objColor);

    //Imposta i colori iniziale e finale dell'interpolatore
    coloring.setStartColor(new Color3f(1,0,0));
    coloring.setEndColor(new Color3f(0,0,1));

    //Imposta un raggio d'azione all'interpolatore
    coloring.setSchedulingBounds(bounds);


    //Imposta i colori iniziale e finale dell'interpolatore


    //Imposta un raggio d'azione all'interpolatore


    //Crea l'automobile
    Shape3D car=this.createCar(false);

    //Imposta l'aspetto dell'automobile per farne modificare il colore
    Appearance materialAppear=new Appearance();
    materialAppear.setMaterial(objColor);
    car.setAppearance(materialAppear);

    //Assembla la scena
    objPos.addChild(car);
    objPos.addChild(coloring);

    return objPos;
  }

  private TransformGroup createSwitchingCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un oggetto Switch
    Switch objSwitch=new Switch();
    objSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

    //Crea un interpolatore per le trasparenze collegato con il gruppo di trasformazione
    SwitchValueInterpolator switching=new SwitchValueInterpolator(alpha,objSwitch);
    switching.setLastChildIndex(3);

    //Imposta un raggio d'azione all'interpolatore
    switching.setSchedulingBounds(bounds);

    //Assembla la scena
    Shape3D car=this.createCar(true);
    Appearance app=car.getAppearance();
    objSwitch.addChild(car);
    objSwitch.addChild(new ColorCube(0.1f));
    objSwitch.addChild(new Cone(0.1f,0.2f,app));
    objSwitch.addChild(new Cylinder(0.2f,0.15f,app));
    objPos.addChild(objSwitch);
    objPos.addChild(switching);

    return objPos;
  }
  
  private TransformGroup createBeingTransparentCar(Vector3f v, Alpha alpha, Bounds bounds)
  {
    //Crea un gruppo per traslare in verticale l'automobile
    Transform3D t3d=new Transform3D();
    t3d.setTranslation(v);
    TransformGroup objPos=new TransformGroup(t3d);

    //Crea un oggetto TransparencyAttributes per la gestione delle trasparenze
    TransparencyAttributes objTransp=new TransparencyAttributes();
    objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
    objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);

    //Crea un interpolatore per le trasparenze collegato con il gruppo di trasformazione
    TransparencyInterpolator trasparencing=new TransparencyInterpolator(alpha,objTransp);

    //Imposta un raggio d'azione all'interpolatore
    trasparencing.setSchedulingBounds(bounds);

    //Crea l'automobile
    Shape3D car=this.createCar(true);

    //Imposta l'aspetto dell'automobile per farne modificare la trasparenza
    Appearance transparencyAppear=car.getAppearance();
    transparencyAppear.setTransparencyAttributes(objTransp);

    //Assembla la scena
    objPos.addChild(car);
    objPos.addChild(trasparencing);

    return objPos;
  }
  
  //Crea l'automobile
  private Shape3D createCar(boolean assignColoring)
  {
    Shape3D car=new Shape3D();
    double scale=0.3;
    GeometryInfo gi=new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
    Point3d[] coord=new Point3d[16];
    coord[0]=new Point3d(scale*-0.3,scale*0.2,0);
    coord[1]=new Point3d(scale*-0.5,scale*0,0);
    coord[2]=new Point3d(scale*-0.7,scale*0,0);
    coord[3]=new Point3d(scale*-0.7,scale*-0.2,0);
    coord[4]=new Point3d(scale*-0.5,scale*-0.2,0);
    coord[5]=new Point3d(scale*-0.5,scale*-0.35,0);
    coord[6]=new Point3d(scale*-0.3,scale*-0.35,0);
    coord[7]=new Point3d(scale*-0.3,scale*-0.2,0);
    coord[8]=new Point3d(scale*0.2,scale*-0.2,0);
    coord[9]=new Point3d(scale*0.2,scale*-0.35,0);
    coord[10]=new Point3d(scale*0.4,scale*-0.35,0);
    coord[11]=new Point3d(scale*0.4,scale*-0.2,0);
    coord[12]=new Point3d(scale*0.7,scale*-0.2,0);
    coord[13]=new Point3d(scale*0.6,scale*0,0);
    coord[14]=new Point3d(scale*0.4,scale*0,0);
    coord[15]=new Point3d(scale*0.1,scale*0.2,0);
    gi.setCoordinates(coord);
    gi.setStripCounts(new int[] {16});
    new NormalGenerator().generateNormals(gi);
    new Stripifier().stripify(gi);
    car.setGeometry(gi.getGeometryArray());

    Appearance carAppear=new Appearance();

    if (assignColoring)
    {
      ColoringAttributes colorAttrib=new ColoringAttributes(0,0,1,ColoringAttributes.NICEST);
      carAppear.setColoringAttributes(colorAttrib);
    }

    PolygonAttributes polyAttrib=new PolygonAttributes();
    polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
    carAppear.setPolygonAttributes(polyAttrib);

    car.setAppearance(carAppear);

    return car;
  }
  
  

  public static void main(String[] args)
  {
	  new MainFrame(new InterpolatorApp(), 1024, 768);
  }
}
