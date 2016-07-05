/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 * Clase para la creación del boton superior que nos permitira reiniciar el juego
 */

public class Boton extends BranchGroup{
    private Appearance ap;
    public Boton(Vector3f vector,String textura){
       
        //Creamos la apariencia
        ap=new Appearance();
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK , 0.0f));
        Texture texture = new TextureLoader (textura, null).getTexture();
        ap.setTexture (texture);
        
        Box box = new Box(0.9f, 0.3f, 0.9f,
            Primitive.ENABLE_APPEARANCE_MODIFY | Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, ap);
        
        //Hacemos pickeable el cubo
        box.setUserData(this);
        box.setPickable(true);
        
        //Creamos la transformacion
        TransformGroup translacion = new TransformGroup();
        Transform3D transformtrans = new Transform3D();
        transformtrans.setTranslation(vector);
        translacion.setTransform(transformtrans); 

        //Enlazamos todo
       translacion.addChild(box);
       this.addChild(translacion);    
    }
    
    /**
     * 
     * Cambia la textura del boton
     */
    public void setTextura(String textura){
        Texture texture = new TextureLoader (textura, null).getTexture();
        ap.setTexture (texture);
    }
    
}
