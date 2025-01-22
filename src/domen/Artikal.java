/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Artikal {

    private int artikalID;
    private String naziv;
    private String pakovanje;
    private int kvalitet;
    private String nazivJM;
    private PoreskaStopa stopaID;
    private double aktuelnaCena;

    public Artikal() {
    }

    public Artikal(int artikalID, String naziv, String pakovanje, int kvalitet, String nazivJM, PoreskaStopa stopaID, double aktuelnaCena) {
        this.artikalID = artikalID;
        this.naziv = naziv;
        this.pakovanje = pakovanje;
        this.kvalitet = kvalitet;
        this.nazivJM = nazivJM;
        this.stopaID = stopaID;
        this.aktuelnaCena = aktuelnaCena;
    }

    public PoreskaStopa getStopaID() {
        return stopaID;
    }

    public void setStopaID(PoreskaStopa stopaID) {
        this.stopaID = stopaID;
    }

    public int getArtikalID() {
        return artikalID;
    }

    public void setArtikalID(int artikalID) {
        this.artikalID = artikalID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPakovanje() {
        return pakovanje;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public int getKvalitet() {
        return kvalitet;
    }

    public void setKvalitet(int kvalitet) {
        this.kvalitet = kvalitet;
    }

    public double getAktuelnaCena() {
        return aktuelnaCena;
    }

    public void setAktuelnaCena(double aktuelnaCena) {
        this.aktuelnaCena = aktuelnaCena;
    }

    public String getNazivJM() {
        return nazivJM;
    }

    public void setNazivJM(String nazivJM) {
        this.nazivJM = nazivJM;
    }

}
