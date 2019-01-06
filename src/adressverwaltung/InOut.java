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
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola Temporal
 */
public class InOut {

    private Controller c = null;

    public InOut(HashMap<String, String> dotEnv) throws SQLException {
        String sep = System.getProperty("file.separator");
        String home = System.getProperty("user.home");
        if(!new File(home).canRead()) home = System.getProperty("user.dir");
        if(dotEnv == null) dotEnv = new HashMap<>();
        File dotEnvFile = new File(home);
        if (dotEnvFile.exists() && dotEnv.isEmpty() ? !(dotEnv = getDotEnv(home, sep)).isEmpty() : true){
            c = new DataBaseController(dotEnv);
        }else{
            c = new FileSystemController(home, sep);
        }
        try {
            export();
        } catch (Exception ex) {
            Logger.getLogger(InOut.class.getName()).log(Level.SEVERE, null, ex);
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
        if (p.getId() != null){
            c.updatePerson(p);
        }else{
            c.insertPerson(p);
        }
    }

    public void deletePerson(Person p) throws SQLException {
        c.deletePerson(p);
    }

    public List<Person> getPeople(int amount,int offset) {
        return c.getPeople(amount, offset);
    }

    public Person getPerson(Long id) {
        return c.getPerson(id);
    }

    public List<Ort> searchOrt(int plz, String name) throws SQLException {
        return c.searchOrt(new Ort(plz, name));
    }

    public void saveOrt(Ort p) throws SQLException {
        if (p.getOid() != null){
            c.updateOrt(p);
        }else{
            c.insertOrt(p);
        }
    }

    public void deleteOrt(Ort p) throws SQLException {
        c.deleteOrt(p);
    }

    public List<Ort> getPlaces(int amount,int offset) {
        return c.getOrt(amount, offset);
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
    
    public void export() throws Exception{
        String workDir = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");
        File f = new File(workDir+sep+"PeopleExport.csv");
        if(f.exists())f.delete();
        PrintWriter pw = new PrintWriter(f);
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID");
        sb.append(";");
        sb.append("Name");
        sb.append(";");
        sb.append("Vorname");
        sb.append(";");
        sb.append("Strasse");
        sb.append(";");
        sb.append("Ort_Name");
        sb.append(";");
        sb.append("Ort_Plz");
        sb.append(";");
        sb.append("Telefon");
        sb.append(";");
        sb.append("Handy");
        sb.append(";");
        sb.append("Email");
        sb.append(";");
        sb.append("\n");
        
        Long count = c.countPeople();
        HashMap<Long,Long> exportMap = new HashMap<>();
        int perUnit = 100;
        int amount = (int) (count/perUnit);
        for(int i = 0;i<amount;i++){
            exportMap.put((long)i*perUnit,(long)i*perUnit+(perUnit-1));
        }
        long left = count - amount*perUnit;
        exportMap.put((long)amount*perUnit, (long)amount*perUnit+left-1);
        
        
        for(long l : exportMap.keySet()){
            List<Person> people = getPeople(perUnit, (int) l);
            for(Person p : people){
                Ort o = null;
                if(new Long(p.getOid()+"") != null)o = c.getOrt(p.getId());
                
                sb.append(p.getId());
                sb.append(";");
                sb.append(p.getName() != null ? p.getName(): "");
                sb.append(";");
                sb.append(p.getVorname() != null ? p.getVorname(): "");
                sb.append(";");
                sb.append(p.getStrasse() != null ? p.getStrasse(): "");
                sb.append(";");
                sb.append(o != null ? o.getName() : "");
                sb.append(";");
                sb.append(o != null ? o.getPlz(): "");
                sb.append(";");
                sb.append(p.getTelefon() != null ? p.getTelefon() : "");
                sb.append(";");
                sb.append(p.getHandy() != null ? p.getHandy(): "");
                sb.append(";");
                sb.append(p.getEmail() != null ? p.getEmail(): "");
                sb.append(";");
                sb.append("\n");
            }
            pw.append(sb.toString());
            pw.flush();
            sb = new StringBuilder();
        }
        
        pw.close();
        
        Desktop desktop = Desktop.getDesktop();
        desktop.open(f.getParentFile());
    }
}
