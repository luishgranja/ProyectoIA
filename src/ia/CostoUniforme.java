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
public class CostoUniforme {
    
    int matriz[][];
    ArrayList<Nodo> arbol;
    Movimientos indicaciones;
    int[] mario;
    Nodo aux;
    ArrayList<Integer> posMov;
    Boolean estado = false;
    Principal test;
    int profundidad;
    
    
    public CostoUniforme(){
        
        
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
    /**
     * 
     * @return la posicion del elemento con menor costo
     */
    public int buscarMenor(){
        
        int menor = arbol.get(0).getCosto();
        int pos = 0;
        for (int i = 1; i < arbol.size(); i++) {
            if (arbol.get(i).getCosto() < menor) {
                if(arbol.get(i).getOperador()+1!=arbol.get(i).getPadre().getOperador() && arbol.get(i).getOperador()-1!=arbol.get(i).getPadre().getOperador() ){
                menor = arbol.get(i).getCosto();
                pos = i;   
                }
                
            }
        }
        return pos;
    }
    
    /**
     * 
     * @param miNodo
     * @param pos
     * @return 
     */
    public void expandir(Nodo miNodo, int pos){
        int x=0 , y=0;
       posMov = indicaciones.posibilidades(matriz, arbol.get(pos).getpX(), arbol.get(pos).getpY());
       for(int i=0; i<posMov.size();i++){
              int lado = posMov.get(i);
              int valor = 0;
              int costo = 1;
               switch (lado) {
                    case 1:
                        valor = matriz[arbol.get(pos).getpX()-1][arbol.get(pos).getpY()];
                        x=-1;
                        y=0;
                        break;
                    case 2:
                        valor = matriz[arbol.get(pos).getpX()+1][arbol.get(pos).getpY()];
                        x=1;
                        y=0;
                        break;
                    case 3:
                        valor = matriz[arbol.get(pos).getpX()][arbol.get(pos).getpY()-1];
                        x = 0;
                        y = -1;
                        break;
                    case 4:
                        valor = matriz[arbol.get(pos).getpX()][arbol.get(pos).getpY()+1];
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
               else if(valor == 4)
                    costo+=7;
              
               aux = new Nodo(estado, arbol.get(pos), lado, arbol.get(pos).getProfundidad()+1, costo+arbol.get(pos).getCosto(), arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
              //System.out.println("Costo " +aux.getCosto() + " - Profundidad " + aux.getProfundidad() + " - Valor "+valor); 
               if(estado)
                   indicaciones.miCamino(aux);
               arbol.add(aux);
            }
       arbol.remove(pos);
       if(!estado){
            expandir(arbol.get(buscarMenor()),buscarMenor());
       }          
    }
    
    public void crearArbol(){ 
        expandir(arbol.get(0),0); //expande el padre
    }
    
}
