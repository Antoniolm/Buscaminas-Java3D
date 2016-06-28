package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Camara extends BranchGroup{
    private View vista;
    private Canvas3D canvas;
    
    public Camara(Canvas3D canva,float distance,float planodelantero,float planotrasero,float screenScl,float angulo,Point3d posicion,Point3d direccion,Vector3d vup){    
      canvas=canva;
      ViewPlatform viewplat=new ViewPlatform();
      viewplat.setActivationRadius(40.0f);
      
      PhysicalBody pbody=new PhysicalBody();
      PhysicalEnvironment penvi=new PhysicalEnvironment();
      
      vista=new View();
     
      vista.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
      vista.setFieldOfView(Math.toRadians(angulo));
      
      vista.setFrontClipDistance(planodelantero);
      vista.setBackClipDistance(planotrasero);
      vista.addCanvas3D(canvas);
      vista.setPhysicalBody(pbody);
      vista.setPhysicalEnvironment(penvi);
      vista.attachViewPlatform(viewplat);
      
      Transform3D posicionVista= new Transform3D ( ) ;
      posicionVista.lookAt( posicion , direccion , vup ) ;
      posicionVista.invert();
      
      TransformGroup transformgr = new TransformGroup(posicionVista);
     
      transformgr.addChild(viewplat);
      addChild(transformgr);    
    }
}
