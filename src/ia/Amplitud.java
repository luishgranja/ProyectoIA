/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;
import interfaz.Principal;
/**
 *
 * @author invitado
 */
public class Amplitud {
    int matriz[][];
    ArrayList<Nodo> arbol;
    Movimientos indicaciones;
    int[] mario;
    Nodo aux;
    ArrayList<Integer> posMov;
    Boolean estado = false;
    Principal test;
    
    
    public Amplitud(){
        matriz = cargarCamino.cargarArchivo();
        mario = new int[2];
        arbol = new ArrayList<Nodo>();
        indicaciones = new Movimientos();
        mario = indicaciones.mario(matriz);
        aux = new Nodo(false,null,0,0,0,mario[0], mario[1]);
        arbol.add(aux);
        posMov = new ArrayList<Integer>();
        test = new Principal();
        
    }
    
    public void expandir(Nodo miNodo){
        int x=0 , y=0;
        for(int k=0; k<arbol.size();k++){
            if(estado)
                break;
            posMov = indicaciones.posibilidades(matriz, arbol.get(k).getpX(), arbol.get(k).getpY());
            for(int i=0; i<posMov.size();i++){
                int lado = posMov.get(i);
                int valor = 0;
                
                switch (lado) {
                    case 1:
                        valor = matriz[arbol.get(k).getpX()-1][arbol.get(k).getpY()];
                        x=-1;
                        y=0;
                        break;
                    case 2:
                        valor = matriz[arbol.get(k).getpX()+1][arbol.get(k).getpY()];
                        x=1;
                        y=0;
                        break;
                    case 3:
                        valor = matriz[arbol.get(k).getpX()][arbol.get(k).getpY()-1];
                        x = 0;
                        y = -1;
                        break;
                    case 4:
                        valor = matriz[arbol.get(k).getpX()][arbol.get(k).getpY()+1];
                        x = 0;
                        y = 1;
                        break;
                    default:
                        break;
                }
                if(valor == 5){
                    estado = true;
                    System.out.println("princesa");
                    
                }
                aux = new Nodo(estado, arbol.get(k), lado, i+1, 0, arbol.get(k).getpX()+x, arbol.get(k).getpY()+y);
                if(estado)
                    indicaciones.miCamino(aux);
                arbol.add(aux);
            }
        }
    }
    
    public void crearArbol(){ 
        expandir(arbol.get(0)); //expande el padre
    }
    
}
