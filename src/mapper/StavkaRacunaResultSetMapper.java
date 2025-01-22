/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Artikal;
import domen.Racun;
import domen.StavkaRacuna;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class StavkaRacunaResultSetMapper {

    public static StavkaRacuna mapResultSetToStavkaRacuna(ResultSet rs) throws SQLException {

        int racunID = rs.getInt("brojRacuna");
        int stavkaRacunaID = rs.getInt("stavkaRacunaID");
        double cenaArtikla = rs.getDouble("cenaartikla");
        int kolicina = rs.getInt("kolicina");
        int artikalID = rs.getInt("artikalID");

        Artikal artikal = new Artikal();
        artikal.setArtikalID(artikalID);

        Racun racun = new Racun();
        racun.setBrojRacuna(racunID);

        StavkaRacuna s = new StavkaRacuna(racun, stavkaRacunaID, cenaArtikla, kolicina, artikal);

        return s;
    }
}
