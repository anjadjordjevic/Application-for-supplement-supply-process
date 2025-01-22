/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Artikal;
import domen.JedinicaMere;
import domen.PoreskaStopa;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class ArtikalResultSetMapper {

    public static Artikal mapResultSetToArtikal(ResultSet rs) throws SQLException {

        int artikalID = rs.getInt("artikalID");
        String naziv = rs.getString("naziv");
        String pakovanje = rs.getString("pakovanje");
        int kvalitet = rs.getInt("kvalitet");
        String jmID = rs.getString("nazivJM");
        int stopaID = rs.getInt("stopaid");
        double aktuelnaCena = rs.getDouble("aktuelnaCena");

        PoreskaStopa ps = new PoreskaStopa();
        ps.setStopaID(stopaID);

        Artikal artikal = new Artikal(artikalID, naziv, pakovanje, kvalitet, jmID, ps, aktuelnaCena);

        return artikal;
    }
}
