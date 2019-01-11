/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.exports;

import adressverwaltung.enums.PersonColumnEnum;
import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import adressverwaltung.services.Service;

/**
 *
 * @author chris
 */
public class JsonExport extends Export{
    
    String jsonData;
    
    public JsonExport(Service connection, List<Person> people) {
        super(connection, people);
    }
    
    public JsonExport(Service connection) {
        super(connection);
    }

    public JsonExport(Service connection, List<Person> people, List<Ort> towns) {
        super(connection, people, towns);
    }

    @Override
    public void render() {

        JSONArray jsonArray = new JSONArray();
        
        people.stream().map((p) -> {
            JSONObject obj = new JSONObject();
            Ort o = new Ort();
            if(p.getOid() != null)o = this.connection.getOrt(p.getOid());
            obj.put(PersonColumnEnum.ID.get(), p.getId());
            obj.put(PersonColumnEnum.LAST_NAME.get(), p.getName() == null ? "" : p.getName());
            obj.put(PersonColumnEnum.FIRST_NAME.get(), p.getVorname() == null ? "" : p.getVorname());
            obj.put(PersonColumnEnum.STREET.get(), p.getStrasse() == null ? "" : p.getStrasse());
            obj.put(PersonColumnEnum.TOWN_NAME.get(), o == null ? "" : o.getName() == null ? "" : o.getName());
            obj.put(PersonColumnEnum.TOWN_PLZ.get(), o == null ? "" : o.getPlz() > 0 ? "" : o.getPlz());
            obj.put(PersonColumnEnum.PHONE.get(), p.getTelefon() == null ? "" : p.getTelefon());
            obj.put(PersonColumnEnum.MOBILE.get(), p.getHandy() == null ? "" : p.getHandy());
            obj.put(PersonColumnEnum.EMAIL.get(), p.getEmail() == null ? "" : p.getEmail());
            return obj;
        }).forEachOrdered((obj) -> {
            jsonArray.add(obj);
        });
        JsonParser parser = new JsonParser();
        JsonArray json = parser.parse(jsonArray.toJSONString()).getAsJsonArray();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        jsonData = gson.toJson(json);
    }
    
    @Override
    public void write() {
        try {
            PrintWriter pw = new PrintWriter(new File(this.path));
            pw.write(jsonData);
            pw.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Couldnt write file: "+this.path);
        }
    }
    
}
