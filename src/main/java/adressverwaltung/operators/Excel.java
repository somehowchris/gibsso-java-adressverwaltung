/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.operators;

import adressverwaltung.main;
import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author chris
 */
public class Excel {
    
    static String[] personColumns = new String[]{"ID","Name","Vorname","Strasse","Ort_Name","Ort_Plz","Telefon","Handy","Email"};
    static String[] ortColumns = new String[]{"ID","Name","Plz"};
    
    public static Workbook getDataSheetFromOrtList(List<Ort> ortlist,Workbook workbook){
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Towns");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        
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
    
    public static Workbook getDataSheetFromPeopleList(List<Person> people,Workbook workbook){
        // Create a Sheet
        Sheet sheet = workbook.createSheet("People");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        
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
            if(p.getOid() != null)o = main.io.connection.getOrt(p.getId());
            
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
}
