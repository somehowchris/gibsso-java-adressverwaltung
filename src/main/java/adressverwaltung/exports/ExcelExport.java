/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.exports;

import adressverwaltung.main;
import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import adressverwaltung.services.Service;

/**
 *
 * @author Christof Weickhardt
 */
public class ExcelExport extends Export{
    // Create a Workbook
    Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
    public ExcelExport(Service connection, List<Person> people) {
        super(connection, people);
    }
    
    public ExcelExport(Service connection) {
        super(connection);
    }

    public ExcelExport(Service connection, List<Person> people, List<Ort> towns) {
        super(connection, people, towns);
    }
    
    @Override
    public void render(){
        workbook = getTownDataSheet(workbook);
        workbook = getPeopleDataSheet(workbook);
    }
    
    @Override
    public void write(){
        File f = new File(this.path);
        if(f.exists())f.delete();
        try {
            //Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(f);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Couldnt write file: "+this.path);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Couldnt write file: "+this.path);
        }
        
    }
    
    public Workbook getTownDataSheet(Workbook workbook){
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
        for(int i = 0; i < this.ortColumns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(ortColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Ort o : this.towns){
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
    
    public Workbook getPeopleDataSheet(Workbook workbook){
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
        
        for(Person p : this.people){
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
