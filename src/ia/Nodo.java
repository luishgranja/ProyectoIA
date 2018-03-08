 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

/**
 *
 * @author invitado
 */
public class Nodo {
    Boolean estado;
    Nodo padre;
    int operador;
    int profundidad;
    int costo;
    int pX;
    int pY;
    Boolean flor;
    
    public Nodo() {
    
    estado = false;
    padre = null;
    operador = 0;
    profundidad = 0;
    costo = 0;
    flor = false;
    pX = 0;
    pY = 0;
}
    public Nodo(Boolean pEstado, Nodo pPadre, int pOperador, int pProfundidad, int pCosto, int posicionX, int posicionY){
        
    estado = pEstado;
    padre = pPadre;
    operador = pOperador;
    profundidad = pProfundidad;
    costo = pCosto;
    pX = posicionX;
    pY = posicionY;
    }
    /*
    public void crearNodo(Boolean estado, Nodo padre, int operador, int profundidad, int costo){
        
        this.estado = estado;
        this.padre = padre;
        this.operador = operador;
        this.profundidad = profundidad;
        this.costo = costo;
        
    }
    */
    

    public void setFlor(Boolean flor) {
        this.flor = flor;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public int getOperador() {
        return operador;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getCosto() {
        return costo;
    }

    public Boolean getFlor() {
        return flor;
    }

    public int getpX() {
        return pX;
    }

    public int getpY() {
        return pY;
    }
   
    
    
    
    
   
}
