/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.gui;

import beans.RandProdus;
import client.controllers.ClientController;
import java.util.ArrayList;
import tableModels.RandProduseTableModel;

/**
 *
 * @author Dell
 */
public class VizualizareComenzi extends javax.swing.JFrame {
     private RandProduseTableModel model;
    /**
     * Creates new form VizualizareComenzi
     */
    public VizualizareComenzi() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Vizualizare Comenzi");
        model = (RandProduseTableModel) jTable1.getModel();
        afisareRandProduse();
    }
    
    public void afisareRandProduse(){
        ArrayList<RandProdus> rand = ClientController.getInstance().getRand();
        for(RandProdus rp: rand){
            model.adaugaRandProdus(rp); 
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new RandProduseTableModel());
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
