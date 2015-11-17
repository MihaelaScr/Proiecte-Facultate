/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.gui;

import beans.Magazin;
import beans.Utilizator;
import client.controllers.ClientController;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tableModels.MagazinTableModel;

/**
 *
 * @author Dell
 */
public class MainFrame extends javax.swing.JFrame {
    private Utilizator u;
    private MagazinTableModel model;
    /**
     * Creates new form MainFrame
     */
    public MainFrame(Utilizator u) {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestionare Comenzi");
        this.u=u;
        model =(MagazinTableModel)jTable1.getModel();
        afisareMagazine();
        
         addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent ev){
                        inchidereAplicatie();
                    }
                }       
        );
        
    }
    
     private void inchidereAplicatie(){
        int r = JOptionPane.showConfirmDialog(null, 
                "Esti sigur ca vrei sa inchizi aplicatia?",
                "Intrebare", JOptionPane.YES_NO_OPTION);
        if(r == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    
    public void afisareMagazine(){
        ArrayList<Magazin> magazine = ClientController.getInstance().getMagazine();
        for(Magazin m : magazine){
            model.adaugaMagazin(m);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jTable1.setModel(new MagazinTableModel());
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Sterge Magazin");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salveaza Magazine");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Restaureaza Magazine");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Adauga Comanda");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Afisare Comenzi");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Info");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Modifica Magazin");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jMenu1.setText("Optiuni");

        jMenuItem1.setText("Adauga magazin");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Adauga produs");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Vizualizare produse");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Vizualizare comenzi");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem4.setText("Inchide");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5))
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton7)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        inchidereAplicatie();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        model.adaugaLinieNoua();
        jTable1.revalidate();
        JOptionPane.showMessageDialog(null,"Apasati de doua ori tasta ENTER dupa completarea unui rand pentru salvarea magazinelor in baza de date");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       new AdaugaProdusFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
      new VizualizareProduseFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
     
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          int count=jTable1.getRowCount();
          //System.out.println(count);
          String nume = (String) model.getValueAt(count-1,0);
          String adresa = (String) model.getValueAt(count-1,1);
          ClientController.getInstance().adaugaMagazin(nume, adresa); 
      }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int rand = jTable1.getSelectedRow();
         String adresa = (String) model.getValueAt(rand, 1);
         String nume = (String) model.getValueAt(rand, 0);
         //System.out.println(nume);
         //System.out.println(adresa);
        
       model.stergeLinie(jTable1.getSelectedRow());
       jTable1.revalidate();
       ClientController.getInstance().stergeMagazin(nume,adresa);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
            model.salvare("Magazin.dat");
            model.stergeLinii();
            jTable1.revalidate();
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FileInputStream f1;
        ObjectInputStream in=null;
        model.clear();
        try {
            f1 = new FileInputStream("Magazin.dat");
            in = new ObjectInputStream(f1);
            while(f1.available() != 0) {
                Magazin m = (Magazin)in.readObject();
                model.adaugaMagazin(m);
            }
            jTable1.revalidate();
        }
        
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        finally {
            if(in != null) {
                try{ in.close(); }
                catch(Exception ex) {}
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int rand = jTable1.getSelectedRow();
        String nume = (String)model.getValueAt(rand,0);
        String adresa = (String)model.getValueAt(rand,1);
        Magazin m = ClientController.getInstance().getMagazin(nume,adresa);
        new AdaugaRandProdusFrame(m).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int rand = jTable1.getSelectedRow();
        String nume = (String)model.getValueAt(rand,0);
        String adresa = (String)model.getValueAt(rand,1);
        Magazin m = ClientController.getInstance().getMagazin(nume,adresa);
        new VizualizareRandProdusFrame(m).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       new VizualizareComenzi().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       JOptionPane.showMessageDialog(null,"Folosirea butoanelor \"Sterge magazin\", \"Modifica Magazin\", \"Adauga Comanda\" si \"Afisare Comenzi\""
               + " necesita selectarea unui magazin din tabel.");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      JOptionPane.showMessageDialog(null, "Pentru a se vedea modificarea facuta faceti click inca o data pe numele si pe adresa magazinului selectat!");
       int rand = jTable1.getSelectedRow();
       String nume = (String)model.getValueAt(rand,0);
       String adresa = (String)model.getValueAt(rand,1);
       Magazin m = ClientController.getInstance().getMagazin(nume,adresa);
       String nume1=JOptionPane.showInputDialog("Nume: ");
       String adresa1 = JOptionPane.showInputDialog("Adresa: ");
       if(nume1!=null && adresa1!=null){
           ClientController.getInstance().modificaMagazin(m.getId(),nume1,adresa1);
       }
       model.setValueAt(nume1, rand, 0);
       model.setValueAt(adresa1, rand,1);
       jTable1.revalidate();
       
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
