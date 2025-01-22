/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import domen.Skladiste;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleSkladiste extends AbstractTableModel {

    List<Skladiste> lista;
    String[] kolone = {"Skladište ID", "Naziv skladišta", "Grad ID"};

    public ModelTabeleSkladiste() {
        lista = new ArrayList<>();
    }

    public ModelTabeleSkladiste(List<Skladiste> s) {
        this.lista = s;
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
        Skladiste a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getSkladisteID();
            case 1:
                return a.getNazivSkladista();
            case 2:
                return a.getGradID().getGradID();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Skladiste> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Skladiste> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Skladiste a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Skladiste returnObject(int red) {
        return lista.get(red);
    }

}
