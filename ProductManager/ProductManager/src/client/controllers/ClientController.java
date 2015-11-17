/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import beans.Magazin;
import beans.Mesaj;
import beans.Produs;
import beans.RandProdus;
import beans.Utilizator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ClientController {
    private static ClientController singleton;
    private Socket socket;
    private ObjectInputStream in ;
    private ObjectOutputStream out;
    private ClientController(){
        try {
            socket = new Socket("localhost",4000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ClientController getInstance(){
        if(singleton==null){
            singleton = new ClientController();
        }
        return singleton;
    }
    
     public boolean inregistrare(String username, String parola){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_INREGISTRARE);
            out.writeObject(m);
            out.writeObject(new Utilizator(username,parola));
            m = (Mesaj)in.readObject();
            if(m.getCod()==Mesaj.RESPONSE_SUCCES)
                return true;
            else
                return false;
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Utilizator conectare(String username, String parola){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_CONECTARE);
            out.writeObject(m);
            out.writeObject(new Utilizator(username,parola));
            Utilizator user = (Utilizator)in.readObject();
            return user;
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void adaugaMagazin(String nume, String adresa){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_ADAUGA_MAGAZIN);
            out.writeObject(m);
            out.writeObject(new Magazin(nume, adresa));
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Magazin> getMagazine(){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_MAGAZINE);
            out.writeObject(m);
            return (ArrayList<Magazin>)in.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Magazin>();
    }
    
     
    public void adaugaProdus(String denumire, double pret){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_ADAUGA_PRODUS);
            out.writeObject(m);
            out.writeObject(new Produs(denumire, pret));
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Produs> getProduse(){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_PRODUSE);
            out.writeObject(m);
            return (ArrayList<Produs>)in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Produs>();
    }
    
    public void stergeMagazin(String nume,String adresa){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_STERGE_MAGAZIN);
            out.writeObject(m);
            out.writeObject(nume);
            out.writeObject(adresa);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stergeProdus(String denumire, double pret){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_STERGE_PRODUS);
            out.writeObject(m);
            out.writeObject(denumire);
            out.writeObject(pret);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Magazin getMagazin(String nume, String adresa){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_MAGAZIN);
            out.writeObject(m);
            out.writeObject(nume);
            out.writeObject(adresa);
            Magazin mag = (Magazin) in.readObject();
            return mag;
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Magazin();
    }
    
    public void adaugaRandProdus(double cantitate, int id_produs, int  id_magazin){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_ADAUGA_RAND_PRODUS);
            out.writeObject(m);
            out.writeObject(new RandProdus(cantitate, id_produs, id_magazin));
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<RandProdus> getRandProduse(Magazin magazin){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_RAND_PRODUSE);
            out.writeObject(m);
            out.writeObject(magazin);
            return (ArrayList<RandProdus>)in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<RandProdus>();
    }
    
    public ArrayList<RandProdus> getRand(){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_RAND);
            out.writeObject(m);
            return (ArrayList<RandProdus>)in.readObject();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<RandProdus>();
    }
    
    public Produs getProdus(int id){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_PRODUS);
            out.writeObject(m);
            out.writeObject(id); 
            return (Produs)in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Magazin getMagazin(int id){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_GET_MAGAZIN_ID);
            out.writeObject(m);
            out.writeObject(id); 
            return (Magazin)in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void stergeRandProdus(double cantitate, int id_produs){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_STERGE_RAND_PRODUS);
            out.writeObject(m);
            out.writeObject(cantitate);
            out.writeObject(id_produs);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificaMagazin(int id, String nume, String adresa){
        try {
            Mesaj m = new Mesaj(Mesaj.REQUEST_MODIFICA_MAGAZIN);
            out.writeObject(m);
            out.writeObject(id);
            out.writeObject(nume);
            out.writeObject(adresa);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
