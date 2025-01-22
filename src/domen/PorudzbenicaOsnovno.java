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
public class PorudzbenicaOsnovno {

    private int porudzbenicaID;
    private Date datum;

    public PorudzbenicaOsnovno() {
    }

    public PorudzbenicaOsnovno(int porudzbenicaID, Date datum) {
        this.porudzbenicaID = porudzbenicaID;
        this.datum = datum;
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

}
