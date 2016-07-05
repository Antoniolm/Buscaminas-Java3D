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
    
    private int posx,posy;                  //posición de elemento en el tablero de juego
    private Box box;                        //figura geométrica del objeto
    private Appearance ap;
    private boolean activado;               //para saber si el bloque ya ha sido activado
    private Transform3D transformtrans;     
    private TransformGroup translacion;
    private Vector3f posicionReal;          //nos permite cambiar la posicion del objeto al seleccionar dicho bloque
    private boolean marcado;                //para distinguir los bloques pulsados de los marcados
                                            // ya que ambos estarian activados
    public Bloque(Vector3f vector,int x,int y){
        //inicializamos variables
        posx=x;
        posy=y;
        posicionReal=new Vector3f((Vector3f) vector.clone());
        activado=false;
        marcado=false;
        
        //Creamos la apariencia
        ap=new Appearance();
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK , 0.0f));
        Texture texture = new TextureLoader ("imgs/casilla.png", null).getTexture();
        ap.setTexture (texture);
        
        box = new Box(0.9f, 0.3f, 0.9f,
            Primitive.ENABLE_APPEARANCE_MODIFY | Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, ap);
        
        //Hacemos pickeable el cubo
        box.setUserData(this);
        box.setPickable(true);
        
        //Creamos la transformacion
        translacion = new TransformGroup();
        translacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformtrans = new Transform3D();
        transformtrans.setTranslation(posicionReal);
        translacion.setTransform(transformtrans); 

        //Enlazamos todo
       translacion.addChild(box);
       this.addChild(translacion);
    }
    
    /**
    * cambia la textura del bloque que ha sido activado, se cambia la textura
    * por la textura de bomba
    */
    public void activarBomba(){
        Texture texture = new TextureLoader ("imgs/bomba.png", null).getTexture();
        activarCasilla();
        ap.setTexture (texture);
        
    }
    
    /**
    * cambia la textura del bloque que ha sido activado, se cambia textura 
    * por la textura de acierto indicada por el parametro valor
    */
    public void activarAcierto(int valor){
        if(!activado){
            Texture texture = new TextureLoader ("imgs/aci"+valor+".png", null).getTexture();
            activarCasilla();
            ap.setTexture (texture);
        }
    }
    /**
    * cambia la textura del bloque que ha sido activado, se cambia la textura
    * por la textura de marca
    */
    public void activarMarca(){
        Texture texture = new TextureLoader ("imgs/marca.png", null).getTexture();
        activarCasilla();
        ap.setTexture (texture);
        marcado=true;
    }
    
    /**
    * desactiva la casilla que haya sido marcada, vuelve a su textura original de 
    * casilla blanca
    */
    public void desactivarMarca(){
        Texture texture = new TextureLoader ("imgs/casilla.png", null).getTexture();
        ap.setTexture (texture);
        desactivarCasilla();
        marcado=false;
    }
    /**
    * activa la casilla del tablero
    */
    private void activarCasilla(){
      posicionReal.y-=1.2f;
      transformtrans.setTranslation(posicionReal);
      translacion.setTransform(transformtrans);
      activado=true;
    }
    
    /**
    * desactiva la casilla del tablero
    * nos sirve cuando queremos quitar una marca
    */
    private void desactivarCasilla(){
      posicionReal.y+=1.2f;
      transformtrans.setTranslation(posicionReal);
      translacion.setTransform(transformtrans);
      activado=false;
    }
    
    public boolean getActivado(){
        return activado;
    }
    public boolean getMarcado(){
        return marcado;
    }   
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
}
