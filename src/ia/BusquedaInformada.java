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
 * @author luis
 */
public class BusquedaInformada {
   int matriz[][];
    ArrayList<Nodo> arbol;
    Movimientos indicaciones;
    int[] mario;
    Nodo aux;
    ArrayList<Integer> posMov;
    ArrayList<Integer> camino;
    Boolean flor;
    int[] princesa;
    int expandidos;
    int profundidad;
        
    
    public BusquedaInformada(){
        matriz = cargarCamino.cargarArchivo();
        mario = new int[2];
        arbol = new ArrayList<>();
        indicaciones = new Movimientos();
        mario = indicaciones.mario(matriz);
        aux = new Nodo(false,null,0,0,0,mario[0], mario[1]);
        arbol.add(aux);
        posMov = new ArrayList<>();
        camino = new ArrayList<>();
        flor=false;
        princesa = new int[2];
        princesa = encontrarPrincesa();
        expandidos = 0;
        profundidad = 0;

    }
  
  public int distancia(Nodo miNodo){
    return Math.abs(princesa[0]-miNodo.getpX()) + Math.abs(princesa[1])-miNodo.getpY();
  }
    
    
 public int calcularHeuristica(int pos, int prof, Boolean a){
        int menor = 100;
        int auxPos = 0;
        for (int i = 0; i < arbol.size(); i++) {
          if(posMov.size()>1){
            if (prof < arbol.get(i).getProfundidad()) {
              if(arbol.get(i).getOperador()+1!=arbol.get(i).getPadre().getOperador() &&
              arbol.get(i).getOperador()-1!=arbol.get(i).getPadre().getOperador()){
                if(a){
                    if ( (distancia(arbol.get(i)) + arbol.get(i).getCosto()  < menor) ) {
                    menor = distancia(arbol.get(i)) + arbol.get(i).getCosto() ;
                    auxPos = i;
                	}                  
                }
                else{
                    if ( distancia(arbol.get(i)) < menor) {
                    menor = distancia(arbol.get(i));
                    auxPos = i;
                	}                    
                }              
              }

            }            
          }
          else{
            matriz[arbol.get(pos).getpX()][arbol.get(pos).getpY()] = 1;
            arbol.remove(pos);
            auxPos = pos;
            break;
          }
 
              }
        
        return auxPos;
 }
    
    public int[] encontrarPrincesa(){
        int[] coordenada = new int[2];
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j]==5) {
                    coordenada[0] = i;
                    coordenada[1] = j;
                }
            }
        }
        return coordenada;
    }
    
    
    public void expandirNodos(int pos){
        expandidos++;
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
                 if(valor==3)
                     flor=true;
                 else if(valor == 5)
                     estado=true;
                 else if(valor == 4 && !flor)
                      costo+=7; 
                 int miProfundidad = arbol.get(pos).getProfundidad()+1;
                aux = new Nodo(estado, arbol.get(pos), lado, miProfundidad, costo+arbol.get(pos).getCosto(), arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
                arbol.add(aux);
                if(miProfundidad > profundidad)
                    profundidad = miProfundidad;
              }
              
       }
           
    }
    
   public void busquedaAvara(Nodo miNodo, int pos){
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }else{
          expandirNodos(pos);
          int prof = miNodo.getProfundidad();
          int siguiente = calcularHeuristica(pos, prof, false);
          busquedaAvara(arbol.get(siguiente),siguiente);
        }     
    }
  
   public void busquedaA(Nodo miNodo, int pos){
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }else{
          expandirNodos(pos);
          int prof = miNodo.getProfundidad();
          int siguiente = calcularHeuristica(pos, prof,true);
          busquedaA(arbol.get(siguiente),siguiente);
        }     
    }
    
    //Crea el árbol con el nodo raíz y empieza a expandir dependiendo del método.
    public void crearArbol(String metodo){ 
        if(metodo.equals("Avara"))
            busquedaAvara(arbol.get(0),0); 
        else if(metodo.equals("A"))
            busquedaA(arbol.get(0),0);         
    }
    
    
    public ArrayList<Integer> getCamino(){
        return camino;
    }

    public int getExpandidos() {
        return expandidos;
    }

    public int getProfundidad() {
        return profundidad;
    }
    
    
}
