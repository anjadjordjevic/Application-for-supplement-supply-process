/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class PorudzbenicaDetalji {

    private int porudzbenicaID;
    private Radnik radnikID;
    private Dobavljac dobavljacID;

    public PorudzbenicaDetalji() {
    }

    public PorudzbenicaDetalji(int porudzbenicaID, Radnik radnikID, Dobavljac dobavljacID) {
        this.porudzbenicaID = porudzbenicaID;
        this.radnikID = radnikID;
        this.dobavljacID = dobavljacID;
    }

    public int getPorudzbenicaID() {
        return porudzbenicaID;
    }

    public void setPorudzbenicaID(int porudzbenicaID) {
        this.porudzbenicaID = porudzbenicaID;
    }

    public Radnik getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Radnik radnikID) {
        this.radnikID = radnikID;
    }

    public Dobavljac getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(Dobavljac dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

}
