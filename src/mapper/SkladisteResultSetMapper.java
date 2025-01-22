/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Drzava;
import domen.Grad;
import domen.Skladiste;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class SkladisteResultSetMapper {

    public static Skladiste mapResultSetToSkladiste(ResultSet rs) throws SQLException {

        Drzava drzava = new Drzava();
        int drzavaID = rs.getInt("drzavaID");
        drzava.setDrzavaID(drzavaID);

        Grad grad = new Grad();
        int gradID = rs.getInt("gradID");
        grad.setDrzava(drzava);
        grad.setGradID(gradID);

        int skladisteID = rs.getInt("skladisteID");
        String naziv = rs.getString("nazivSkladista");

        Skladiste skladiste = new Skladiste(skladisteID, naziv, grad);

        return skladiste;
    }
}
