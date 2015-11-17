/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;

import beans.Magazin;
import beans.Produs;
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
public class ProdusController {
    private Connection con;
    private PreparedStatement ps1,ps2,ps3,ps4;
    
    public ProdusController(Connection con){
        try {
            this.con = con;
            ps1 = con.prepareStatement("SELECT * FROM produse");
            ps2 = con.prepareStatement("INSERT INTO produse VALUES (NULL,?,?)");
            ps3 = con.prepareStatement("DELETE FROM produse WHERE denumire=? AND pret=?");
            ps4 = con.prepareStatement("SELECT * FROM produse WHERE id=?");
        } catch (SQLException ex) {
            Logger.getLogger(ProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void adaugaProdus(String denumire,double pret){
        try {
            ps2.setString(1, denumire);
            ps2.setDouble(2, pret);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<>();
        try {
            ResultSet rs = ps1.executeQuery();
            
            while(rs.next()){
                produse.add(new Produs(rs.getInt("id"), rs.getString("denumire"), rs.getDouble("pret")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return produse;
    }
    
    public void stergeProdus(String denumire, double pret){
        try {
            ps3.setString(1, denumire);
            ps3.setDouble(2, pret);
            ps3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Produs getProdus(int id){
        Produs p = null;
        try {
            ps4.setInt(1, id);
            ResultSet rs = ps4.executeQuery();
            if(rs.next()){
               p = new Produs(rs.getInt("id"), rs.getString("denumire"), rs.getDouble("pret"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
     
}
