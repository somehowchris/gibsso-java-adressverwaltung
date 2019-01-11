/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.exports;

import adressverwaltung.models.Person;
import adressverwaltung.models.Ort;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import adressverwaltung.services.Service;

/**
 *
 * @author chris
 */
public class CsvExport extends Export{
    
    String csvData;
    
    public CsvExport(Service connection, List<Person> people) {
        super(connection, people);
    }
    
    public CsvExport(Service connection){
        super(connection);
    } 

    public CsvExport(Service connection, List<Person> people, List<Ort> twons) {
        super(connection, people, twons);
    }
    
    @Override
    public void render(){
        ArrayList<String> data = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(";", this.personColumns));
        sb.append("\n");
        this.people.forEach((p) -> {
            String line = "";
            Ort o = new Ort();
            if(p.getOid() != null)o = this.connection.getOrt(p.getOid());
            line+=p.getId()+";";
            line+=(p.getName() == null ? "" : p.getName())+";";
            line+=(p.getVorname() == null ? "" : p.getVorname())+";";
            line+=(p.getStrasse() == null ? "" : p.getStrasse());
            line+=(o == null ? "" : o.getName() == null ? "" : o.getName())+";";
            line+=(o == null ? "" : o.getPlz() > 0 ? "" : o.getPlz())+";";
            line+=(p.getTelefon() == null ? "" : p.getTelefon())+";";
            line+=(p.getHandy() == null ? "" : p.getHandy())+";";
            line+=(p.getEmail() == null ? "" : p.getEmail());
            data.add(line);
        });
        
        csvData = String.join("\n", data);
        
        csvData = sb.toString()+csvData;
    }

    @Override
    public void write() {
        try {
            PrintWriter pw = new PrintWriter(new File(this.path));
            pw.write(csvData);
            pw.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Couldnt write file: "+this.path);
        }
    }
    
    
}
