/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;

import beans.Utilizator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class UserController {
    private Connection con;
    private PreparedStatement ps1,ps2,ps3;
    
    public UserController(Connection con) {
        this.con = con;
        try {
            ps1 = con.prepareStatement("SELECT * FROM utilizatori WHERE username = ?");
            ps2 = con.prepareStatement("INSERT INTO utilizatori VALUES (NULL,?,?)");
            ps3 = con.prepareStatement("SELECT * FROM utilizatori WHERE id = ?");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Utilizator getUserByUsername(String username){
        try {
            ps1.setString(1, username);
            ResultSet rs = ps1.executeQuery();
            
            if(rs.next()){
                return new Utilizator(rs.getInt("id"), rs.getString("username"), rs.getString("parola"));
            }else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public void adaugaUser(String username,String parola){
        try {
            ps2.setString(1, username);
            ps2.setString(2, parola);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Utilizator getUser(int id){
        Utilizator u = null;
        try {
            ps3.setInt(1, id);
            ResultSet rs = ps3.executeQuery();
            if(rs.next()){
                u = new Utilizator(rs.getInt("id"), rs.getString("username"), rs.getString("parola"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
