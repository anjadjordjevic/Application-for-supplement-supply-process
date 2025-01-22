/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import dbb.DBBroker;
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
import domen.Skladiste;
import domen.StavkaRacuna;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Kontroler {

    public static Kontroler instance;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<Adresa> vratiSveAdrese() {
        List<Adresa> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllAdrese();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteAdresa(Adresa a) {
        try {
            db.otvoriKonekciju();
            db.deleteAdresa(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertAdresa(Adresa a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertAdresa(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateAdresa(Adresa a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateAdresa(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<Grad> vratiSveGradove() {
        List<Grad> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllGradovi();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateGrad(Grad grad) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateGrad(grad);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<Dobavljac> vratiSveDobavljace() {
        List<Dobavljac> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllDobavljaci();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Otpremnica> vratiSveOtpremnice() {
        List<Otpremnica> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllOtpremnice();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteOtpremnica(Otpremnica a) {
        try {
            db.otvoriKonekciju();
            db.deleteOtpremnica(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateDobavljac(Dobavljac dob) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateDobavljac(dob);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insertOtpremnica(Otpremnica otpremnica) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertOtpremnica(otpremnica);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateOtpremnica(Otpremnica otpremnica) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateOtpremnica(otpremnica);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<Skladiste> vratiSvaSkladista() {
        List<Skladiste> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllSkladiste();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateSkladiste(Skladiste skla) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateSkladiste(skla);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<Racun> vratiSveRacune() {
        List<Racun> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllRacun();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteRacun(Racun a) {
        try {
            db.otvoriKonekciju();
            db.deleteRacun(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertRacun(Racun racun) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertRacun(racun);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public List<Artikal> vratiSveArtikle() {
        List<Artikal> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllArtikal();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateArtikal(Artikal a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateArtikal(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<CenaParitet> vratiSveCene() {
        List<CenaParitet> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.selectAllCenaParitet();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteCena(CenaParitet a) {
        try {
            db.otvoriKonekciju();
            db.deleteCena(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateNazivOtpremnice(Otpremnica a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateNazivOtpremnice(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insertCena(CenaParitet cp) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertCena(cp);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateCena(CenaParitet cp) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateCena(cp);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insertRacunSaStavkama(Racun racun) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertRacunSaStavkama(racun);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateRacun(Racun r) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateRacun(r);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public int vratiDrzavuPoGradu(int gradID) {
        int a = 0;
        try {
            db.otvoriKonekciju();
            a = db.vratiDrzavuPoGradu(gradID);
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public List<PorudzbenicaOsnovno> vratiSvePorudzbeniceOsnovno() {
        List<PorudzbenicaOsnovno> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.vratiSvePorudzbeniceOsnovno();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<PorudzbenicaDetalji> vratiSvePorudzbeniceDetalji() {
        List<PorudzbenicaDetalji> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.vratiSvePorudzbeniceDetalji();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean insertPorudzbenica(Porudzbenica cp) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertPorudzbenica(cp);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public void deletePorudzbenica(Porudzbenica a) {
        try {
            db.otvoriKonekciju();
            db.deletePorudzbenica(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Porudzbenica vratiPorudzbenicuPoId(int id) {
        Porudzbenica p = new Porudzbenica();
        try {
            db.otvoriKonekciju();
            p = db.vratiPorudzbenicuPoId(id);
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean updatePorudzbenica(Porudzbenica p) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updatePorudzbenica(p);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public List<StavkaRacuna> vratiStavkeZaRacun(int a) {
        List<StavkaRacuna> p = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            p = db.vratiStavkeZaRacun(a);
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean insertStavkaRacuna(StavkaRacuna stavka) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertStavka(stavka);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public void deleteStavkaRacuna(StavkaRacuna a) {
        try {
            db.otvoriKonekciju();
            db.deleteStavkaRacuna(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateStavkaRacuna(StavkaRacuna s, int brojRacuna) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateStavkaRacuna(s, brojRacuna);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean updateNazivGrada(Adresa aa) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateNazivGrada(aa);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public int vratiBrojRacuna(int stavkaRacunaID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean insertJM(String jm) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertJM(jm);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;

    }

    public boolean updateGradID(Racun r) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateGradID(r);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean updateDrzavaID(Racun r) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateDrzavaID(r);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insertPorudzbenicaOsnovno(PorudzbenicaOsnovno po) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertPorudzbenicaOsnovno(po);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updatePorudzbenicaOsnovno(PorudzbenicaOsnovno po, int porudzbenicaID) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updatePorudzbenicaOsnovno(po, porudzbenicaID);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean deletePorudzbenicaOsnovno(PorudzbenicaOsnovno a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.deletePorudzbenicaOsnovno(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean deletePorudzbenicaDetalji(PorudzbenicaDetalji a) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.deletePorudzbenicaDetalji(a);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean insertPorudzbenicaDetalji(PorudzbenicaDetalji pd) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertPorudzbenicaDetalji(pd);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updatePorudzbenicaDetalji(PorudzbenicaDetalji pd, int porudzbenicaID) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updatePorudzbenicaDetalji(pd, porudzbenicaID);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

}
