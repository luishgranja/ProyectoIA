/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import ia.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author luis
 */
public class Principal extends javax.swing.JFrame {

    IA aux;
    Movimientos auxMovimientos = new Movimientos();
    
    int[][] arreglo;
    //ruta mario
    String auxRutaMario = "/img/2.png";
    int[] coord;
    int[] posicionActual;
    boolean flor;
    
    
    /**
     * CONSTRUCTOR
     */
    public Principal() {
        
        arreglo = cargarCamino.cargarArchivo();
        flor = false;
        initComponents();
        setResizable(false);
        this.setTitle("MARIO SMART");
        this.setLocationRelativeTo(null);
        
        //agrega el banner
        String path = "/img/banner.png";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        banner.setIcon(icon);
        
        //agrega las opciones al combobox
        combobox.addItem("Búsqueda No Informada");
        combobox.addItem("Búsqueda Informada");
        
        //Agrega una opicion vacia al combobox auxiliar
        comboboxAux.addItem("");
        
        coord = new int[2];
        
        cargarImagenes();
        
        
        
        /* Poner imagenes a los botones
        botonStart.setIcon(new ImageIcon(this.getClass().getResource("/img/botonstart.png")));
        botonStart.setBorder(null);
        botonStart.setBorderPainted(false);
        botonStart.setContentAreaFilled(false);
        botonStart.setFocusPainted(false);
        botonStart.setPressedIcon(new ImageIcon(this.getClass().getResource("/img/botonstartpeq.png")));
        
        botonReset.setIcon(new ImageIcon(this.getClass().getResource("/img/reload.png")));
        botonReset.setBorder(null);
        botonReset.setBorderPainted(false);
        botonReset.setContentAreaFilled(false);
        botonReset.setFocusPainted(false);
        */
        
        
        posicionActual = auxMovimientos.mario(arreglo);

    }
    
    public void cargarImagenes(){
        
        //agrega las imagenes al tablero
       
        for (int i = 0; i < arreglo.length ; i++) {
            
            for (int j = 0; j < arreglo[i].length; j++) {
                
            //crea un label auxiliar
            JLabel auxLabel = new JLabel();
            
            String auxRuta = "/img/"+arreglo[i][j]+".png";
            URL urlAux = this.getClass().getResource(auxRuta);
            ImageIcon iconAux = new ImageIcon(urlAux);
            auxLabel.setIcon(iconAux);
            tablero.add(auxLabel);
            auxLabel.setBounds(50*j, 50*i, 50, 50);
            if (arreglo[i][j]==2){
                coord[0] = 50*j;
                coord[1] = 50*i;
            }
                
            }
        }
    }
    
    public void modificaTortuga(){
        
        
    }
    
    public void modificarCamino(ArrayList<Integer> camino){ 
            JLabel auxLabel = new JLabel();
            URL urlAux = this.getClass().getResource(auxRutaMario);
            ImageIcon iconAux = new ImageIcon(urlAux);    
            auxLabel.setIcon(iconAux);
            int operador=0;     
            int pos = posicionActual[0]*arreglo.length + posicionActual[1];
            
            JLabel auxLabel2 = new JLabel();    
            String auxRuta2 = "/img/0.png";
        
            URL urlAux2 = this.getClass().getResource(auxRuta2);
            ImageIcon iconAux2 = new ImageIcon(urlAux2);
            auxLabel2.setIcon(iconAux2);
            tablero.remove(pos);
            tablero.add(auxLabel2, pos);
            auxLabel2.setBounds(coord[0], coord[1], 50, 50);
            
            
            for (int i = camino.size()-1; i >=0 ; i--) {
                
                
                if (flor && (arreglo[coord[1]/50][coord[0]/50] == 4 )) {
                    int pos1 = (coord[1]/50)*arreglo.length + (coord[0]/50);
                    System.out.println("");
            
            JLabel auxLabelModificada = new JLabel();    
            String auxRutaModificada = "/img/0.png";
        
            URL urlAuxModificada = this.getClass().getResource(auxRutaModificada);
            ImageIcon iconAuxModificada = new ImageIcon(urlAuxModificada);
            auxLabelModificada.setIcon(iconAuxModificada);
            tablero.remove(pos1);
            tablero.add(auxLabelModificada, pos1);
            auxLabelModificada.setBounds(coord[0], coord[1], 50, 50);
                }
               operador = camino.get(i);
                switch (operador) {      
                    case 1:
                        pos = pos-10;
                        coord[1] = coord[1]-50;
                        tablero.add(auxLabel, pos);
                        auxLabel.setBounds(coord[0], coord[1], 50, 50);
                        break;
                        
                    case 2:
                        pos = pos+10;
                        coord[1] = coord[1]+50;
                        tablero.add(auxLabel, pos);
                        auxLabel.setBounds(coord[0], coord[1], 50, 50);
                        break;
                        
                    case 3:
                        pos = pos-1;
                        coord[0] = coord[0]-50;
                        tablero.add(auxLabel, pos);
                        auxLabel.setBounds(coord[0], coord[1], 50, 50);
                        break;
                        
                    case 4:
                        pos = pos+1;
                        coord[0] = coord[0]+50;
                        tablero.add(auxLabel, pos);
                        auxLabel.setBounds(coord[0], coord[1], 50, 50);
                        break;                                    
                    default:
                        throw new AssertionError();                 
                }
                
                if(arreglo[coord[1]/50][coord[0]/50] == 3 ) {
                    auxLabel.setIcon(new ImageIcon(this.getClass().getResource("/img/up.png")));
                    flor = true;
                }
                try{
                    Thread.sleep(500);
                } catch (Exception e) {}      
                this.paintAll(this.getGraphics());
        }
            
        tablero.removeAll();
        this.paintAll(this.getGraphics());     
        //Pone la imagen de win y repinta
       JLabel auxLabelWin = new JLabel();
       String auxRuta = "/img/win.gif";
       URL urlAuxWin = this.getClass().getResource(auxRuta);
       ImageIcon iconAuxWin = new ImageIcon(urlAuxWin);
       auxLabelWin.setIcon(iconAuxWin);
       auxLabelWin.setBounds(0,0,500,500);
       tablero.add(auxLabelWin);
       this.paintAll(this.getGraphics());
       //Se desactiva el boton para que no se ejecute este metodo
       botonStart.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablero = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        combobox = new javax.swing.JComboBox<>();
        botonStart = new javax.swing.JButton();
        comboboxAux = new javax.swing.JComboBox<>();
        botonReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoNodos = new javax.swing.JTextField();
        campoProfundidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoTiempo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        banner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(tableroLayout);
        tableroLayout.setHorizontalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        tableroLayout.setVerticalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        combobox.setModel(new javax.swing.DefaultComboBoxModel<>());
        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
            }
        });

        botonStart.setText("BUSCAR");
        botonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonStartActionPerformed(evt);
            }
        });

        comboboxAux.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboboxAux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxAuxActionPerformed(evt);
            }
        });

        botonReset.setText("RECARGAR");
        botonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Cantidad de Nodos Expandidos");

        campoNodos.setText("0");
        campoNodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNodosActionPerformed(evt);
            }
        });

        campoProfundidad.setText("0");
        campoProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoProfundidadActionPerformed(evt);
            }
        });

        jLabel2.setText("Profundidad del Árbol");

        campoTiempo.setText("0");
        campoTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTiempoActionPerformed(evt);
            }
        });

        jLabel3.setText("Tiempo de Computo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(botonStart)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonReset)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(campoNodos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboboxAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(botonStart)
                .addGap(18, 18, 18)
                .addComponent(botonReset)
                .addGap(20, 20, 20)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void botonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonStartActionPerformed
        long startTime = 0;
        long endTime = 0;
        long totalTime = 0;
        int expandidos = 0;
        int profundidad = 0;
        if (combobox.getSelectedIndex() == 0) {
            
            switch (comboboxAux.getSelectedIndex()) {
                case 0:
                    BusquedaNoInformada amplitud = new BusquedaNoInformada();
                    startTime = System.currentTimeMillis();
                    amplitud.crearArbol("Amplitud");
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime; //en Milisegundos
                    expandidos = amplitud.getExpandidos(); //Nodos Expandidos
                    profundidad = amplitud.getProfundidad(); //Profundidad del Arbol
                    modificarCamino(amplitud.getCamino());
                    break;
                case 1:
                    BusquedaNoInformada costoUniforme = new BusquedaNoInformada();
                    startTime = System.currentTimeMillis();
                    costoUniforme.crearArbol("Costo");
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime; //en Milisegundos
                    expandidos = costoUniforme.getExpandidos(); //Nodos Expandidos
                    profundidad = costoUniforme.getProfundidad(); //Profundidad del Arbol
                    modificarCamino(costoUniforme.getCamino());
                    break;
                case 2:
                   BusquedaNoInformada profundidadC = new BusquedaNoInformada();
                   startTime = System.currentTimeMillis();
                    profundidadC.crearArbol("Profundidad");
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime; //en Milisegundos
                    expandidos = profundidadC.getExpandidos(); //Nodos Expandidos
                    profundidad = profundidadC.getProfundidad(); //Profundidad del Arbol
                    modificarCamino(profundidadC.getCamino());
                    break;
                default:
                    break;
            }
            
        }
        
        if (combobox.getSelectedIndex() == 1) {
            
            switch (comboboxAux.getSelectedIndex()) {
                case 0:
                    BusquedaInformada avara = new BusquedaInformada();
                    startTime = System.currentTimeMillis();
                    avara.crearArbol("Avara");
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime; //en Milisegundos
                    expandidos = avara.getExpandidos(); //Nodos Expandidos
                    profundidad = avara.getProfundidad(); //Profundidad del Arbol
                    modificarCamino(avara.getCamino());
                    break;
                case 1:
                    BusquedaInformada a = new BusquedaInformada();
                    startTime = System.currentTimeMillis();
                    a.crearArbol("A");
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime; //en Milisegundos
                    expandidos = a.getExpandidos(); //Nodos Expandidos
                    profundidad = a.getProfundidad(); //Profundidad del Arbol
                    modificarCamino(a.getCamino());
                    break;
                default:
                    break;
            }   
        }
        
        //Borra la info antes de ingresarla
        campoNodos.setText(null);
        campoProfundidad.setText(null);
        campoTiempo.setText(null);
        
        campoNodos.setText(""+expandidos);
        campoProfundidad.setText(""+profundidad);
        campoTiempo.setText(""+totalTime + " ms");
    }//GEN-LAST:event_botonStartActionPerformed

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
        // TODO add your handling code here:
        
        if (combobox.getSelectedIndex() == 0) {
            
            comboboxAux.removeAllItems();
            comboboxAux.addItem("Amplitud");
            comboboxAux.addItem("Costo Uniforme");
            comboboxAux.addItem("Profundidad Evitando Ciclos");       
        }else{
            comboboxAux.removeAllItems();
            comboboxAux.addItem("Avara");
            comboboxAux.addItem("A*");
        
        }
    }//GEN-LAST:event_comboboxActionPerformed

    private void comboboxAuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxAuxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxAuxActionPerformed

    private void botonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonResetActionPerformed
        // TODO add your handling code here:
        
        //Se limpia el panel y se vuelve a cargar con las imagenes y se repinta
        tablero.removeAll();
        cargarImagenes();
        this.paintAll(this.getGraphics());
        flor = false;
        
        //Borra los campos de informacion
        campoNodos.setText(""+0);
        campoProfundidad.setText(""+0);
        campoTiempo.setText(""+0);
        
        
        //se activa el boton para poder dar uso de los metodos de busqueda otra vez
        botonStart.setEnabled(true);
    }//GEN-LAST:event_botonResetActionPerformed

    private void campoNodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNodosActionPerformed

    private void campoProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoProfundidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoProfundidadActionPerformed

    private void campoTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTiempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTiempoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JButton botonReset;
    private javax.swing.JButton botonStart;
    private javax.swing.JTextField campoNodos;
    private javax.swing.JTextField campoProfundidad;
    private javax.swing.JTextField campoTiempo;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JComboBox<String> comboboxAux;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel tablero;
    // End of variables declaration//GEN-END:variables
}
