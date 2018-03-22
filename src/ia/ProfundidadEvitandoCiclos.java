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
        int x=0 , y=0;
        int pos = arbol.size()-1;
        System.out.println(miNodo.getOperador());
        matriz[arbol.get(pos).getpX()][arbol.get(pos).getpY()]=100;
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
               aux = new Nodo(true, arbol.get(pos), lado, arbol.get(pos).getProfundidad()+1, costo+arbol.get(pos).getCosto(), arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
           
               if(valor!=100){
                   if(estado)
                        caminoProfundidad = indicaciones.miCamino(aux);
                   else{
                    arbol.add(aux);
                    expandir(aux);
                   } 
                   
                   }
               
               
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
