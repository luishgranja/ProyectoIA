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
public class BusquedaNoInformada {
    
    int matriz[][];
    ArrayList<Nodo> arbol;
    Movimientos indicaciones;
    int[] mario;
    Nodo aux;
    ArrayList<Integer> posMov;
    int profundidad;
    ArrayList<Integer> camino;
    
    public BusquedaNoInformada(){  
        matriz = cargarCamino.cargarArchivo();
        mario = new int[2];
        arbol = new ArrayList<>();
        indicaciones = new Movimientos();
        mario = indicaciones.mario(matriz);
        aux = new Nodo(false,null,0,0,0,mario[0], mario[1]);
        arbol.add(aux);
        posMov = new ArrayList<>();
        camino = new ArrayList<>();
    }
    /**
     * 
     * @return la posicion del elemento con menor costo sin contar devolverse.
     */
    public int buscarMenor(){
        int menor = 100;
        int pos = 0;
        for (int i = 0; i < arbol.size(); i++) {
            if (arbol.get(i).getCosto() < menor) {
                if(arbol.get(i).getPadre().getOperador()!=0){
                        if(arbol.get(i).getOperador()+1!=arbol.get(i).getPadre().getOperador() &&
                        arbol.get(i).getOperador()-1!=arbol.get(i).getPadre().getOperador() ){
                        menor = arbol.get(i).getCosto();
                        pos = i;   
                    }        
                }
                else{
                     menor = arbol.get(i).getCosto();
                     pos = i; 
                }                    
            }
        }
        return pos;
    }

    public void expandirNodos(int pos,boolean noCiclos){
        int x=0 , y=0;
       Boolean estado = false;
       posMov.clear();
       posMov = indicaciones.posibilidades(matriz, arbol.get(pos).getpX(), arbol.get(pos).getpY());
       if(!posMov.isEmpty()){
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
                 if(valor == 5)
                     estado=true;
                 else if(valor == 4)
                      costo+=7;  
                aux = new Nodo(estado, arbol.get(pos), lado, arbol.get(pos).getProfundidad()+1, costo+arbol.get(pos).getCosto(), arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
                arbol.add(aux);
                if(noCiclos){
                     matriz[arbol.get(arbol.size()-1).getpX()][arbol.get(arbol.size()-1).getpY()]=1;
                      if(aux.getEstado()==true){
                            System.out.println("princesa");
                            camino = indicaciones.miCamino(aux);               
                        }
                      else
                            expandirNodos(arbol.size()-1,true);                  
                }     
              }  
       }
       else {
           if(noCiclos){
               arbol.remove(pos);
               expandirNodos(pos-1,true);
           }
           
       }
           
    }
    
    public void amplitud(Nodo miNodo){
        expandirNodos(0,false);
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }
       else{   
       arbol.remove(0);
       amplitud(arbol.get(0)); 
         }
    }
    
     public void costoUniforme(Nodo miNodo, int pos){
        camino.clear();
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }
       else{
       expandirNodos(pos,false);
       arbol.remove(pos);
        int nodo = buscarMenor();
       costoUniforme(arbol.get(nodo),nodo);
        }
    }
     
     public void profundidadEvitandoCiclos(Nodo miNodo, int pos){
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }
        expandirNodos(pos,true);
     }
     
    //Crea el árbol con el nodo raíz y empieza a expandir dependiendo del método.
    public void crearArbol(String metodo){ 
        if(metodo.equals("Amplitud"))
            amplitud(arbol.get(0)); 
        else if(metodo.equals("Costo"))
            costoUniforme(arbol.get(0),0); 
        else if(metodo.equals("Profundidad"))
            expandirNodos(0,true);         
    }
    
    public ArrayList<Integer> getCamino(){
        return camino;
    }   
    
}
