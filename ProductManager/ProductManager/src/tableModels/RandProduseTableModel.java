/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import beans.Magazin;
import beans.Produs;
import beans.RandProdus;
import client.controllers.ClientController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class RandProduseTableModel extends AbstractTableModel{
    private List<RandProdus> lista = new ArrayList<>();
    private List<String> coloane = new ArrayList<>();

    public RandProduseTableModel() {
        coloane.add("Cantitate");
        coloane.add("Produs");
        coloane.add("Magazin");
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
        RandProdus rp = lista.get(rowIndex);
        Produs p = ClientController.getInstance().getProdus(rp.getId_produs());
        Magazin m = ClientController.getInstance().getMagazin(rp.getId_magazin());
        switch(columnIndex) {
            case 0:
                return rp.getCantitate();
            case 1:
                return p.getDenumire();
            case 2: 
                return m.getNume();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        return coloane.get(column);
    }
     
    public void adaugaRandProdus(RandProdus rp) {
        
        lista.add(rp);
    }
    
    public void adaugaLinieNoua() {
        lista.add(new RandProdus());
    }
   
    public void stergeLinie(int indexRandProdus) {
        if(indexRandProdus >= 0 && indexRandProdus < lista.size()) {
            lista.remove(indexRandProdus);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RandProdus rp = lista.get(rowIndex);
         switch(columnIndex) {
            case 0:
                rp.setCantitate(Double.parseDouble(aValue.toString()));
                break;
            case 1:
                rp.setId_produs(Integer.parseInt(aValue.toString()));
                break;  
            case 2:
                rp.setId_magazin(Integer.parseInt(aValue.toString())); 
        }
    }
}
