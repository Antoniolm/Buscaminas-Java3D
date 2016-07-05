package Model;

import GUI.Visualization;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Partida {
    private Fondo background;
    Tabla tabla;
    
    
    public Partida() throws IOException{

    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    canvas.setSize(1000, 800);

    // Se construyen las ventanas de visualización
    Visualization visualizationWindow = new Visualization (canvas,1000,800,750,0);
     
    // Se crea el universo
    VirtualUniverse universe = new VirtualUniverse();
    Locale local=new Locale(universe);
    
    //Camaras posicionada mirando al tablero
    Camara camaraJuego=new Camara(canvas, 60.0f, 0.02f, 40.0f,0.01f,45,new Point3d(0.0,14.0,55.0), new Point3d(0.0,13.25,0.0), new Vector3d(0,1,0));
    
    //Compilamos todas las camaras
    camaraJuego.compile();
    
    //Se crea la luz ambiental y la compilamos
    Luz aLight= new Luz();
    aLight.compile();
    
    // Se crea y se añade el fondo y la compilamos
    background = new Fondo();
    background.compile();

    //Color del tablero
   Color3f color=new Color3f(0.2f, 0.9f, 0.2f);
   tabla=new Tabla(color,16,40);
   //Ponemos la tabla en vertical 
    TransformGroup rotacion=new TransformGroup();
    Transform3D rotacionX=new Transform3D();
    rotacionX.rotX(Math.PI/2);
    rotacionX.setTranslation(new Vector3f(0,13.0f,0.0f));
    rotacion.setTransform(rotacionX);
    rotacion.addChild(tabla);
    
    BranchGroup bg=new BranchGroup();
    bg.addChild(rotacion);    
    
    //Añadimos el picking al bg
    Picking picar=new Picking(canvas,this);
    picar.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),300.0f));
    picar.setStatus(bg);
    
    bg.addChild(picar);
    
    //Compilamos los BranchGroup
    bg.compile();
    

    
    
    //Añadimos nuestros tableros al locale
    local.addBranchGraph(bg);
    //Añadimos al locale los branchgraph, luz ambiental fondo y cartel
    local.addBranchGraph(aLight);
    local.addBranchGraph(background);
    //Añadimos nuestras camaras al locale
    local.addBranchGraph(camaraJuego);
    // Se muestra la ventana
    visualizationWindow.setVisible(true);
    }
    
    
    /**
    * Realiza una opción u otro segun el boton del raton clickeado
    * ,es decir, si tenemos la opc 0 es descubrir una casilla y si
    * es la opc 1 es marcar la casilla como bomba
    */
    void procesarAccion(int posx,int posy,int opc){
    if(opc==0)// Se pulsa una casilla para ver si hay bomba o no
        tabla.actualizarTabla(posx, posy);
    else if(opc==1)//Se marca una casilla como una bandera
        tabla.setMarca(posx,posy);
        else
            tabla.setNoMarca(posx, posy);
    }
}
