/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class RandProdus implements Serializable{
    private int id;
    private double cantitate;
    private int id_produs;
    private int id_magazin;
    private String descriere;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantitate() {
        return cantitate;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }
    
     public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getId_magazin() {
        return id_magazin;
    }

    public void setId_magazin(int id_magazin) {
        this.id_magazin = id_magazin;
    }
    
    public RandProdus() {
    }


    public RandProdus(int id, double cantitate, int id_produs, int id_magazin) {
        this.id = id;
        this.cantitate = cantitate;
        this.id_produs = id_produs;
        this.id_magazin = id_magazin;
    }

    public RandProdus(double cantitate, int id_produs, int id_magazin) {
        this.cantitate = cantitate;
        this.id_produs = id_produs;
        this.id_magazin = id_magazin;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    

    @Override
    public String toString() {
        return descriere;
    }

   
    
}
