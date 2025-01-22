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
public class PoreskaStopa {

    private int stopaID;
    private double iznos;
    private Date datum;

    public PoreskaStopa() {
    }

    public PoreskaStopa(int stopaID, double iznos, Date datum) {
        this.stopaID = stopaID;
        this.iznos = iznos;
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getStopaID() {
        return stopaID;
    }

    public void setStopaID(int stopaID) {
        this.stopaID = stopaID;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

}
