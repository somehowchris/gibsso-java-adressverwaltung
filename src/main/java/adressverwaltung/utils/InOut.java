/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.utils;

import adressverwaltung.models.Person;

import java.util.*;
import java.io.File;
import adressverwaltung.services.DatabaseService;
import adressverwaltung.services.FileSystemService;
import adressverwaltung.errors.CanNotConnectToDatabaseError;
import adressverwaltung.models.Ort;
import adressverwaltung.enums.DotEnvEnum;
import adressverwaltung.enums.SystemPropertyEnum;
import adressverwaltung.exports.Export;
import java.sql.SQLException;
import adressverwaltung.services.Service;

/**
 * A middleware class to operate with the Service interface
 * @author Nicola Temporal
 */
public class InOut {

    public Service connection = null;
    String home = SystemPropertyEnum.USER_HOME.get();
    public InOut(HashMap<String, String> dotEnv) throws SQLException, CanNotConnectToDatabaseError {
        if(dotEnv == null) dotEnv = new HashMap<>();
        File dotEnvFile = new File(home);
        if (dotEnvFile.exists() && dotEnv.isEmpty() ? !(dotEnv = DotEnv.getDotEnv()).isEmpty() : true){
            if(dotEnv.containsKey(DotEnvEnum.DB_USE.get()) && DotEnv.containsAllKeys(dotEnv)){
                if(dotEnv.get(DotEnvEnum.DB_USE.get()).equals("true")){
                    if(MySQLConnection.verify(dotEnv.get(DotEnvEnum.HOST.get()), dotEnv.get(DotEnvEnum.PASSWORD.get()), dotEnv.get(DotEnvEnum.TABLE_NAME.get()), dotEnv.get(DotEnvEnum.PORT.get()), dotEnv.get(DotEnvEnum.USER.get()))){
                        connection = new DatabaseService(dotEnv);
                    }else{
                        throw new CanNotConnectToDatabaseError();
                    }
                }else if(dotEnv.get(DotEnvEnum.DB_USE.get()).equals("false")){
                    connection = new FileSystemService(home, SystemPropertyEnum.FILE_SEPERATOR.get());
                }else{
                    throw new CanNotConnectToDatabaseError();
                }
            }else{
                connection = new FileSystemService(home, SystemPropertyEnum.FILE_SEPERATOR.get());
            }
        }else{
            connection = new FileSystemService(home, SystemPropertyEnum.FILE_SEPERATOR.get());
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
    
    public void exportAll() throws Exception{
        Export export = new Export(connection);
        export = export.configure();
        if(export == null)return;
        export.render();
        export.write();
        export.open();
    }
    
    public void searchExport(List<Person> people) throws Exception{
        Export export = new Export(connection, people);
        export = export.configure();
        if(export == null)return;
        export.render();
        export.write();
        export.open();
    }
}
