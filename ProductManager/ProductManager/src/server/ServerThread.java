/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.Magazin;
import beans.Mesaj;
import beans.Produs;
import beans.RandProdus;
import beans.Utilizator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controllers.ServerController;

/**
 *
 * @author Dell
 */
public class ServerThread extends Thread{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public ServerThread(Socket socket){
        try {
            this.socket=socket;
            in =  new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        try{
            while(true){
                Mesaj m = (Mesaj) in.readObject();
                switch(m.getCod()){
                    case Mesaj.REQUEST_INREGISTRARE:{
                        Utilizator u = (Utilizator)in.readObject();
                        boolean r=ServerController.getInstance().inregistrare(u.getUsername(), u.getParola());
                        if(r){
                            out.writeObject(new Mesaj(Mesaj.RESPONSE_SUCCES));
                        }else{
                            out.writeObject(new Mesaj(Mesaj.RESPONSE_FAIL));
                        }
                        
                    }break;
                        
                    case Mesaj.REQUEST_CONECTARE:{
                        Utilizator u = (Utilizator)in.readObject();
                        u = ServerController.getInstance().conectare(u.getUsername(), u.getParola());
                        out.writeObject(u);
                    }break;
                        
                    case Mesaj.REQUEST_ADAUGA_MAGAZIN:{
                        Magazin mag = (Magazin)in.readObject();
                        ServerController.getInstance().adaugaMagazin(mag.getNume(), mag.getAdresa());
                    }break;
                        
                    case Mesaj.REQUEST_GET_MAGAZINE:{
                        out.writeObject(ServerController.getInstance().getMagazine());
                    }break;
                        
                    case Mesaj.REQUEST_ADAUGA_PRODUS:{
                        Produs p = (Produs)in.readObject();
                        ServerController.getInstance().adaugaProdus(p.getDenumire(), p.getPret());
                    }break;
        
                    case Mesaj.REQUEST_GET_PRODUSE:{
                        out.writeObject(ServerController.getInstance().getProduse()); 
                    }break;
                        
                    case Mesaj.REQUEST_STERGE_MAGAZIN:{
                        String nume = (String)in.readObject();
                        String adresa = (String)in.readObject();
                        ServerController.getInstance().stergeMagazin(nume,adresa);
                    }break;
                        
                         
                    case Mesaj.REQUEST_STERGE_PRODUS:{
                        String denumire = (String)in.readObject();
                        double pret = (double)in.readObject();
                        ServerController.getInstance().stergeProdus(denumire,pret);
                    }break;
                        
                    case Mesaj.REQUEST_GET_MAGAZIN:{
                       String nume = (String)in.readObject();
                       String adresa = (String) in.readObject();
                       Magazin mag = ServerController.getInstance().getMagazin(nume, adresa);
                       out.writeObject(mag);
                    }break;
                        
                    case Mesaj.REQUEST_ADAUGA_RAND_PRODUS:{
                        RandProdus rp = (RandProdus)in.readObject();
                        ServerController.getInstance().adaugaRandProdus(rp.getCantitate(),rp.getId_produs(),rp.getId_magazin());
                    }break;
                        
                    case Mesaj.REQUEST_GET_RAND_PRODUSE:{
                        Magazin magazin = (Magazin)in.readObject();
                        out.writeObject(ServerController.getInstance().getRandProduse(magazin));
                    }break;
                        
                    case Mesaj.REQUEST_GET_RAND:{
                        out.writeObject(ServerController.getInstance().getRand());
                    }break;
                        
                    case Mesaj.REQUEST_GET_PRODUS:{
                        int id = (int)in.readObject();
                        out.writeObject(ServerController.getInstance().getProdus(id)); 
                    }break;
                        
                    case Mesaj.REQUEST_GET_MAGAZIN_ID:{
                        int id = (int)in.readObject();
                        out.writeObject(ServerController.getInstance().getMagazin(id)); 
                    }break;
                        
                    case Mesaj.REQUEST_STERGE_RAND_PRODUS:{
                        double cantitate = (double)in.readObject();
                        int id_produs = (int)in.readObject();
                        ServerController.getInstance().stergeRandProdus(cantitate, id_produs);
                    }break;
                        
                    case Mesaj.REQUEST_MODIFICA_MAGAZIN:{
                        int id = (int)in.readObject();
                        String nume = (String)in.readObject();
                        String adresa = (String) in.readObject();
                        ServerController.getInstance().modificaMagazin(id,nume,adresa);
                    }break;
                }
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
