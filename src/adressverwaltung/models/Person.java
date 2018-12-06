/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class Person {
    private int anr;
    private String name;
    private String vorname;
    private String strasse;
    private int oid;
    private String telefon;
    private String handy;
    private String email;
    
    public Person() {
        
    }

    public Person(int anr, String name, String vorname, String strasse, int oid, String telefon, String handy, String email) {
        this.anr = anr;
        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.oid = oid;
        this.telefon = telefon;
        this.handy = handy;
        this.email = email;
    }
    
    
    public void setAnr(int anr) {
        this.anr=anr;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setVorname(String vorname) {
        this.vorname=vorname;
    }
    
    public String getVorname() {
        return this.vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
