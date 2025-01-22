/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Drzava {

    private int drzavaID;
    private String nazivDrzave;

    public Drzava() {
    }

    public Drzava(int drzavaID, String nazivDrzave) {
        this.drzavaID = drzavaID;
        this.nazivDrzave = nazivDrzave;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }
/*
    @Override
    public String toString() {
        return drzavaID + "";
    }*/

}
