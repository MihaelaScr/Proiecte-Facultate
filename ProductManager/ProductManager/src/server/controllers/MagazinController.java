/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;

import beans.Magazin;
import beans.Utilizator;
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
public class MagazinController {
    private Connection con;
    private PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6;
    
    public MagazinController(Connection con){
        try {
            this.con = con;
            ps1 = con.prepareStatement("SELECT * FROM magazine");
            ps2 = con.prepareStatement("INSERT INTO magazine VALUES (NULL,?,?)");
            ps3 = con.prepareStatement("DELETE FROM magazine WHERE nume=? AND adresa=?");
            ps4 = con.prepareStatement("SELECT * FROM magazine WHERE nume=? AND adresa=?");
            ps5 = con.prepareStatement("SELECT * FROM magazine WHERE id=?");
            ps6 = con.prepareStatement("UPDATE magazine SET nume=?, adresa=? WHERE id=?");
        } catch (SQLException ex) {
            Logger.getLogger(MagazinController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
     public void adaugaMagazin(String nume,String adresa){
        try {
            ps2.setString(1, nume);
            ps2.setString(2, adresa);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public ArrayList<Magazin> getMagazine(){
        ArrayList<Magazin> magazine = new ArrayList<>();
        try {
            ResultSet rs = ps1.executeQuery();
            
            while(rs.next()){
                magazine.add(new Magazin(rs.getInt("id"), rs.getString("nume"), rs.getString("adresa")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return magazine;
    }
     
    public void stergeMagazin(String nume, String adresa){
        try {
            ps3.setString(1, nume);
            ps3.setString(2, adresa);
            ps3.executeUpdate();;
        } catch (SQLException ex) {
            Logger.getLogger(MagazinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Magazin getMagazin(String nume, String adresa){
        Magazin mag = new Magazin();
        try {
            ps4.setString(1,nume);
            ps4.setString(2,adresa);
            ResultSet rs = ps4.executeQuery();
            while(rs.next())
             mag= new Magazin(rs.getInt("id"),rs.getString("nume"),rs.getString("adresa"));
            
        } catch (SQLException ex) {
            Logger.getLogger(MagazinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mag;
    }
    
    public Magazin getMagazin(int id){
        Magazin m = null;
        try {
            ps5.setInt(1, id);
            ResultSet rs = ps5.executeQuery();
            if(rs.next()){
                m = new Magazin(rs.getInt("id"), rs.getString("nume"),rs.getString("adresa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MagazinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public void modificaMagazin(int id, String nume, String adresa){
        try {
            ps6.setString(1, nume);
            ps6.setString(2, adresa);
            ps6.setInt(3, id);
            ps6.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MagazinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
