/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Dobavljac;
import domen.Grad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleDobavljac extends AbstractTableModel {

    List<Dobavljac> lista;
    String[] kolone = {"Dobavljač ID", "Naziv dobavljača", "Telefon", "Matični broj", "PIB", "Adresa ID", "Grad ID", "Država ID"};

    public ModelTabeleDobavljac() {
        lista = new ArrayList<>();
    }

    public ModelTabeleDobavljac(List<Dobavljac> dobavljaci) {
        this.lista = dobavljaci;
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
        Dobavljac a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getDobavljacID();
            case 1:
                return a.getNazivDobavljaca();
            case 2:
                return a.getTelefon();
            case 3:
                return a.getMaticniBroj();
            case 4:
                return a.getPib().getVrednost();
            case 5:
                return a.getAdresaID().getAdresaID();
            case 6:
                return a.getAdresaID().getGrad().getGradID();
            case 7:
                return a.getAdresaID().getGrad().getDrzava().getDrzavaID();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Dobavljac> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Dobavljac> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Dobavljac a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Dobavljac returnObject(int red) {
        return lista.get(red);
    }
}
