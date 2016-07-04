/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class PairInt {
    private int x;
    private int y; 
    
    public PairInt(){};
    
    public PairInt(int elemX,int elemY){
     x=elemX;
     y=elemY;
    }
    public PairInt clone(){
        PairInt salida=new PairInt(x,y);
        return salida;
    }
    public void setX(int elem){
        x=elem;
    }
    public void setY(int elem){
        y=elem;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}
    
    
