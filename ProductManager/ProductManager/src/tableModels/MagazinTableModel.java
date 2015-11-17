/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import beans.Magazin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class MagazinTableModel extends AbstractTableModel {
    
    private List<Magazin> lista = new ArrayList<>();
    private List<String> coloane = new ArrayList<>();

    public MagazinTableModel() {
        coloane.add("Nume");
        coloane.add("Adresa");
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return coloane.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Magazin magazin = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return magazin.getNume();
            case 1:
                return magazin.getAdresa();
            default:
                return null;
        }
    }
    
     @Override
    public String getColumnName(int column) {
        return coloane.get(column);
    }
     
    public void adaugaMagazin(Magazin m) {
        
        lista.add(m);
    }
    
    public void adaugaLinieNoua() {
        lista.add(new Magazin());
    }
   
    public void stergeLinie(int indexMagazin) {
        if(indexMagazin >= 0 && indexMagazin < lista.size()) {
            lista.remove(indexMagazin);
        }
    }
    
    public void stergeLinii(){
        if(lista.size()>0){
            lista.removeAll(lista);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Magazin magazin = lista.get(rowIndex);
        
         switch(columnIndex) {
            case 0:
                magazin.setNume(aValue.toString());
                break;
            case 1:
                magazin.setAdresa(aValue.toString());
                break;   
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
         
        switch(columnIndex) {
            case 0:
            case 1:
                return String.class;
            default:
                return super.getColumnClass(columnIndex);
        }
    }
 
    public void salvare(String fisier) throws Exception {
        File f = new File(fisier);
        this.salvare(f);
    }
    
    public void salvare(File fisier) throws Exception {
        
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fisier));
            for(Magazin c:lista) {
                out.writeObject(c);
            }
            out.close();
        
       
    }
    
    public void clear() {
        lista.clear();
    }
    
    
    
}
