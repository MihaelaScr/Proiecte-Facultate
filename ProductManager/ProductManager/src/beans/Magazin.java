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
public class Magazin implements Serializable{
    private int id;
    private String nume;
    private String adresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Magazin() {
    }

    public Magazin(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
    }

    public Magazin(String nume, String adresa) {
        this.nume = nume;
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Magazinul cu id-ul "+id+", numele "+nume+", adresa "+adresa;
    }
    
    
}
