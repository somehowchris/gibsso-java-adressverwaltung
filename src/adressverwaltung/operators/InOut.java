/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.operators;

import adressverwaltung.models.Person;

import java.util.*;
import java.io.File;
import adressverwaltung.controller.Controller;
import adressverwaltung.controller.DataBaseController;
import adressverwaltung.controller.FileSystemController;
import adressverwaltung.models.Ort;
import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * A middleware class to operate with the Controller interface
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
        if (dotEnvFile.exists() && dotEnv.isEmpty() ? !(dotEnv = DotEnv.getDotEnv(home, sep)).isEmpty() : true){
            c = dotEnv.get("DATABASE_USE").equals("true") ? new DataBaseController(dotEnv) : new FileSystemController(home, sep);
        }else{
            c = new FileSystemController(home, sep);
        }
    }
    
    public List<Person> searchPerson(String vorname, String name) throws SQLException {
        return c.searchPerson(new Person(vorname, name));
    }

    public long savePerson(Person p) throws SQLException {
        if (p.getId() != null){
            return c.updatePerson(p);
        }else{
            return c.insertPerson(p);
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

    public List<Ort> getPlaces() {
        return c.getOrt();
    }

    public Ort getOrt(Long id) {
        return c.getOrt(id);
    }
    
    public long countOrt(){
        return c.countOrt();
    }
    
    public long countPeople(){
        return c.countPeople();
    }
    
    public void export() throws Exception{
        String workDir = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");    
        
        File f = new File(workDir+sep+"PeopleExport-"+getDate()+".csv");
        if(f.exists())f.delete();
        PrintWriter pw = new PrintWriter(f);
        
        pw.append(getHeader());
        pw.flush();
        
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
            pw.append(getDataFromPeopleArray(people));
            pw.flush();
        }
        pw.close();
        
        openDesktop(f.getParentFile());
    }
    
    public void searchExport(List<Person> people) throws Exception{
        String workDir = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");    
        
        File f = new File(workDir+sep+"SearchExport-"+getDate()+".csv");
        if(f.exists())f.delete();
        PrintWriter pw = new PrintWriter(f);
        
        pw.append(getHeader());
        pw.flush();
        
        pw.append(getDataFromPeopleArray(people));
        pw.flush();
        pw.close();
        
        openDesktop(f.getParentFile());
    }
    
    private void openDesktop(File dir) throws IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(dir);
    }
    
    private String getHeader(){
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
        return sb.toString();
    }
    
    private String getDataFromPeopleArray(List<Person> people){
        StringBuilder sb = new StringBuilder();
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
        return sb.toString();
    }
    private String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
