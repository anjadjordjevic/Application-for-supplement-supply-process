/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.PorudzbenicaOsnovno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class PorudzbenicaOsnovnoResultSetMapper {

    public static PorudzbenicaOsnovno mapResultSetToPorudzbenicaOsnovno(ResultSet rs) throws SQLException {

        int porID = rs.getInt("porudzbenicaID");
        Date datum = rs.getDate("datum");

        PorudzbenicaOsnovno po = new PorudzbenicaOsnovno(porID, datum);

        return po;
    }
}
