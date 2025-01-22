/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Adresa;
import domen.Drzava;
import domen.Grad;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class AdresaResultSetMapper {

    public static Adresa mapResultSetToAdresa(ResultSet rs) throws SQLException {

        Drzava drzava = new Drzava();
        int drzavaID = rs.getInt("drzavaID");
      //  String nazivDrzave = rs.getString("nazivDrzave");
        drzava.setDrzavaID(drzavaID);
     //   drzava.setNazivDrzave(nazivDrzave);

        Grad grad = new Grad();
        int gradID = rs.getInt("gradID");
        String nazivGrada=rs.getString("nazivGrada");
        grad.setDrzava(drzava);
        grad.setGradID(gradID);
        grad.setNazivGrada(nazivGrada);

        int adresaID = rs.getInt("adresaID");
        String ulica = rs.getString("ulica");
        int broj = rs.getInt("broj");

        return new Adresa(grad, adresaID, ulica, broj);
    }
}
