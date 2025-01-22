/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.CenaParitet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleCenaParitet extends AbstractTableModel {

    List<CenaParitet> lista;
    String[] kolone = {"Artikal ID", "Cena paritet ID", "Naziv", "Cena"};

    public ModelTabeleCenaParitet() {
        lista = new ArrayList<>();
    }

    public ModelTabeleCenaParitet(List<CenaParitet> c) {
        this.lista = c;
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
        CenaParitet a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getArtikalID().getArtikalID();
            case 1:
                return a.getCenaParitetID();
            case 2:
                return a.getNaziv();
            case 3:
                return a.getCena();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<CenaParitet> getLista() {
        return lista;
    }

    public void setLista(ArrayList<CenaParitet> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(CenaParitet a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public CenaParitet returnObject(int red) {
        return lista.get(red);
    }

}
