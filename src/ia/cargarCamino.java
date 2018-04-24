/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ennuikibun
 */
public class cargarCamino {
    
    public cargarCamino(){
        
    }
    
    public static int[][] cargarArchivo(){
        File archivo = new File ("Prueba4.txt");
        int matriz[][] = new int[10][10];
        try {
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            int c = 0;
            while((linea=br.readLine())!=null){
                String[] aux = linea.split(" ");
                for(int i=0; i<aux.length; i++){
                    matriz[c][i] = Integer.parseInt(aux[i]);
                    //System.out.print(matriz[c][i]);
                }
                //System.out.println("");
                c++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;
    }
    
}
