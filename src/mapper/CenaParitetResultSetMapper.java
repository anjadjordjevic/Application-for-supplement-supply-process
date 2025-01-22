/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Artikal;
import domen.CenaParitet;
import domen.JedinicaMere;
import domen.PoreskaStopa;
import java.sql.ResultSet;
import java.sql.SQLException;
import tipovi.CenaParitetTip;

/**
 *
 * @author Korisnik
 */
public class CenaParitetResultSetMapper {

    public static CenaParitet mapResultSetToCenaParitet(ResultSet rs) throws SQLException {

        int artikalID = rs.getInt("artikalID");
        Artikal artikal = new Artikal();
        artikal.setArtikalID(artikalID);

        int cenaParitetID = rs.getInt("cenaParitetID");
        String naziv = rs.getString("naziv");
        CenaParitetTip cena = (CenaParitetTip) rs.getObject("cena");

        CenaParitet c = new CenaParitet(artikal, cenaParitetID, naziv, cena);

        return c;
    }
}
