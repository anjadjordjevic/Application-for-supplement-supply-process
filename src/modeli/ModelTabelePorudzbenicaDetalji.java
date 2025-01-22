/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.PorudzbenicaDetalji;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePorudzbenicaDetalji extends AbstractTableModel {
    
    List<PorudzbenicaDetalji> lista;
    String[] kolone = {"Porudžbenica ID", "Radnik ID", "Dobavljač ID"};

    public ModelTabelePorudzbenicaDetalji() {
        lista = new ArrayList<>();
    }

    public ModelTabelePorudzbenicaDetalji(List<PorudzbenicaDetalji> adrese) {
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
        PorudzbenicaDetalji a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getPorudzbenicaID();
            case 1:
                return a.getRadnikID().getRadnikID();
            case 2:
                return a.getDobavljacID().getDobavljacID();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<PorudzbenicaDetalji> getLista() {
        return lista;
    }

    public void setLista(ArrayList<PorudzbenicaDetalji> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(PorudzbenicaDetalji a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public PorudzbenicaDetalji returnObject(int red) {
        return lista.get(red);
    }

}
