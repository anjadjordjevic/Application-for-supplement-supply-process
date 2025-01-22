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
public class Otpremnica {

    private int brojOtpremnice;
    private Date datumDokumenta;
    private Date datumValute;
    private int sifraKomitenta;
    private Dobavljac dobavljac;
    private Porudzbenica porudzbenica;
    private Radnik radnik;

    public Otpremnica() {
    }

    public Otpremnica(int brojOtpremnice, Date datumDokumenta, Date datumValute, int sifraKomitenta, Dobavljac dobavljacID, Porudzbenica porudzbenicaID, Radnik radnikID) {
        this.brojOtpremnice = brojOtpremnice;
        this.datumDokumenta = datumDokumenta;
        this.datumValute = datumValute;
        this.sifraKomitenta = sifraKomitenta;
        this.dobavljac = dobavljacID;
        this.porudzbenica = porudzbenicaID;
        this.radnik = radnikID;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public int getBrojOtpremnice() {
        return brojOtpremnice;
    }

    public void setBrojOtpremnice(int brojOtpremnice) {
        this.brojOtpremnice = brojOtpremnice;
    }

    public Date getDatumDokumenta() {
        return datumDokumenta;
    }

    public void setDatumDokumenta(Date datumDokumenta) {
        this.datumDokumenta = datumDokumenta;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public int getSifraKomitenta() {
        return sifraKomitenta;
    }

    public void setSifraKomitenta(int sifraKomitenta) {
        this.sifraKomitenta = sifraKomitenta;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Porudzbenica getPorudzbenica() {
        return porudzbenica;
    }

    public void setPorudzbenica(Porudzbenica porudzbenica) {
        this.porudzbenica = porudzbenica;
    }

}
