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
public class Produs implements Serializable {
    private int id;
    private String denumire;
    private double pret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public Produs() {
    }

    public Produs(int id, String denumire, double pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public Produs(String denumire, double pret) {
        this.denumire = denumire;
        this.pret = pret;
    }

    @Override
    public String toString() {
        return denumire+" "+pret+ " lei";
    }
    
    
}
