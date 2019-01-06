/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author chris
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    
    private String name;
    private String vorname;
    private String strasse;
    private int oid;
    private String telefon;
    private String handy;
    private String email;

    public Person(String name, String vorname, String strasse, int oid, String telefon, String handy, String email) {
        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.oid = oid;
        this.telefon = telefon;
        this.handy = handy;
        this.email = email;
    }

    public Person() {
    }

    
    
    
    public Person(String vorname,String name) {
        this.name = name;
        this.vorname = vorname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adressverwaltung.models.Person[ id=" + pid + " ]";
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

    public void setPid(Long pid) {
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
    
    public Long getId() {
        return pid;
    }

    public void setId(Long pid) {
        this.pid = pid;
    }
    
    public void setAll(Person person){
        this.name = person.getName();
        this.vorname = person.getVorname();
        this.strasse = person.getStrasse();
        this.oid = person.getOid();
        this.telefon = person.getTelefon();
        this.handy = person.getHandy();
        this.email = person.getEmail();
    }
    
}
