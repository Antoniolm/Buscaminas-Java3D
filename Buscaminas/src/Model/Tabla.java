package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Tabla extends BranchGroup{
    
    private ArrayList<ArrayList<Bloque>> matrizbloques;
    
    public Tabla(Color3f colorp){
        Appearance ap=new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        ColoringAttributes color=new ColoringAttributes(colorp ,ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(color);
        Box box = new Box(9.0f, 1.0f, 9.0f, Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        
        box.setPickable(false);
        
        matrizbloques=new ArrayList<ArrayList<Bloque>>();
        ArrayList<Bloque> auxArray;
        //Translacion que nos permite ubicar el bloque en la posicion 0,0 de nuestra
        //tabla para que desde esa posicion se posicionen todos los bloques en su lugar
        //correspondiente
        Vector3f vector=new Vector3f(-7.0f,2.0f,-7f);
        
        for(int i=0;i<8;i++){
            vector.x=-7f;
            auxArray=new ArrayList<Bloque>();
            for(int j=0;j<8;j++){
                auxArray.add(new Bloque(vector,j,i));
                this.addChild(auxArray.get(j));
                vector.x+=2f;
           }
            matrizbloques.add(auxArray);
           vector.z+=2;
        }
        
        this.addChild(box);
        setAcierto(0,0);
    }    
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setAcierto(int x,int y){
        matrizbloques.get(y).get(x).activarAcierto();
    }
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setBomba(int x,int y){
        matrizbloques.get(y).get(x).activarBomba();
    }
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setMarca(int x,int y){
        matrizbloques.get(y).get(x).activarMarca();
    }
    
}
