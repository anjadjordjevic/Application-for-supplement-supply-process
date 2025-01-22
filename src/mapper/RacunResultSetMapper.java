/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Dobavljac;
import domen.Drzava;
import domen.Grad;
import domen.Otpremnica;
import domen.Racun;
import domen.Radnik;
import domen.Skladiste;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class RacunResultSetMapper {

    public static Racun mapResultSetToRacun(ResultSet rs) throws SQLException {

        int brojRacuna = rs.getInt("brojRacuna");
        Date datumRacuna = rs.getDate("datumRacuna");
        Date datumPlacanja = rs.getDate("datumPlacanja");
        Date datumPrometa = rs.getDate("datumPrometa");
        int dobavljacID = rs.getInt("dobavljacID");
        int skladisteID = rs.getInt("skladisteID");
        int otpremnicaID = rs.getInt("brojOtpremnice");
        int radnikID = rs.getInt("radnikID");
        int gradID = rs.getInt("gradID");
        int drzavaID = rs.getInt("drzavaID");
        double ukupno = rs.getDouble("ukupno");

        Dobavljac d = new Dobavljac();
        d.setDobavljacID(dobavljacID);

        Skladiste s = new Skladiste();
        s.setSkladisteID(skladisteID);

        Otpremnica o = new Otpremnica();
        o.setBrojOtpremnice(otpremnicaID);

        Radnik ra = new Radnik();
        ra.setRadnikID(radnikID);

        Drzava dr = new Drzava();
        dr.setDrzavaID(drzavaID);

        Grad g = new Grad();
        g.setGradID(gradID);
        g.setDrzava(dr);

        Racun r = new Racun(brojRacuna, datumRacuna, datumPlacanja, datumPrometa, d, s, o, ra, g, ukupno, null);
        return r;
    }
}
