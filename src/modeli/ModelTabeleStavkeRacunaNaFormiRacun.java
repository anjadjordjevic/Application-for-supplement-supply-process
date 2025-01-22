/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleStavkeRacunaNaFormiRacun extends AbstractTableModel {

    List<StavkaRacuna> lista;
    String[] kolone = {"Stavka računa ID", "Artikal ID", "Cena artikla", "Količina"};

    public ModelTabeleStavkeRacunaNaFormiRacun() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkeRacunaNaFormiRacun(List<StavkaRacuna> r) {
        this.lista = r;
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
        StavkaRacuna a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getStavkaRacunaID();
            case 1:
                return a.getArtikalID().getArtikalID();
            case 2:
                return a.getCenaArtikla();
            case 3:
                return a.getKolicina();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaRacuna> getLista() {
        return lista;
    }

    public void setLista(ArrayList<StavkaRacuna> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(StavkaRacuna a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public StavkaRacuna returnObject(int red) {
        return lista.get(red);
    }

}
