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
    int flor;
    int expandidos;
    
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
        flor=0;
        int expandidos = 0;
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
                    if(arbol.get(i).getPadre().getFlor()==0){
                        if(arbol.get(i).getOperador()+1!=arbol.get(i).getPadre().getOperador() &&
                        arbol.get(i).getOperador()-1!=arbol.get(i).getPadre().getOperador()){
                            menor = arbol.get(i).getCosto();
                            pos = i;   
                        }
                    }
                    else{
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

    //Función que verifica que no se devuelva 
   public int siguiente(int pos){
       int res = pos;
        if(arbol.get(pos).getPadre().getOperador()!=0){
              if(arbol.get(pos).getOperador()+1!=arbol.get(pos).getPadre().getOperador() &&
              arbol.get(pos).getOperador()-1!=arbol.get(pos).getPadre().getOperador() ){
                         return res;  
                    }
              else{
                  arbol.remove(pos);
                  res = siguiente(pos);
              }
                  
                }
        return res;
    }


    public void expandirNodos(int pos,boolean noCiclos){
        expandidos++;
        int x=0 , y=0;
       Boolean estado = false;
       flor=0;
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
                 if(valor==3 || arbol.get(pos).getFlor()==1)
                     flor=1;
                 if(arbol.get(pos).getFlor()==0 && valor ==4) 
                     costo+=7;
                 if(valor == 5)
                     estado=true;
                int miProfundidad = arbol.get(pos).getProfundidad()+1;
                 aux = new Nodo(estado, arbol.get(pos), lado, miProfundidad, costo+arbol.get(pos).getCosto(), arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
                 aux.setFlor(flor);
                 arbol.add(aux);
                if(miProfundidad > profundidad)
                    profundidad = miProfundidad;
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
    
    public void amplitud(Nodo miNodo,int pos){
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                   camino = indicaciones.miCamino(miNodo);               
        }
       else{   
            expandirNodos(pos,false);
            arbol.remove(pos);
            int siguiente = siguiente(pos);
            amplitud(arbol.get(siguiente),siguiente); 
         }
    }
    
     public void costoUniforme(Nodo miNodo, int pos){
        if(miNodo.getEstado()==true){
                   System.out.println("princesa");
                    System.out.println("Costo: "+miNodo.getCosto());
                   camino = indicaciones.miCamino(miNodo);               
        }
       else{
       expandirNodos(pos,false);
       if(miNodo.getPadre()!=null)
            System.out.println("Expande: "+miNodo.getpX()+","+miNodo.getpY()+" desde "+miNodo.getPadre().getpX()+","+miNodo.getPadre().getpY()+" costando: "+miNodo.getCosto());
       arbol.remove(pos);
        int nodo = buscarMenor();
       costoUniforme(arbol.get(nodo),nodo);
        }
    }
     
     
    //Crea el árbol con el nodo raíz y empieza a expandir dependiendo del método.
    public void crearArbol(String metodo){ 
        if(metodo.equals("Amplitud"))
            amplitud(arbol.get(0),0); 
        else if(metodo.equals("Costo"))     
            costoUniforme(arbol.get(0),0);            
        else if(metodo.equals("Profundidad"))
            expandirNodos(0,true);         
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
