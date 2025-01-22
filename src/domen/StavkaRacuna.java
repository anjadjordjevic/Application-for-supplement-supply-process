/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class StavkaRacuna {

    private Racun racunID;
    private int stavkaRacunaID;
    private double cenaArtikla;
    private int kolicina;
    private Artikal artikalID;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racunID, int stavkaRacunaID, double iznos, int kolicina, Artikal artikalID) {
        this.racunID = racunID;
        this.stavkaRacunaID = stavkaRacunaID;
        this.cenaArtikla = iznos;
        this.kolicina = kolicina;
        this.artikalID = artikalID;
    }

    public Artikal getArtikalID() {
        return artikalID;
    }

    public void setArtikalID(Artikal artikalID) {
        this.artikalID = artikalID;
    }

    public Racun getRacunID() {
        return racunID;
    }

    public void setRacunID(Racun racunID) {
        this.racunID = racunID;
    }

    public int getStavkaRacunaID() {
        return stavkaRacunaID;
    }

    public void setStavkaRacunaID(int stavkaRacunaID) {
        this.stavkaRacunaID = stavkaRacunaID;
    }

    public double getCenaArtikla() {
        return cenaArtikla;
    }

    public void setCenaArtikla(double cenaArtikla) {
        this.cenaArtikla = cenaArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

}
