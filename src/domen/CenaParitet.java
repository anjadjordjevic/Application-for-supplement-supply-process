/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import tipovi.CenaParitetTip;

/**
 *
 * @author Korisnik
 */
public class CenaParitet {
    private Artikal artikalID;
    private int cenaParitetID;
    private String naziv;
    private CenaParitetTip cena;

    public CenaParitet() {
    }

    public CenaParitet(Artikal artikalID, int cenaParitetID, String naziv, CenaParitetTip cena) {
        this.artikalID = artikalID;
        this.cenaParitetID = cenaParitetID;
        this.naziv = naziv;
        this.cena = cena;
    }

    public CenaParitetTip getCena() {
        return cena;
    }

    public void setCena(CenaParitetTip cena) {
        this.cena = cena;
    }

    public Artikal getArtikalID() {
        return artikalID;
    }

    public void setArtikalID(Artikal artikalID) {
        this.artikalID = artikalID;
    }

    public int getCenaParitetID() {
        return cenaParitetID;
    }

    public void setCenaParitetID(int cenaParitetID) {
        this.cenaParitetID = cenaParitetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
