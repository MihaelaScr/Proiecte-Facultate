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
public class Mesaj implements Serializable{
    public static final int REQUEST_INREGISTRARE=1;
    public static final int REQUEST_CONECTARE=2;
    public static final int RESPONSE_SUCCES=3;
    public static final int RESPONSE_FAIL=4;
    public static final int REQUEST_ADAUGA_MAGAZIN = 5;
    public static final int REQUEST_ADAUGA_PRODUS = 6;
    public static final int REQUEST_ADAUGA_RAND_PRODUS = 7;
    public static final int REQUEST_GET_PRODUSE = 8;
    public static final int REQUEST_GET_MAGAZINE = 9;
    public static final int REQUEST_STERGE_MAGAZIN = 10;
    public static final int REQUEST_STERGE_PRODUS = 11;
    public static final int REQUEST_GET_MAGAZIN = 12;
    public static final int REQUEST_GET_RAND_PRODUSE = 13;
    public static final int REQUEST_GET_RAND = 14;
    public static final int REQUEST_GET_PRODUS = 15;
    public static final int REQUEST_GET_MAGAZIN_ID = 16;
    public static final int REQUEST_STERGE_RAND_PRODUS = 17;
    public static final int REQUEST_MODIFICA_MAGAZIN = 18;
    
    
    private int cod;
    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Mesaj() {
    }

    public Mesaj(int cod) {
        this.cod = cod;
    }
    
    
    
}
