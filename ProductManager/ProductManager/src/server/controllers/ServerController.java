/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;

import beans.Magazin;
import beans.Produs;
import beans.RandProdus;
import beans.Utilizator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ServerController {
    private static ServerController singleton;
    private UserController userController;
    private MagazinController magazinController;
    private ProdusController produsController;
    private RandProdusController randProdusController;

    private ServerController(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/proiectjava",
                    "root","");
            userController = new UserController(con);
            magazinController = new MagazinController(con);
            produsController = new ProdusController(con);
            randProdusController = new RandProdusController(con);
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServerController getInstance(){
        if(singleton==null){
            singleton = new ServerController();
        }
        return singleton;
    }
    
    public boolean inregistrare(String username,String parola){
        Utilizator user = userController.getUserByUsername(username);
        
        if(user == null){
            //parola = CryptStrings.ecryptWithMD5(parola);
            userController.adaugaUser(username, parola);
            return true;
        }else{
            return false;
        }
    }
    
    public Utilizator conectare(String username,String parola){
        Utilizator user = userController.getUserByUsername(username);
        
        if(user != null){
            if(user.getParola().equals(parola)){
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    public void adaugaMagazin(String nume, String adresa){
        magazinController.adaugaMagazin(nume, adresa);
    }
    
    public ArrayList<Magazin> getMagazine(){
        return magazinController.getMagazine();
    }
    
    public void adaugaProdus(String denumire,double pret){
        produsController.adaugaProdus(denumire, pret);
    }
    
    public ArrayList<Produs> getProduse(){
        return produsController.getProduse();
    }
    
    public void stergeMagazin(String nume, String adresa){
        magazinController.stergeMagazin(nume,adresa);
    }
    
    public void stergeProdus(String denumire, double pret){
        produsController.stergeProdus(denumire, pret);
    }
    
    public Magazin getMagazin(String nume, String adresa){
        return magazinController.getMagazin(nume, adresa);
    }
    
    public void adaugaRandProdus(double cantitate, int id_produs, int id_magazin){
        randProdusController.adaugaRandProdus(cantitate,id_produs,id_magazin);
    }

    public ArrayList<RandProdus> getRandProduse(Magazin magazin){
        ArrayList<RandProdus> randuri = randProdusController.getRandProduse(magazin);
        for(RandProdus rp: randuri){
            Produs produs = produsController.getProdus(rp.getId_produs());
            rp.setDescriere("Produs: "+produs.getDenumire()+" Cantitate: "+rp.getCantitate());
        }
        return randuri;
    }
    
    public ArrayList<RandProdus> getRand(){
       ArrayList<RandProdus> randuri =  randProdusController.getRand();     
        return randuri;
    }
    
    public Produs getProdus(int id){
        return produsController.getProdus(id);
    }
    
    public Magazin getMagazin(int id){
        return magazinController.getMagazin(id);
    }
    
    public void stergeRandProdus(double cantitate, int id_produs){
        randProdusController.stergeRandProdus(cantitate, id_produs);
    }
    
    public void modificaMagazin(int id, String nume, String adresa){
        magazinController.modificaMagazin(id,nume,adresa);
    }
}