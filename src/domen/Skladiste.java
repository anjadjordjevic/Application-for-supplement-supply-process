/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Skladiste {

    private int skladisteID;
    private String nazivSkladista;
    private Grad gradID;

    public Skladiste() {
    }

    public Skladiste(int skladisteID, String nazivSkladista, Grad gradID) {
        this.skladisteID = skladisteID;
        this.nazivSkladista = nazivSkladista;
        this.gradID = gradID;
    }

    public Grad getGradID() {
        return gradID;
    }

    public void setGradID(Grad gradID) {
        this.gradID = gradID;
    }

    public int getSkladisteID() {
        return skladisteID;
    }

    public void setSkladisteID(int skladisteID) {
        this.skladisteID = skladisteID;
    }

    public String getNazivSkladista() {
        return nazivSkladista;
    }

    public void setNazivSkladista(String nazivSkladista) {
        this.nazivSkladista = nazivSkladista;
    }

}
