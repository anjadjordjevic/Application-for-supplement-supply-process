/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleAdresa extends AbstractTableModel {

    List<Adresa> lista;
    String[] kolone = {"Adresa ID", "Država ID", "Grad ID", "Ulica", "Broj", "Naziv grada"};

    public ModelTabeleAdresa() {
        lista = new ArrayList<>();
    }

    public ModelTabeleAdresa(List<Adresa> adrese) {
        this.lista = adrese;
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
        Adresa a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getAdresaID();
            case 1:
                return a.getGrad().getDrzava().getDrzavaID();
            case 2:
                return a.getGrad().getGradID();
            case 3:
                return a.getUlica();
            case 4:
                return a.getBroj();
            case 5:
                return a.getGrad().getNazivGrada();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Adresa> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Adresa> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Adresa a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Adresa returnObject(int red) {
        return lista.get(red);
    }

}
