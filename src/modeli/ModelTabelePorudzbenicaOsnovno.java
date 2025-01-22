/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.PorudzbenicaOsnovno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePorudzbenicaOsnovno extends AbstractTableModel{
     
    List<PorudzbenicaOsnovno> lista;
    String[] kolone = {"Porudžbenica ID", "Datum"};

    public ModelTabelePorudzbenicaOsnovno() {
        lista = new ArrayList<>();
    }

    public ModelTabelePorudzbenicaOsnovno(List<PorudzbenicaOsnovno> adrese) {
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
        PorudzbenicaOsnovno a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getPorudzbenicaID();
            case 1:
                return a.getDatum();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<PorudzbenicaOsnovno> getLista() {
        return lista;
    }

    public void setLista(ArrayList<PorudzbenicaOsnovno> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(PorudzbenicaOsnovno a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public PorudzbenicaOsnovno returnObject(int red) {
        return lista.get(red);
    }
}
