/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Racun {

    private int brojRacuna;
    private Date datumRacuna;
    private Date datumPlacanja;
    private Date datumPrometa;
    private Dobavljac dobavljacID;
    private Skladiste skladisteID;
    private Otpremnica otpremnicaID;
    private Radnik radnikID;
    private Grad gradID;
    private double ukupno;
    private ArrayList<StavkaRacuna> stavke;

    public Racun() {
    }

    public Racun(int brojRacuna, Date datumRacuna, Date datumPlacanja, Date datumPrometa, Dobavljac dobavljacID, Skladiste skladisteID, Otpremnica otpremnicaID, Radnik radnikID, Grad gradID, double ukupno, ArrayList<StavkaRacuna> stavke) {
        this.brojRacuna = brojRacuna;
        this.datumRacuna = datumRacuna;
        this.datumPlacanja = datumPlacanja;
        this.datumPrometa = datumPrometa;
        this.dobavljacID = dobavljacID;
        this.skladisteID = skladisteID;
        this.otpremnicaID = otpremnicaID;
        this.radnikID = radnikID;
        this.gradID = gradID;
        this.ukupno = ukupno;
        this.stavke = stavke;
    }
    
    
    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Date getDatumRacuna() {
        return datumRacuna;
    }

    public void setDatumRacuna(Date datumRacuna) {
        this.datumRacuna = datumRacuna;
    }

    public Date getDatumPlacanja() {
        return datumPlacanja;
    }

    public void setDatumPlacanja(Date datumPlacanja) {
        this.datumPlacanja = datumPlacanja;
    }

    public Date getDatumPrometa() {
        return datumPrometa;
    }

    public void setDatumPrometa(Date datumPrometa) {
        this.datumPrometa = datumPrometa;
    }

    public Dobavljac getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(Dobavljac dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public Skladiste getSkladisteID() {
        return skladisteID;
    }

    public void setSkladisteID(Skladiste skladisteID) {
        this.skladisteID = skladisteID;
    }

    public Otpremnica getOtpremnicaID() {
        return otpremnicaID;
    }

    public void setOtpremnicaID(Otpremnica otpremnicaID) {
        this.otpremnicaID = otpremnicaID;
    }

    public Radnik getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Radnik radnikID) {
        this.radnikID = radnikID;
    }

    public Grad getGradID() {
        return gradID;
    }

    public void setGradID(Grad gradID) {
        this.gradID = gradID;
    }

    public ArrayList<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

}
