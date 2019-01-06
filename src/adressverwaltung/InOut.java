/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

import adressverwaltung.models.Person;

import java.util.*;
import java.io.File;
import java.sql.*;
import adressverwaltung.controller.Controller;
import adressverwaltung.controller.DataBaseController;
import adressverwaltung.controller.FileSystemController;
import adressverwaltung.models.Ort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nicola Temporal
 */
public class InOut {

    private Controller c = null;

    public InOut(HashMap<String, String> dotEnv) {
        String sep = System.getProperty("file.separator");
        String home = System.getProperty("user.home");
        if(!new File(home).canRead()) home = System.getProperty(System.getProperty("user.dir"));
        if(dotEnv == null) dotEnv = new HashMap<>();
        File dotEnvFile = new File(home);
        if (dotEnvFile.exists() && dotEnv.isEmpty() ? !(dotEnv = getDotEnv(home, sep)).isEmpty() : true){
            c = new DataBaseController(dotEnv);
        }else{
            c = new FileSystemController(home, sep);
        }
    }

    private HashMap<String, String> getDotEnv(String dir,String sep) {
        HashMap<String, String> values = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dir + sep + ".env"));
            List<String> keys = Arrays.asList("DATABASE_USER", "DATABASE_PASSWORD", "DATABASE_HOST", "DATABASE_PORT", "DATABASE_NAME");
            String line = reader.readLine();
            while (line != null) {
                if (keys.contains(line.split("=")[0])) values.put(line.split("=")[0], line.split("=").length > 1 ? line.split("=")[1] : "");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
        }
        return values;
    }

    public List<Person> searchPerson(String vorname, String name) throws SQLException {
        return c.searchPerson(new Person(vorname, name));
    }

    public void savePerson(Person p) throws SQLException {
        if (p.getId() != null)
            c.updatePerson(p);
        else
            c.insertPerson(p);
    }

    public void deletePerson(Person p) throws SQLException {
        c.deletePerson(p);
    }

    public List<Person> getPeople(int offset) {
        return c.getPeople(20, offset);
    }

    public Person getPerson(Long id) {
        return c.getPerson(id);
    }

    public List<Ort> searchOrt(int plz, String name) throws SQLException {
        return c.searchOrt(new Ort(plz, name));
    }

    public void saveOrt(Ort p) throws SQLException {
        if (p.getOid() != null)
            c.updateOrt(p);
        else
            c.insertOrt(p);
    }

    public void deleteOrt(Ort p) throws SQLException {
        c.deleteOrt(p);
    }

    public List<Ort> getPlaces(int offset) {
        return c.getOrt(20, offset);
    }

    public Ort getOrt(Long id) {
        return c.getOrt(id);
    }
    
    public Long countOrt(){
        return c.countOrt();
    }
    public Long countPeople(){
        return c.countPeople();
    }
}
