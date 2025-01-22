/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import tipovi.Pib;

/**
 *
 * @author Korisnik
 */
public class Dobavljac {

    private int dobavljacID;
    private String nazivDobavljaca;
    private String telefon;
    private String maticniBroj;
    private Pib pib;
    private Adresa adresaID;

    public Dobavljac() {
    }

    public Dobavljac(int dobavljacID, String nazivDobavljaca, String telefon, String maticniBroj, Pib pib, Adresa adresaID) {
        this.dobavljacID = dobavljacID;
        this.nazivDobavljaca = nazivDobavljaca;
        this.telefon = telefon;
        this.maticniBroj = maticniBroj;
        this.pib = pib;
        this.adresaID = adresaID;
    }

    public int getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(int dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public Pib getPib() {
        return pib;
    }

    public void setPib(Pib pib) {
        this.pib = pib;
    }

    public Adresa getAdresaID() {
        return adresaID;
    }

    public void setAdresaID(Adresa adresaID) {
        this.adresaID = adresaID;
    }

}
