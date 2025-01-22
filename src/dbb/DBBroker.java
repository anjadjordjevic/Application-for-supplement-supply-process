/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;

import domen.Adresa;
import domen.Artikal;
import domen.CenaParitet;
import domen.Dobavljac;
import domen.Grad;
import domen.Otpremnica;
import domen.Porudzbenica;
import domen.PorudzbenicaDetalji;
import domen.PorudzbenicaOsnovno;
import domen.Racun;
import domen.Radnik;
import domen.Skladiste;
import domen.StavkaRacuna;
import tipovi.CenaParitetTip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.AdresaResultSetMapper;
import mapper.DobavljacResultSetMapper;
import mapper.GradResultSetMapper;
import mapper.OtpremnicaResultSetMapper;
import tipovi.Pib;
import mapper.ArtikalResultSetMapper;
import mapper.CenaParitetResultSetMapper;
import mapper.PorudzbenicaDetaljiResultSetMapper;
import mapper.PorudzbenicaOsnovnoResultSetMapper;
import mapper.RacunResultSetMapper;
import mapper.SkladisteResultSetMapper;
import mapper.StavkaRacunaResultSetMapper;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    private Connection konekcija;

    public DBBroker() {
    }

    public void otvoriKonekciju() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        konekcija = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##ANJADJ", "ANJADJ");
        konekcija.setAutoCommit(false);
        registerMapTypes(konekcija);
    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
    }

    public void commit() throws SQLException {
        konekcija.commit();
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

    private void registerMapTypes(Connection con) throws SQLException, ClassNotFoundException {
        Map<String, Class<?>> mapTypes = con.getTypeMap();
        mapTypes.put(CenaParitetTip.CENA_TIP, CenaParitetTip.class);
        mapTypes.put(Pib.PIB_TIP, Pib.class);
    }

    public List<Adresa> selectAllAdrese() throws SQLException {
        List<Adresa> adrese = new ArrayList<>();
        String selectQuery = "SELECT A.ADRESAID, d.DRZAVAID, g.GRADID, A.ULICA, A.BROJ, G.NAZIVGRADA"
                + " FROM ADRESA A LEFT JOIN GRAD G ON A.GRADID = G.GRADID"
                + " LEFT JOIN DRZAVA D ON G.DRZAVAID = D.DRZAVAID";
        //  String selectQuery = "SELECT * FROM ADRESA";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Adresa a = AdresaResultSetMapper.mapResultSetToAdresa(resultSet);
                adrese.add(a);
            }

            return adrese;
        }
    }

    public void deleteAdresa(Adresa a) {
        String deleteQuery = "DELETE FROM Adresa WHERE adresaID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, a.getAdresaID());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertAdresa(Adresa a) {
        String insertQuery = "INSERT INTO ADRESA (ADRESAID, GRADID, ULICA, BROJ, DRZAVAID) VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, a.getAdresaID());
            preparedStatement.setInt(2, a.getGrad().getGradID());
            preparedStatement.setString(3, a.getUlica());
            preparedStatement.setInt(4, a.getBroj());
            preparedStatement.setInt(5, a.getGrad().getDrzava().getDrzavaID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;

            //Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateAdresa(Adresa a) {
        String sql = "UPDATE Adresa SET drzavaID = ?, gradID = ?, "
                + " ulica = ?, broj = ? WHERE adresaID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(1, a.getGrad().getDrzava().getDrzavaID());
            preparedStatement.setInt(2, a.getGrad().getGradID());
            preparedStatement.setString(3, a.getUlica());
            preparedStatement.setInt(4, a.getBroj());
            preparedStatement.setInt(5, a.getAdresaID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Grad> selectAllGradovi() {
        List<Grad> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM GRAD";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Grad a = GradResultSetMapper.mapResultSetToGrad(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean updateGrad(Grad grad) {
        String sql = "UPDATE Grad SET nazivGrada = ?"
                + " WHERE gradID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setString(1, grad.getNazivGrada());
            preparedStatement.setInt(2, grad.getGradID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Dobavljac> selectAllDobavljaci() {
        List<Dobavljac> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM DOBAVLJAC";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Dobavljac a = DobavljacResultSetMapper.mapResultSetToDobavljac(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Otpremnica> selectAllOtpremnice() {
        List<Otpremnica> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM OTPREMNICA";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Otpremnica a = OtpremnicaResultSetMapper.mapResultSetToOtpremnica(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void deleteOtpremnica(Otpremnica a) {
        String deleteQuery = "DELETE FROM OTPREMNICA WHERE brojOtpremnice = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, a.getBrojOtpremnice());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateDobavljac(Dobavljac dob) {
        String sql = "UPDATE Dobavljac SET nazivDobavljaca = ?, telefon = ?, maticniBroj = ?, pib = ?, adresaID = ?,"
                + " GRADID = ? WHERE dobavljacID = ?";

        int gradID = vratiGradID(dob.getAdresaID().getAdresaID());

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setString(1, dob.getNazivDobavljaca());
            preparedStatement.setString(2, dob.getTelefon());
            preparedStatement.setString(3, dob.getMaticniBroj());
            preparedStatement.setObject(4, dob.getPib());
            preparedStatement.setInt(5, dob.getAdresaID().getAdresaID());
            preparedStatement.setInt(6, gradID);
            preparedStatement.setInt(7, dob.getDobavljacID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertOtpremnica(Otpremnica a) {
        String insertQuery = "INSERT INTO OTPREMNICA (BROJOTPREMNICE, DATUMDOKUMENTA, DATUMVALUTE, SIFRAKOMITENTA"
                + ", DOBAVLJACID, PORUDZBENICAID, RADNIKID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, a.getBrojOtpremnice());
            preparedStatement.setDate(2, new java.sql.Date(a.getDatumDokumenta().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(a.getDatumValute().getTime()));
            //preparedStatement.setDate(3, p.getDatumValute());
            preparedStatement.setInt(4, a.getSifraKomitenta());
            preparedStatement.setInt(5, a.getDobavljac().getDobavljacID());
            preparedStatement.setInt(6, a.getPorudzbenica().getPorudzbenicaID());
            preparedStatement.setInt(7, a.getRadnik().getRadnikID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
            //Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateOtpremnica(Otpremnica otpremnica) {
        String sql = "UPDATE Otpremnica SET BROJOTPREMNICE = ?, DATUMDOKUMENTA = ?, DATUMVALUTE = ?, SIFRAKOMITENTA = ?,"
                + " PORUDZBENICAID = ?, RADNIKID = ?, DOBAVLJACID = ? WHERE BROJOTPREMNICE = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(1, otpremnica.getBrojOtpremnice());
            preparedStatement.setDate(2, new java.sql.Date(otpremnica.getDatumDokumenta().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(otpremnica.getDatumValute().getTime()));
            preparedStatement.setInt(4, otpremnica.getSifraKomitenta());
            preparedStatement.setInt(7, otpremnica.getDobavljac().getDobavljacID());
            preparedStatement.setInt(5, otpremnica.getPorudzbenica().getPorudzbenicaID());
            preparedStatement.setInt(6, otpremnica.getRadnik().getRadnikID());
            preparedStatement.setInt(8, otpremnica.getBrojOtpremnice());
            //.setString(7, otpremnica.getDobavljac().getNazivDobavljaca());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Skladiste> selectAllSkladiste() {
        List<Skladiste> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM SKLADISTE";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Skladiste a = SkladisteResultSetMapper.mapResultSetToSkladiste(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean updateSkladiste(Skladiste skla) {
        String sql = "UPDATE SKLADISTE SET NAZIVSKLADISTA = ?, GRADID = ? WHERE SKLADISTEID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setString(1, skla.getNazivSkladista());
            preparedStatement.setInt(2, skla.getGradID().getGradID());
            //preparedStatement.setInt(3, skla.getGradID().getDrzava().getDrzavaID());
            preparedStatement.setInt(3, skla.getSkladisteID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Racun> selectAllRacun() {
        List<Racun> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM RACUN";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Racun a = RacunResultSetMapper.mapResultSetToRacun(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void deleteRacun(Racun a) {
        String deleteQuery = "DELETE FROM RACUN WHERE brojRacuna = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, a.getBrojRacuna());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertRacun(Racun a) {
        String insertQuery = "INSERT INTO RACUN (BROJRACUNA, DATUMRACUNA, DATUMPLACANJA, DATUMPROMETA"
                + ", DOBAVLJACID, SKLADISTEID, BROJOTPREMNICE, RADNIKID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, a.getBrojRacuna());
            preparedStatement.setDate(2, new java.sql.Date(a.getDatumRacuna().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(a.getDatumPlacanja().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(a.getDatumPrometa().getTime()));
            preparedStatement.setInt(5, a.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(6, a.getSkladisteID().getSkladisteID());
            preparedStatement.setInt(7, a.getOtpremnicaID().getBrojOtpremnice());
            preparedStatement.setInt(8, a.getRadnikID().getRadnikID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
            //Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Artikal> selectAllArtikal() {
        List<Artikal> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM artikal";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Artikal a = ArtikalResultSetMapper.mapResultSetToArtikal(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean updateArtikal(Artikal a) {
        String sql = "UPDATE ARTIKAL SET NAZIV = ?, PAKOVANJE = ?, nazivjm = ?, STOPAID = ?, KVALITET = ?, "
                + "AKTUELNACENA = ? WHERE ARTIKALID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setString(1, a.getNaziv());
            preparedStatement.setString(2, a.getPakovanje());
            preparedStatement.setString(3, a.getNazivJM());
            preparedStatement.setInt(4, a.getStopaID().getStopaID());
            preparedStatement.setInt(5, a.getKvalitet());
            preparedStatement.setDouble(6, a.getAktuelnaCena());
            preparedStatement.setInt(7, a.getArtikalID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<CenaParitet> selectAllCenaParitet() {
        List<CenaParitet> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM CENAPARITET";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CenaParitet a = CenaParitetResultSetMapper.mapResultSetToCenaParitet(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void deleteCena(CenaParitet a) {
        String deleteQuery = "DELETE FROM CENAPARITET WHERE CENAPARITETID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, a.getCenaParitetID());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateNazivOtpremnice(Otpremnica a) {
        String sql = "UPDATE Otpremnica SET NAZIVDOBAVLJACA = ? WHERE BROJOTPREMNICE = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(2, a.getBrojOtpremnice());
            preparedStatement.setString(1, a.getDobavljac().getNazivDobavljaca());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertCena(CenaParitet cp) {
        String insertQuery = "INSERT INTO CENAPARITET (ARTIKALID, CENAPARITETID, NAZIV, CENA)"
                + " VALUES (?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, cp.getArtikalID().getArtikalID());
            preparedStatement.setInt(2, cp.getCenaParitetID());
            preparedStatement.setString(3, cp.getNaziv());
            preparedStatement.setObject(4, cp.getCena());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateCena(CenaParitet a) {
        String sql = "UPDATE CENAPARITET SET NAZIV = ?, ARTIKALID = ?, CENA = ?"
                + " WHERE CENAPARITETID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setString(1, a.getNaziv());
            preparedStatement.setInt(2, a.getArtikalID().getArtikalID());
            preparedStatement.setObject(3, a.getCena());
            preparedStatement.setInt(4, a.getCenaParitetID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertRacunSaStavkama(Racun a) throws SQLException {
        String insertQuery = "INSERT INTO RACUN (BROJRACUNA, DATUMRACUNA, DATUMPLACANJA, DATUMPROMETA"
                + ", DOBAVLJACID, SKLADISTEID, BROJOTPREMNICE, RADNIKID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, a.getBrojRacuna());
            preparedStatement.setDate(2, new java.sql.Date(a.getDatumRacuna().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(a.getDatumPlacanja().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(a.getDatumPrometa().getTime()));
            preparedStatement.setInt(5, a.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(6, a.getSkladisteID().getSkladisteID());
            preparedStatement.setInt(7, a.getOtpremnicaID().getBrojOtpremnice());
            preparedStatement.setInt(8, a.getRadnikID().getRadnikID());

            preparedStatement.executeUpdate();

            try ( ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Object rowId = generatedKeys.getObject(1);

                    String selectSql = "SELECT brojracuna FROM racun WHERE ROWID = '" + rowId.toString() + "'";
                    ResultSet rs = konekcija.createStatement().executeQuery(selectSql);

                    int brojRacuna = -1;

                    if (rs.next()) {
                        brojRacuna = rs.getInt("brojRacuna");
                        a.setBrojRacuna(brojRacuna);
                    } else {
                        // Obrada situacije kada nema rezultata u ResultSet-u
                        System.out.println("Nema rezultata u ResultSet-u.");
                        throw new SQLException("Pojavila se greška prilikom čuvanja računa, nije vraćen generisani ID.");
                    }

                    List<StavkaRacuna> stavke = a.getStavke();
                    for (StavkaRacuna stavka : stavke) {
                        String insertStavkaQuery = "INSERT INTO stavkaracuna (brojracuna, stavkaracunaid, artikalid, kolicina) VALUES (?, ?, ?, ?)";
                        try ( PreparedStatement stavkaStatement = konekcija.prepareStatement(insertStavkaQuery)) {
                            stavkaStatement.setInt(1, brojRacuna);
                            stavkaStatement.setInt(2, stavka.getStavkaRacunaID());
                            stavkaStatement.setInt(3, stavka.getArtikalID().getArtikalID());
                            stavkaStatement.setInt(4, stavka.getKolicina());
                            stavkaStatement.executeUpdate();
                        }
                    }
                }

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    public boolean updateRacun(Racun a) {
        String sql = "UPDATE RACUN SET DATUMRACUNA = ?, DATUMPLACANJA = ?, DATUMPROMETA = ?, DOBAVLJACID = ?,"
                + " SKLADISTEID = ?, RADNIKID = ?, BROJOTPREMNICE = ?, UKUPNO = ? WHERE BROJRACUNA = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(a.getDatumRacuna().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(a.getDatumPlacanja().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(a.getDatumPrometa().getTime()));
            preparedStatement.setInt(4, a.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(5, a.getSkladisteID().getSkladisteID());
            preparedStatement.setInt(7, a.getOtpremnicaID().getBrojOtpremnice());
            preparedStatement.setInt(6, a.getRadnikID().getRadnikID());
            preparedStatement.setDouble(8, a.getUkupno());
            preparedStatement.setInt(9, a.getBrojRacuna());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int vratiDrzavuPoGradu(int gradID) {
        int a = 0;
        String selectQuery = "SELECT drzavaID FROM grad WHERE GRADID = " + gradID;
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                a = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(a);
        return a;
    }

    public List<PorudzbenicaOsnovno> vratiSvePorudzbeniceOsnovno() {
        List<PorudzbenicaOsnovno> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM PorudzbenicaOsnovno";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PorudzbenicaOsnovno a = PorudzbenicaOsnovnoResultSetMapper.mapResultSetToPorudzbenicaOsnovno(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<PorudzbenicaDetalji> vratiSvePorudzbeniceDetalji() {
        List<PorudzbenicaDetalji> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM PorudzbenicaDetalji";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PorudzbenicaDetalji a = PorudzbenicaDetaljiResultSetMapper.mapResultSetToPorudzbenicaDetalji(resultSet);
                lista.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insertPorudzbenica(Porudzbenica cp) {
        String insertQuery = "INSERT INTO PORUDZBENICAPOGLED (PORUDZBENICAID, DATUM, DOBAVLJACID, RADNIKID)"
                + " VALUES (?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, cp.getPorudzbenicaID());
            preparedStatement.setDate(2, new java.sql.Date(cp.getDatum().getTime()));
            preparedStatement.setInt(3, cp.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(4, cp.getRadnikID().getRadnikID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deletePorudzbenica(Porudzbenica a) {
        String deleteQuery = "DELETE FROM PORUDZBENICAPOGLED WHERE PORUDZBENICAid = " + a.getPorudzbenicaID();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Porudzbenica vratiPorudzbenicuPoId(int id) {
        Porudzbenica p = new Porudzbenica();
        String selectQuery = "SELECT * FROM PORUDZBENICAPOGLED WHERE PORUDZBENICAId = " + id;
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int porID = resultSet.getInt("porudzbenicaID");
                int dobID = resultSet.getInt("dobavljacID");
                int radID = resultSet.getInt("radnikID");
                Date datum = resultSet.getDate("datum");
                Dobavljac dob = new Dobavljac();
                dob.setDobavljacID(dobID);

                Radnik radnik = new Radnik();
                radnik.setRadnikID(radID);

                p = new Porudzbenica(porID, datum, radnik, dob);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean updatePorudzbenica(Porudzbenica cp) {
        String insertQuery = "UPDATE PORUDZBENICAPOGLED SET DATUM = ?, DOBAVLJACID = ?, RADNIKID = ?"
                + " WHERE PORUDZBENICAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(4, cp.getPorudzbenicaID());
            preparedStatement.setDate(1, new java.sql.Date(cp.getDatum().getTime()));
            preparedStatement.setInt(2, cp.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(3, cp.getRadnikID().getRadnikID());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<StavkaRacuna> vratiStavkeZaRacun(int a) {
        List<StavkaRacuna> lista = new ArrayList<>();

        String selectQuery = "SELECT * FROM StavkaRacuna WHERE brojracuna = " + a;
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StavkaRacuna aa = StavkaRacunaResultSetMapper.mapResultSetToStavkaRacuna(resultSet);
                lista.add(aa);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insertStavka(StavkaRacuna cp) {
        String insertQuery = "INSERT INTO STAVKARACUNA (BROJRACUNA, STAVKARACUNAID, ARTIKALID, KOLICINA)"
                + " VALUES (?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, cp.getRacunID().getBrojRacuna());
            preparedStatement.setInt(2, cp.getStavkaRacunaID());
            preparedStatement.setInt(3, cp.getArtikalID().getArtikalID());
            preparedStatement.setInt(4, cp.getKolicina());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deleteStavkaRacuna(StavkaRacuna a) {
        String deleteQuery = "DELETE FROM stavkaRacuna WHERE stavkaracunaid = " + a.getStavkaRacunaID() + " and brojracuna = " + a.getRacunID().getBrojRacuna();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateStavkaRacuna(StavkaRacuna s, int brojRacuna) {
        String insertQuery = "UPDATE stavkaracuna SET artikalid = ?, kolicina = ?"
                + " WHERE stavkaracunaid = ? and brojracuna = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(insertQuery)) {
            // preparedStatement.setInt(1, s.getRacunID().getBrojRacuna());
            preparedStatement.setInt(1, s.getArtikalID().getArtikalID());
            preparedStatement.setInt(2, s.getKolicina());
            preparedStatement.setInt(3, s.getStavkaRacunaID());
            preparedStatement.setInt(4, brojRacuna);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateNazivGrada(Adresa a) {
        String sql = "UPDATE Adresa SET NAZIVgrada = ? WHERE adresaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(2, a.getAdresaID());
            preparedStatement.setString(1, a.getGrad().getNazivGrada());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private int vratiGradID(int adresaID) {
        int g = 0;
        String selectQuery = "SELECT gradID from Adresa WHERE adresaID = " + adresaID;
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(selectQuery);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                g = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    public boolean insertJM(String jm) {
        String upit = "{call DODAVANJE_NOVE_JEDINICE_MERE(?)}";

        try ( CallableStatement callableStatement = konekcija.prepareCall(upit)) {
            callableStatement.setString(1, jm);
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateGradID(Racun r) {
        String sql = "UPDATE racun SET gradID = ? WHERE brojracuna = " + r.getBrojRacuna();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(1, r.getGradID().getGradID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateDrzavaID(Racun r) {
        String sql = "UPDATE racun SET drzavaid = ? WHERE brojracuna = " + r.getBrojRacuna();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(1, r.getGradID().getDrzava().getDrzavaID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertPorudzbenicaOsnovno(PorudzbenicaOsnovno po) {
        String upit = "INSERT INTO PORUDZBENICAOSNOVNO VALUES (?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, po.getPorudzbenicaID());
            preparedStatement.setDate(2, new java.sql.Date(po.getDatum().getTime()));

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updatePorudzbenicaOsnovno(PorudzbenicaOsnovno po, int porudzbenicaID) {
        String upit = "UPDATE PORUDZBENICAOSNOVNO SET PORUDZBENICAID = ?, DATUM = ? WHERE PORUDZBENICAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, po.getPorudzbenicaID());
            preparedStatement.setDate(2, new java.sql.Date(po.getDatum().getTime()));
            preparedStatement.setInt(3, porudzbenicaID);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletePorudzbenicaOsnovno(PorudzbenicaOsnovno a) {
        String deleteQuery = "DELETE FROM PorudzbenicaOsnovno WHERE porudzbenicaid = " + a.getPorudzbenicaID();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletePorudzbenicaDetalji(PorudzbenicaDetalji a) {
        String deleteQuery = "DELETE FROM PorudzbenicaDetalji WHERE porudzbenicaid = " + a.getPorudzbenicaID();

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(deleteQuery)) {

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertPorudzbenicaDetalji(PorudzbenicaDetalji pd) {
        String upit = "INSERT INTO PorudzbenicaDetalji VALUES (?, ?, ?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, pd.getPorudzbenicaID());
            preparedStatement.setInt(2, pd.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(3, pd.getRadnikID().getRadnikID());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updatePorudzbenicaDetalji(PorudzbenicaDetalji pd, int porudzbenicaID) {
        String upit = "UPDATE PorudzbenicaDetalji SET PORUDZBENICAID = ?, dobavljacid = ?, radnikid = ? WHERE PORUDZBENICAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, pd.getPorudzbenicaID());
            preparedStatement.setInt(2, pd.getDobavljacID().getDobavljacID());
            preparedStatement.setInt(3, pd.getRadnikID().getRadnikID());
            preparedStatement.setInt(4, porudzbenicaID);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
