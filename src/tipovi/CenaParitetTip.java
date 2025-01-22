/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipovi;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author Korisnik
 */
public class CenaParitetTip implements ORAData, ORADataFactory {

    public static final String CENA_TIP = "C##ANJADJ.CENAPARITETTIP";
    private BigDecimal iznosCene;
    private LocalDate datumCene;

    @Override
    public Datum toDatum(Connection connection) throws SQLException {
        StructDescriptor sd
                = StructDescriptor.createDescriptor(CENA_TIP, connection);
        Object[] attributes;
        attributes = new Object[]{iznosCene, java.sql.Date.valueOf(datumCene)};
        return new STRUCT(sd, connection, attributes);
    }

    @Override
    public ORAData create(Datum datum, int i) throws SQLException {
        CenaParitetTip cena = new CenaParitetTip();
        Struct struct = (Struct) datum;
        Object[] attributes = struct.getAttributes();
        cena.iznosCene = (BigDecimal) attributes[0];
        cena.datumCene = ((Timestamp) attributes[1]).toLocalDateTime().toLocalDate();

        return cena;
    }

    public CenaParitetTip() {
    }

    public CenaParitetTip(BigDecimal iznosCene, LocalDate datumCene) {
        this.iznosCene = iznosCene;
        this.datumCene = datumCene;
    }

    public BigDecimal getIznosCene() {
        return iznosCene;
    }

    public void setIznosCene(BigDecimal iznosCene) {
        this.iznosCene = iznosCene;
    }

    public LocalDate getDatumCene() {
        return datumCene;
    }

    public void setDatumCene(LocalDate datumCene) {
        this.datumCene = datumCene;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return "Cena: " + iznosCene + "din važi od datuma: " + datumCene.format(formatter);
   
    }

}
