/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Dobavljac;
import domen.Otpremnica;
import domen.Porudzbenica;
import domen.Radnik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class OtpremnicaResultSetMapper {

    public static Otpremnica mapResultSetToOtpremnica(ResultSet rs) throws SQLException {

        int brojOtpremnice = rs.getInt("brojOtpremnice");
        Date datumDokumenta = rs.getDate("datumDokumenta");
        Date datumValute = rs.getDate("datumValute");
        int sifra = rs.getInt("sifraKomitenta");
        int dobavljacID = rs.getInt("dobavljacID");
        String nazivDobavljaca = rs.getString("nazivDobavljaca");
        Dobavljac dobavljac = new Dobavljac();
        dobavljac.setDobavljacID(dobavljacID);
        dobavljac.setNazivDobavljaca(nazivDobavljaca);

        Porudzbenica por = new Porudzbenica();
        por.setPorudzbenicaID(rs.getInt("porudzbenicaID"));

        Radnik radnik = new Radnik();
        radnik.setRadnikID(rs.getInt("radnikID"));

        Otpremnica otpremnica = new Otpremnica(brojOtpremnice, datumDokumenta, datumValute, sifra, dobavljac, por, radnik);

        return otpremnica;
    }
}
