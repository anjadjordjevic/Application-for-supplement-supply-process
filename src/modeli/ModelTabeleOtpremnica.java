/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Adresa;
import domen.Otpremnica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleOtpremnica extends AbstractTableModel {

    List<Otpremnica> lista;
    String[] kolone = {"Broj otpremnice", "Datum dokumenta", "Datum valute", "Šifra komitenta",
        "Porudžbenica ID", "Radnik ID", "Dobavljač ID", "Naziv dobavljača"};

    public ModelTabeleOtpremnica() {
        lista = new ArrayList<>();
    }

    public ModelTabeleOtpremnica(List<Otpremnica> otpremnice) {
        this.lista = otpremnice;
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
        Otpremnica a = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        switch (columnIndex) {
            case 0:
                return a.getBrojOtpremnice();
            case 1:
                return sdf.format(a.getDatumDokumenta());
            case 2:
                return sdf.format(a.getDatumValute());
            case 3:
                return a.getSifraKomitenta();
            case 6:
                return a.getDobavljac().getDobavljacID();
            case 4:
                return a.getPorudzbenica().getPorudzbenicaID();
            case 5:
                return a.getRadnik().getRadnikID();
            case 7:
                return a.getDobavljac().getNazivDobavljaca();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Otpremnica> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Otpremnica> novaLista) {
        lista = novaLista;
        fireTableDataChanged();
    }

    public void add(Otpremnica a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void remove(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public Otpremnica returnObject(int red) {
        return lista.get(red);
    }

}
