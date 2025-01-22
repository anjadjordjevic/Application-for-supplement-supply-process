/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Grad {

    private Drzava drzava;
    private int gradID;
    private String nazivGrada;

    public Grad() {
    }

    public Grad(Drzava drzavaID, int gradID, String nazivGrada) {
        this.drzava = drzavaID;
        this.gradID = gradID;
        this.nazivGrada = nazivGrada;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }
/*
    @Override
    public String toString() {
        return nazivGrada;
    }
*/
}
