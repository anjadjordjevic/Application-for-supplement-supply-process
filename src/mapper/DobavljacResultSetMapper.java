/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import domen.Adresa;
import domen.Dobavljac;
import domen.Drzava;
import domen.Grad;
import java.sql.ResultSet;
import java.sql.SQLException;
import tipovi.Pib;

/**
 *
 * @author Korisnik
 */
public class DobavljacResultSetMapper {

    public static Dobavljac mapResultSetToDobavljac(ResultSet rs) throws SQLException {

        int dobavljacID = rs.getInt("dobavljacID");
        String nazivDobavljaca = rs.getString("nazivDobavljaca");
        String telefon = rs.getString("telefon");
        String maticniBroj = rs.getString("maticniBroj");
        Pib pib = (Pib) rs.getObject("PIB");

        int adresaID = rs.getInt("adresaID");
        int gradID = rs.getInt("gradID");
        int drzavaID = rs.getInt("drzavaID");
        Adresa a = new Adresa();
        a.setAdresaID(adresaID);
        Grad grad =new Grad();
        grad.setGradID(gradID);
        Drzava drzava=new Drzava();
        drzava.setDrzavaID(drzavaID);
        grad.setDrzava(drzava); 
        a.setGrad(grad);

        Dobavljac dob = new Dobavljac(dobavljacID, nazivDobavljaca, telefon, maticniBroj, pib, a);
        return dob;
    }
}
