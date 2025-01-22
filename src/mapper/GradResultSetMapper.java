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
public class GradResultSetMapper {
    
    public static Grad mapResultSetToGrad(ResultSet rs) throws SQLException {

        Drzava drzava = new Drzava();
        int drzavaID = rs.getInt("drzavaID");
        drzava.setDrzavaID(drzavaID);

        Grad grad = new Grad();
        int gradID = rs.getInt("gradID");
        String nazivGrada=rs.getString("nazivGrada");
        grad.setDrzava(drzava);
        grad.setGradID(gradID);
        grad.setNazivGrada(nazivGrada);

        return grad;
    }
}
