/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class JedinicaMere {
    private int jmID;
    private String nazivJM;

    public JedinicaMere() {
    }

    public JedinicaMere(int jmID, String nazivJM) {
        this.jmID = jmID;
        this.nazivJM = nazivJM;
    }

    public String getNazivJM() {
        return nazivJM;
    }

    public void setNazivJM(String nazivJM) {
        this.nazivJM = nazivJM;
    }

    public int getJmID() {
        return jmID;
    }

    public void setJmID(int jmID) {
        this.jmID = jmID;
    }
    
    
}
