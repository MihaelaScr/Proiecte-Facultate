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
public class Utilizator implements Serializable{
    private int id;
    private String username; 
    private String parola;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Utilizator() {
    }

    public Utilizator(int id, String username, String parola) {
        this.id = id;
        this.username = username;
        this.parola = parola;
    }

    public Utilizator(String username, String parola) {
        this.username = username;
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Utilizatorul cu id-ul " + id + ", username-ul " + username + ", parola "+parola;
    }
    
    
}
