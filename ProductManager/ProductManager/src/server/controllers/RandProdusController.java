/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;

import beans.Magazin;
import beans.Produs;
import beans.RandProdus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class RandProdusController {
    private Connection con;
    private PreparedStatement ps1,ps2,ps3,ps4;
    
    public RandProdusController(Connection con){
        try {
            this.con = con;
            ps1 = con.prepareStatement("INSERT INTO rand_produse VALUES(NULL,?,?,?)");
            ps2 = con.prepareStatement("SELECT * FROM rand_produse WHERE magazin=?");
            ps3 = con.prepareStatement("SELECT * FROM rand_produse");
            ps4 = con.prepareStatement("DELETE FROM rand_produse WHERE cantitate=? AND produs=?");
        } catch (SQLException ex) {
            Logger.getLogger(RandProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void adaugaRandProdus(double cantitate, int id_produs, int id_magazin){
        try {
            ps1.setDouble(1, cantitate);
            ps1.setInt(2,id_produs);
            ps1.setInt(3, id_magazin);
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RandProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<RandProdus> getRandProduse(Magazin mag){
          ArrayList<RandProdus> rp = new ArrayList<>();
        try {
           
            ps2.setInt(1, mag.getId());
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                rp.add(new RandProdus(rs.getInt("id"),rs.getDouble("cantitate"),rs.getInt("produs"),rs.getInt("magazin")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RandProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rp;
    }
    
    public ArrayList<RandProdus> getRand(){
       ArrayList<RandProdus> rand = new ArrayList<>();
        try {
            
            
            ResultSet rs = ps3.executeQuery();
            while(rs.next()){
                rand.add(new RandProdus(rs.getInt("id"),rs.getDouble("cantitate"),rs.getInt("produs"),rs.getInt("magazin")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RandProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rand;
    }
    
    public void stergeRandProdus(double cantitate, int id_produs){
        try {
            ps4.setDouble(1, cantitate);
            ps4.setInt(2, id_produs);
            ps4.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RandProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
