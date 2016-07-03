package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    
    private ArrayList<ArrayList<Bloque>> matrizBloques;
    private ArrayList<ArrayList<Integer>> matrizMinas;
    
    public Tabla(Color3f colorp,String fichero) throws IOException{
        cargarMinas(fichero);
        Appearance ap=new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        ColoringAttributes color=new ColoringAttributes(colorp ,ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(color);
        Box box = new Box(17.0f, 1.0f, 18.0f, Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        
        box.setPickable(false);
        
        matrizBloques=new ArrayList<ArrayList<Bloque>>();
        ArrayList<Bloque> auxArray;
        //Translacion que nos permite ubicar el bloque en la posicion 0,0 de nuestra
        //tabla para que desde esa posicion se posicionen todos los bloques en su lugar
        //correspondiente
        Vector3f vector=new Vector3f(-15.0f,2.0f,-15f);
        
        for(int i=0;i<16;i++){
            vector.x=-15f;
            auxArray=new ArrayList<Bloque>();
            for(int j=0;j<16;j++){
                auxArray.add(new Bloque(vector,j,i));
                this.addChild(auxArray.get(j));
                vector.x+=2f;
           }
            matrizBloques.add(auxArray);
           vector.z+=2;
        }
        
        this.addChild(box);
        
    }    
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setAcierto(int x,int y,int valor){
        matrizBloques.get(y).get(x).activarAcierto();
    }
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setBomba(int x,int y){
        matrizBloques.get(y).get(x).activarBomba();
    }
    /**
    * Cambia la textura del bloque en la posicion x,y de la tabla
    */
    public void setMarca(int x,int y){
        matrizBloques.get(y).get(x).activarMarca();
    }
    
    public void cargarMinas(String fichero)throws FileNotFoundException, IOException{
      String cadena;
      matrizMinas=new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> auxArray=new ArrayList<Integer>();
      FileReader f = new FileReader(fichero);
      BufferedReader b = new BufferedReader(f);
      while((cadena = b.readLine())!=null) {
          
          for(int i=0;i<cadena.length();i++)
            auxArray.add(Integer.parseInt(""+cadena.charAt(i)));
          
          matrizMinas.add((ArrayList<Integer>) auxArray.clone());
          auxArray.clear();
      }
      b.close();
      
      //Prueba imprimiendo la matriz de minas
      for(int i=0;i<matrizMinas.size();i++){
          for(int j=0;j<matrizMinas.get(i).size();j++)
              System.out.print(" "+matrizMinas.get(i).get(j)+" ");
          System.out.println("");     
              
      }
    }
    public void actualizarTabla(int x,int y){
        if(matrizMinas.get(y).get(x)==9)
            setBomba(x,y);
        else 
            setAcierto(x,y,matrizMinas.get(y).get(x));
    
    }
}
