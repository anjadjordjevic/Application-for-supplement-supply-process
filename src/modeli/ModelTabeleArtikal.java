/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import domen.Artikal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleArtikal extends AbstractTableModel {

    List<Artikal> lista;
    String[] kolone = {"Artikal ID", "Naziv", "Pakovanje", "Kvalitet", "Naziv JM", "Poreska stopa ID", "Aktuelna cena"};

    public ModelTabeleArtikal() {
        lista = new ArrayList<>();
    }

    public ModelTabeleArtikal(List<Artikal> a) {
        this.lista = a;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artikal a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getArtikalID();
            case 1:
                return a.getNaziv();
            case 2:
                return a.getPakovanje();
            case 3:
                return a.getKvalitet();
            case 4:
                return a.getNazivJM();
            case 5:
                return a.getStopaID().getStopaID();
            case 6:
                return a.getAktuelnaCena();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Artikal> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Artikal> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Artikal a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Artikal returnObject(int red) {
        return lista.get(red);
    }

}
