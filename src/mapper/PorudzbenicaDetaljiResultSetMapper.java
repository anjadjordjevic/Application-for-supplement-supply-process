/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Dobavljac;
import domen.PorudzbenicaDetalji;
import domen.Radnik;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class PorudzbenicaDetaljiResultSetMapper {

    public static PorudzbenicaDetalji mapResultSetToPorudzbenicaDetalji(ResultSet rs) throws SQLException {

        int porID = rs.getInt("porudzbenicaID");
        int dobID = rs.getInt("dobavljacID");
        int radID = rs.getInt("radnikID");

        Dobavljac dob = new Dobavljac();
        dob.setDobavljacID(dobID);

        Radnik radnik = new Radnik();
        radnik.setRadnikID(radID);

        PorudzbenicaDetalji pd = new PorudzbenicaDetalji(porID, radnik, dob);

        return pd;
    }
}
