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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A middleware class to operate with the Controller interface
 * @author Nicola Temporal
 */
// TODO document 
public class InOut {

    public Controller connection = null;
    String sep = System.getProperty("file.separator");
    String home = System.getProperty("user.home");
    String workDir = System.getProperty("user.dir");   
    
    public InOut(HashMap<String, String> dotEnv) throws SQLException {
        
        if(!new File(home).canRead()) home = System.getProperty("user.dir");
        if(dotEnv == null) dotEnv = new HashMap<>();
        File dotEnvFile = new File(home);
        if (dotEnvFile.exists() && dotEnv.isEmpty() ? !(dotEnv = DotEnv.getDotEnv(home, sep)).isEmpty() : true){
            connection = dotEnv.get("DATABASE_USE").equals("true") ? new DataBaseController(dotEnv) : new FileSystemController(home, sep);
        }else{
            connection = new FileSystemController(home, sep);
        }
    }
    
    public List<Person> searchPerson(String vorname, String name) throws SQLException {
        return connection.searchPerson(new Person(vorname, name));
    }

    public long savePerson(Person p) throws SQLException {
        if (p.getId() != null){
            return connection.updatePerson(p);
        }else{
            return connection.insertPerson(p);
        }
    }

    public void deletePerson(Person p) throws SQLException {
        connection.deletePerson(p);
    }

    public List<Person> getPeople(int amount,int offset) {
        return connection.getPeople(amount, offset);
    }

    public Person getPerson(Long id) {
        return connection.getPerson(id);
    }

    public List<Ort> searchOrt(int plz, String name) throws SQLException {
        return connection.searchOrt(new Ort(plz, name));
    }

    public long saveOrt(Ort p) throws SQLException {
        if (p.getOid() != null){
            return connection.updateOrt(p);
        }else{
            return connection.insertOrt(p);
        }
    }

    public void deleteOrt(Ort p) throws Error {
        connection.deleteOrt(p);
    }

    public List<Ort> getPlaces() {
        return connection.getOrt();
    }

    public Ort getOrt(Long id) {
        return connection.getOrt(id);
    }
    
    public long countOrt(){
        return connection.countOrt();
    }
    
    public long countPeople(){
        return connection.countPeople();
    }
    
    public List<Ort> getPlaces(int amount,int offset) {
        return connection.getOrt(amount, offset);
    }
    
    public void export() throws Exception{
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        workbook = Excel.getDataSheetFromOrtList(connection.getOrt(), workbook);
        workbook = Excel.getDataSheetFromPeopleList(connection.getPeople(new Integer(connection.countPeople()+""), 0), workbook);
        
        File f = new File(workDir+sep+"Export-"+getDate()+".xlsx");
        if(f.exists())f.delete();
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(f);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        
        openDesktop(f.getParentFile());
    }
    
    public void searchExport(List<Person> people) throws Exception{
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        
        ArrayList<Ort> ortlist = new ArrayList<>();
        
        for(Person p : people){
            if(containsOrt(ortlist,p.getOid())){
                ortlist.add(connection.getOrt(p.getId()));
            }
        }
        
        workbook = Excel.getDataSheetFromPeopleList(people, workbook);
        workbook = Excel.getDataSheetFromOrtList(ortlist, workbook);  
        
        File f = new File(workDir+sep+"SearchExport-"+getDate()+".xlsx");
        if(f.exists())f.delete();
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(f);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        openDesktop(f.getParentFile());
    }
    
    private boolean containsOrt(ArrayList<Ort> ortlist, Long ort){
        if(ort != null){
            if (ortlist.stream().anyMatch((o) -> (Objects.equals(o.getOid(), ort)))) {
                return true;
            }
        }
        return false;
    }
    
    private void openDesktop(File dir) throws IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(dir);
    }
    
    private String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
