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
public class Ort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oid;

    private String name;
    private int plz;

    public Ort(int plz, String name){
        this.plz = plz;
        this.name = name;
    }

    public Ort() {
    }
    
    
    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the oid fields are not set
        if (!(object instanceof Ort)) {
            return false;
        }
        Ort other = (Ort) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adressverwaltung.models.Ort[ id=" + oid + " ]";
    }

    public int getPlz() {
        return plz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getName() {
        return name;
    }

    public void setAll(Ort ort){
        this.name = ort.name;
        this.plz = ort.plz;
    }
    
}
