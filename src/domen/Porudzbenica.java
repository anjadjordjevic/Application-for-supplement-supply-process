/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Porudzbenica {
    private int porudzbenicaID;
    private Date datum;
    private Radnik radnikID;
    private Dobavljac dobavljacID;

    public Porudzbenica() {
    }

    public Porudzbenica(int porudzbenicaID, Date datum, Radnik radnikID, Dobavljac dobavljacID) {
        this.porudzbenicaID = porudzbenicaID;
        this.datum = datum;
        this.radnikID = radnikID;
        this.dobavljacID = dobavljacID;
    }

    public Dobavljac getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(Dobavljac dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public int getPorudzbenicaID() {
        return porudzbenicaID;
    }

    public void setPorudzbenicaID(int porudzbenicaID) {
        this.porudzbenicaID = porudzbenicaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Radnik getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Radnik radnikID) {
        this.radnikID = radnikID;
    }
    
    
}
