/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;

/**
 *
 * @author invitado
 */
public class ProfundidadEvitandoCiclos {
   
    int matriz[][];
    ArrayList<Nodo> arbol;
    Movimientos indicaciones;
    int[] mario;
    Nodo aux;
    ArrayList<Integer> posMov;
    Boolean estado = false;
    ArrayList<Integer> caminoProfundidad;
    
    
    
    public ProfundidadEvitandoCiclos(){
        matriz = cargarCamino.cargarArchivo();
        mario = new int[2];
        arbol = new ArrayList<Nodo>();
        indicaciones = new Movimientos();
        mario = indicaciones.mario(matriz);
        aux = new Nodo(false,null,0,0,0,mario[0], mario[1]);
        arbol.add(aux);
        caminoProfundidad = new ArrayList<Integer>();
        posMov = new ArrayList<Integer>();
    }
    
    
    public void expandir(Nodo miNodo){
        
        int pos = arbol.size()-1;
        System.out.println(miNodo.getOperador());
        matriz[arbol.get(pos).getpX()][arbol.get(pos).getpY()]=1;
          
                   if(estado)
                        caminoProfundidad = indicaciones.miCamino(aux);
                   else{
                    arbol.add(aux);
                    expandir(aux);
                   } 
       arbol.remove(pos);
            
        
    }
    
    public void crearArbol(){ 
        expandir(arbol.get(0)); //expande el padre
    }
    
    public ArrayList<Integer> getCaminoAmplitud(){
        return caminoProfundidad;
    } 
    
}
