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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A middleware class to operate with the Controller interface
 * @author Nicola Temporal
 */
// TODO document 
public class InOut {

    public Controller c = null;
    
    public String[] personColumns = new String[]{"ID","Name","Vorname","Strasse","Ort_Name","Ort_Plz","Telefon","Handy","Email"};
    public String[] ortColumns = new String[]{"ID","Name","Plz"};

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
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        workbook = getDataSheetFromOrtList(c.getOrt(), workbook);
        workbook = getDataSheetFromPeopleList(c.getPeople(new Integer(c.countPeople()+""), 0), workbook);
        
        String workDir = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");    
        
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
                ortlist.add(c.getOrt(p.getId()));
            }
        }
        
        workbook = getDataSheetFromPeopleList(people, workbook);
        workbook = getDataSheetFromOrtList(ortlist, workbook);
        
        String workDir = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");    
        
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
    
    
    private Workbook getDataSheetFromOrtList(List<Ort> ortlist,Workbook workbook){
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Towns");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.CORAL.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for(int i = 0; i < ortColumns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(ortColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        
        for(Ort o : ortlist){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(o.getOid());
            row.createCell(1).setCellValue(o.getName() != null ? o.getName(): "");
            row.createCell(2).setCellValue(!"".equals(o.getPlz()+"") ? o.getPlz()+"" : "");
        }
        // Resize all columns to fit the content size
        for(int i = 0; i < ortColumns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
    
    private Workbook getDataSheetFromPeopleList(List<Person> people,Workbook workbook){
        // Create a Sheet
        Sheet sheet = workbook.createSheet("People");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.CORAL.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for(int i = 0; i < personColumns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(personColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        
        for(Person p : people){
            Ort o = null;
            if(new Long(p.getOid()+"") != null)o = c.getOrt(p.getId());
            
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(p.getId());
            row.createCell(1).setCellValue(p.getName() != null ? p.getName(): "");
            row.createCell(2).setCellValue(p.getVorname() != null ? p.getVorname(): "");
            row.createCell(3).setCellValue(p.getStrasse() != null ? p.getStrasse(): "");
            row.createCell(4).setCellValue(o != null ? o.getName() : "");
            row.createCell(5).setCellValue(o != null ? o.getPlz()+"" : "");
            row.createCell(6).setCellValue(p.getTelefon() != null ? p.getTelefon() : "");
            row.createCell(7).setCellValue(p.getHandy() != null ? p.getHandy(): "");
            row.createCell(7).setCellValue(p.getEmail() != null ? p.getEmail(): "");
        }
        // Resize all columns to fit the content size
        for(int i = 0; i < personColumns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
    
    private String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
