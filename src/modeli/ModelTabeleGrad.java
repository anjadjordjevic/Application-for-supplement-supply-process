/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import domen.Grad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleGrad extends AbstractTableModel {

    List<Grad> lista;
    String[] kolone = {"Grad ID", "Naziv grada", "Država ID"};

    public ModelTabeleGrad() {
        lista = new ArrayList<>();
    }

    public ModelTabeleGrad(List<Grad> gradovi) {
        this.lista = gradovi;
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
        Grad a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getGradID();
            case 1:
                return a.getNazivGrada();
            case 2:
                return a.getDrzava().getDrzavaID();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Grad> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Grad> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Grad a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Grad returnObject(int red) {
        return lista.get(red);
    }

}
