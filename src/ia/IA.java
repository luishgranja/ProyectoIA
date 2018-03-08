/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaz.*;


/**
 *
 * @author invitado
 */
public class IA {

    int matriz[][];

    public IA(){
        matriz = new int[10][10];
        
    }
 
    public static void main(String[] args) {
    
       cargarCamino.cargarArchivo();
       Principal gui = new Principal();
       gui.setVisible(true);
       CostoUniforme a = new CostoUniforme();
       a.crearArbol(); 
    
    }
    
    

    
}
