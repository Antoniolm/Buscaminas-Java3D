package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Bloque extends BranchGroup {
    
    private int posx,posy;
    private Box box;
    private ColoringAttributes color;
    private Appearance ap;
    private boolean activado;
    private Transform3D transformtrans;
    private TransformGroup translacion;
    private Vector3f posicionReal;
    public Bloque(Vector3f vector,int x,int y){
        posx=x;
        posy=y;
        posicionReal=new Vector3f((Vector3f) vector.clone());
        
        ap=new Appearance();
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK , 0.0f));
        color=new ColoringAttributes();
        ap.setColoringAttributes(color);
        
        box = new Box(0.9f, 0.3f, 0.9f,
            Primitive.ENABLE_APPEARANCE_MODIFY | Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, ap);
        
        //Hacemos pickeable el cubo
        box.setUserData(this);
        box.setPickable(true);
        
        translacion = new TransformGroup();
        translacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformtrans = new Transform3D();
        transformtrans.setTranslation(posicionReal);
        translacion.setTransform(transformtrans); 

        
       translacion.addChild(box);
       this.addChild(translacion);
    }
    public void activarBomba(){
        Texture texture = new TextureLoader ("imgs/bomba.png", null).getTexture();
        ap.setTexture (texture);
    }
    
    public void activarAcierto(){
        Texture texture = new TextureLoader ("imgs/aci1.png", null).getTexture();
        activarCasilla();
        ap.setTexture (texture);
    }
    
    public void activarMarca(){
        Texture texture = new TextureLoader ("imgs/marca.png", null).getTexture();
        activarCasilla();
        ap.setTexture (texture);
    }
    
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
    public void activarCasilla(){
      posicionReal.y+=8.0f;
      transformtrans.setTranslation(posicionReal);
      translacion.setTransform(transformtrans);
    }
    public boolean getActivado(){
        return activado;
    }
}
