/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ennuikibun
 */
public class Movimientos {
    
ArrayList<Integer> movimientos;

    public Movimientos(){
        movimientos  = new ArrayList<Integer>(); 
    }
    /**
     * 
     * @param matriz
     * @param i Posicion actual en x, se usa para calcular los posibles movimientos sin salirse de los limites de la matriz
     * @param j Posicion actual en y, se usa para calcular los posibles movimientos sin salirse de los limites de la matriz
     * @return  Retorna un arreglo con las posibles direcciones donde se puede mover mario en la posicion (i,j)
     */
    public ArrayList<Integer> posibilidades(int matriz[][], int i, int j){
        ArrayList<Integer> posibles = new ArrayList<>();
        //Posibles movimientos que puede tener mario, los agrega a un arreglo
        //1 arriba
        //2 abajo
        //3 izquierda
        //4 derecha
        if((i-1) >= 0 && (i-1) <= 9){
            if (matriz[i-1][j]!=1) {
                posibles.add(1);
            }
        }
            
        if( (i+1) >= 0 && (i+1) <= 9 ){
            if (matriz[i+1][j]!=1) {
                posibles.add(2);
            }
        }
            
        if((j-1) >= 0 && (j-1) <= 9 ){
            if (matriz[i][j-1]!=1) {
                posibles.add(3);
            }
        }
            
        if((j+1) >= 0 && (j+1) <= 9){
            if ( matriz[i][j+1]!=1) {
                posibles.add(4);
            }
        }
            
        return posibles;        
    }
    
    public int[] mario(int matriz[][]){
        int[] pos = new int[2];
        
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length;j++){
                if(matriz[i][j]==2){
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        
        return pos;
    }
    
    public ArrayList<Integer> miCamino(Nodo nodo){
        System.out.println(nodo.getOperador());
        movimientos.add(nodo.getOperador());
        if(nodo.getPadre().getPadre()!=null)
            miCamino(nodo.getPadre());
        return movimientos;
    }
    
}
