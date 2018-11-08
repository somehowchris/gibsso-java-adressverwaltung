/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

/**
 *
 * @author Nicola Temporal
 */
public class PersonData {
    private int pid;
    private String name;
    private String vorname;
    private String strasse;
    private int oid;
    private String telefon;
    private String handy;
    private String email;

    public PersonData(int pid, String name, String vorname, String strasse, int oid, String telefon, String handy, String email) {
        this.pid = pid;
        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.oid = oid;
        this.telefon = telefon;
        this.handy = handy;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getHandy() {
        return handy;
    }

    public String getName() {
        return name;
    }

    public int getOid() {
        return oid;
    }

    public int getPid() {
        return pid;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getVorname() {
        return vorname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
}
