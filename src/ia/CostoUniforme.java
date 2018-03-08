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
    
    public int buscarMenor(){
        
        int menor = arbol.get(0).getCosto();
        int pos = 0;
       
        for (int i = 1; i < arbol.size(); i++) {
            
            if (arbol.get(i).getCosto() < menor ) {
                menor = arbol.get(i).getCosto();
                pos = i;
            }
        }
        return pos;
    }
    
    public int expandir(Nodo miNodo, int pos){
        int x=0 , y=0;
        if (estado) {
            return 66;
        }
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
                if(valor == 4)
                    costo=8;
                aux = new Nodo(estado, arbol.get(pos), lado, i+1, costo, arbol.get(pos).getpX()+x, arbol.get(pos).getpY()+y);
                if(estado)
                    indicaciones.miCamino(aux);
                arbol.add(aux);
            }
            arbol.remove(pos);
            expandir(arbol.get(buscarMenor()),buscarMenor());
               
            return 0;
    }
    
    public void crearArbol(){ 
        expandir(arbol.get(0),0); //expande el padre
    }
    
}
