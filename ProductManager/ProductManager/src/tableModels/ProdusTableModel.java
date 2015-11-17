/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import beans.Magazin;
import beans.Produs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class ProdusTableModel extends AbstractTableModel {
     private List<Produs> lista = new ArrayList<>();
    private List<String> coloane = new ArrayList<>();

    public ProdusTableModel() {
        coloane.add("Denumire");
        coloane.add("Pret");
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
        Produs produs = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return produs.getDenumire();
            case 1:
                return produs.getPret();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        return coloane.get(column);
    }
     
    public void adaugaProdus(Produs p) {
        
        lista.add(p);
    }
    
    public void adaugaLinieNoua() {
        lista.add(new Produs());
    }
   
    public void stergeLinie(int indexMagazin) {
        if(indexMagazin >= 0 && indexMagazin < lista.size()) {
            lista.remove(indexMagazin);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Produs p = lista.get(rowIndex);
        
         switch(columnIndex) {
            case 0:
                p.setDenumire(aValue.toString());
                break;
            case 1:
                p.setPret(Double.parseDouble(aValue.toString()));
                break;   
        }
    }
}
 