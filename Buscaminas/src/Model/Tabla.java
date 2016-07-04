package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
    private int tamTablero;
    private int numMinas;
    
    public Tabla(Color3f colorp,int tam,int nminas){
        tamTablero=tam;
        numMinas=nminas;
        matrizMinas=new ArrayList<ArrayList<Integer>>();
        //cargarMinas(fichero);
        generarMinas();
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
        
        for(int i=0;i<tamTablero;i++){
            vector.x=-15f;
            auxArray=new ArrayList<Bloque>();
            for(int j=0;j<tamTablero;j++){
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
        matrizBloques.get(y).get(x).activarAcierto(valor);
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
    
    public void generarMinas(){    
        int casillas=tamTablero*tamTablero;
        ArrayList<Integer> posMinas=new ArrayList<Integer>();//array que nos permite no tener 2 minas en la misma posición
        int nuevaMina,posYMina,posXMina,minasAlrededor=0;
        //Inicializamos el array con todas las posiciones a 0
        ArrayList<Integer> auxArray=new ArrayList<Integer>(Collections.nCopies(tamTablero, 0));
        Random rnd=new Random();
        int contPos=0; //contador de posiciones absolutapara ir introduciendo las minas
        int i=0;
        
        //Inicializamos el mapa
        for(int j=0;j<tamTablero;j++)
            matrizMinas.add((ArrayList<Integer>) auxArray.clone());
        

        //Generamos las minas de forma aleatoria
        while(i<numMinas){
                nuevaMina= (int)(rnd.nextDouble() * casillas);
                //System.out.println("nº "+ i+" mina: "+nuevaMina);
                if(!posMinas.contains(nuevaMina)){
                    posMinas.add(nuevaMina);
                    posYMina=nuevaMina/16;
                    posXMina=nuevaMina%16;
                    matrizMinas.get(posYMina).set(posXMina, 9); //se representa cada mina
                    i++;
                }
        }
        System.out.println("Numero de minas " +i);
       
        //Contamos el numero de minas que tiene a su alrededor
        for(int y=0;y<tamTablero;y++){
            for(int x=0;x<tamTablero;x++){
                if(matrizMinas.get(y).get(x)!=9){
                    
                    if(y-1>=0 && matrizMinas.get(y-1).get(x)==9)
                        minasAlrededor++;
                    if(y+1<tamTablero && matrizMinas.get(y+1).get(x)==9)
                        minasAlrededor++;
                    
                    if(x-1>=0 && matrizMinas.get(y).get(x-1)==9)
                        minasAlrededor++;
                    if(x+1<tamTablero && matrizMinas.get(y).get(x+1)==9)
                        minasAlrededor++;
                    
                    if(y-1>=0 && x-1>=0 && matrizMinas.get(y-1).get(x-1)==9)
                        minasAlrededor++;
                    if(y-1>=0 && x+1<tamTablero && matrizMinas.get(y-1).get(x+1)==9)
                        minasAlrededor++;
                    if(y+1<tamTablero && x-1>=0 && matrizMinas.get(y+1).get(x-1)==9)
                        minasAlrededor++;
                    if(y+1<tamTablero && x+1<tamTablero && matrizMinas.get(y+1).get(x+1)==9)
                        minasAlrededor++;
                    matrizMinas.get(y).set(x,minasAlrededor);
                    minasAlrededor=0;
                }
                
            }
        }
        
        
          for(int p=0;p<matrizMinas.size();p++){
          for(int j=0;j<matrizMinas.get(p).size();j++)
              System.out.print(" "+matrizMinas.get(p).get(j)+" ");
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
