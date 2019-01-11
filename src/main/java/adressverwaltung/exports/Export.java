/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.exports;

import adressverwaltung.enums.FileTypeEnum;
import adressverwaltung.enums.OrtColumnEnum;
import adressverwaltung.enums.PersonColumnEnum;
import adressverwaltung.enums.SystemPropertyEnum;
import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import adressverwaltung.utils.FileTypeFilter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import adressverwaltung.services.Service;

/**
 *
 * @author Christof Weickhardt
 */
public class Export {
    
    Service connection;
    ArrayList<Person> people;
    ArrayList<Ort> towns;
    String[] personColumns = PersonColumnEnum.getValues();
    String[] ortColumns = OrtColumnEnum.getValues();
    String path;
    public Export(Service connection, List<Person> people){
        this.connection = connection;
        this.people = (ArrayList<Person>) people;
        this.towns = getTowns();
    }
    
    public Export(Service connection, List<Person> people, List<Ort> towns){
        this.connection = connection;
        this.people = (ArrayList<Person>) people;
        this.towns = (ArrayList<Ort>) towns;
    }
    
    public Export(Service connection){
        this.connection = connection;
        this.towns = (ArrayList<Ort>) this.connection.getOrt();
        this.people = (ArrayList<Person>) this.connection.getPeople(new Integer(this.connection.countPeople()+""), 0);
    }
    
    public Export configure(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(SystemPropertyEnum.USER_DIR.get()));
        chooser.setDialogTitle("Please choose a location to export your file to");
        for(FileTypeEnum fileType : FileTypeEnum.values()){
            chooser.addChoosableFileFilter(new FileTypeFilter(fileType));
        }
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
          final String extension = FilenameUtils.getExtension(chooser.getSelectedFile().getName());
          if("".equals(extension)){
            path = chooser.getSelectedFile().getAbsolutePath()+((FileTypeFilter)chooser.getFileFilter()).getExtension();
            return createExportConfiguration((FileTypeFilter)chooser.getFileFilter());
          }else if(Arrays.asList(FileTypeEnum.values()).stream().filter(el -> el.getExtension().equalsIgnoreCase("."+extension)).count() == 1){
              Export e = null;
              if(("."+extension.toLowerCase()).equalsIgnoreCase(FileTypeEnum.CSV.getExtension())){
                  e = new ExcelExport(connection, people, towns);
              }else if(("."+extension.toLowerCase()).equalsIgnoreCase(FileTypeEnum.JSON.getExtension())){
                  e = new ExcelExport(connection, people, towns);
              }else if(("."+extension.toLowerCase()).equalsIgnoreCase(FileTypeEnum.XLSX.getExtension())){
                  e = new ExcelExport(connection, people, towns);
              }else if(("."+extension.toLowerCase()).equalsIgnoreCase(FileTypeEnum.XML.getExtension())){
                  e = new XmlExport(connection, people, towns);
              }
              e.setPath(chooser.getSelectedFile().getAbsolutePath());
              return e;
          }else{
            if(extension.length() <= 4)path = chooser.getSelectedFile().getAbsolutePath().replaceAll("."+extension, "")+((FileTypeFilter)chooser.getFileFilter()).getExtension();
            if(extension.length() > 4)path = chooser.getSelectedFile().getAbsolutePath()+((FileTypeFilter)chooser.getFileFilter()).getExtension();
            return createExportConfiguration((FileTypeFilter)chooser.getFileFilter());
          }
        } else {
            return null;
        }
    }
    
    public void render(){}
    
    public void write(){}
    
    public void open(){
        try {
            openDirectoryOfFile(new File(path));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Couldnt open file: "+path);
        }
    }
    
    private void openDirectoryOfFile(File dir) throws IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(dir.getParentFile());
    }
    
    private boolean containsOrt(ArrayList<Ort> ortlist, Long ort){
        if(ort != null){
            if (ortlist.stream().anyMatch((o) -> (Objects.equals(o.getOid(), ort)))) {
                return true;
            }
        }
        return false;
    }
    
    private ArrayList<Ort> getTowns(){
        ArrayList<Ort> ortlist = new ArrayList<>();
        people.stream().filter((p) -> (containsOrt(ortlist,p.getOid()))).forEachOrdered((p) -> {
            ortlist.add(connection.getOrt(p.getId()));
        });
        return ortlist;
    }
    
    public void setPath(String path){
        this.path = path;
    }
    
    private Export createExportConfiguration(FileTypeFilter filter){
        Export e = null;
        switch(filter.getFileType()){
            case CSV:
                e = new CsvExport(connection, people, towns);
                break;
            case JSON:
                e = new JsonExport(connection, people, towns);
                break;
            case XLSX:
                e = new ExcelExport(connection, people, towns);
                break;
            case XML:
                e = new XmlExport(connection, people, towns);
                break;
        }
        e.setPath(path);
        return e;
    }

    public String getPath() {
        return path;
    }
    
    
}
