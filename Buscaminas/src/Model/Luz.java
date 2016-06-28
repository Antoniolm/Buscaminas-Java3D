package Model;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Luz extends BranchGroup  {

    public Luz() {
            Light aLight;

            // Se crea el nodo de la luz ambiental
            aLight = new AmbientLight (new Color3f (0.2f, 0.2f, 0.2f));
            aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
            aLight.setEnable(true);
            this.addChild(aLight);
    }
    
}
