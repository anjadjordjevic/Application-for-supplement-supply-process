/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import domen.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleRacun extends AbstractTableModel {

    List<Racun> lista;
    String[] kolone = {"Broj računa", "Datum računa", "Datum plaćanja", "Datum prometa", "Dobavljač ID", "Skladište ID",
        "Otpremnica ID", "Radnik ID", "Grad ID", "Ukupno"};

    public ModelTabeleRacun() {
        lista = new ArrayList<>();
    }

    public ModelTabeleRacun(List<Racun> r) {
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
        Racun a = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        switch (columnIndex) {
            case 0:
                return a.getBrojRacuna();
            case 1:
                return sdf.format(a.getDatumRacuna());
            case 2:
                return sdf.format(a.getDatumPlacanja());
            case 3:
                return sdf.format(a.getDatumPrometa());
            case 4:
                return a.getDobavljacID().getDobavljacID();
            case 5:
                return a.getSkladisteID().getSkladisteID();
            case 6:
                return a.getOtpremnicaID().getBrojOtpremnice();
            case 7:
                return a.getRadnikID().getRadnikID();
            case 8:
                return a.getGradID().getGradID();
            case 9:
                return a.getUkupno();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Racun> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Racun a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Racun returnObject(int red) {
        return lista.get(red);
    }

}
