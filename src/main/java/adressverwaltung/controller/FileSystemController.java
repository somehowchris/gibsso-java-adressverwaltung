/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.controller;

import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A File System Controller managing the data set in a .fsdb folder of the users home
 * @author Christof Weickhardt
 */
public class FileSystemController implements Controller{

    String fsdbdir;
    
    HashMap<Long, Person> redismapPeople = new HashMap<>();
    HashMap<Long, Ort> redismapOrt = new HashMap<>();
    
    public FileSystemController(String dir,String sep) {
        if(!new File(dir+sep+".fsdb"+sep).exists()) new File(dir+sep+".fsdb"+sep).mkdir();
        fsdbdir = dir+sep+".fsdb"+sep;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Person getPerson(Long id) {
        if(redismapPeople.containsKey(id))return redismapPeople.get(id);
        File f = new File(fsdbdir+id+".person");
        if(f.exists()){
            BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            int i = 0;
            Person p = new Person();
            while ((line = reader.readLine()) != null) {
                switch(i){
                    case 0:
                        p.setPid(new Long(line));
                        break;
                    case 1:
                        p.setName(line);
                        break;
                    case 2:
                        p.setVorname(line);
                        break;
                    case 3:
                        p.setStrasse(line);
                        break;
                    case 4:
                        p.setOid(new Integer(line));
                    case 5:
                        p.setTelefon(line);
                        break;
                    case 6:
                        p.setHandy(line);
                        break;
                    case 7:
                        p.setEmail(line);
                        break;
                }
                i++;
            }
            reader.close();
            redismapPeople.put(id, p);
            return p;
        } catch (IOException e) {
        }
        }
        return null;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Ort getOrt(Long id) {
        if(redismapOrt.containsKey(id))return redismapOrt.get(id);
        File f = new File(fsdbdir+id+".ort");
        if(f.exists()){
            BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            int i = 0;
            Ort o = new Ort();
            while ((line = reader.readLine()) != null) {
                switch(i){
                    case 0:
                        o.setOid(new Long(line));
                        break;
                    case 1:
                        o.setName(line);
                        break;
                    case 2:
                        o.setPlz(new Integer(line));
                        break;
                }
                i++;
            }
            reader.close();
            redismapOrt.put(id,o);
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return null;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public ArrayList<Person> searchPerson(Person person) {
        String[] files = searchInDir(".person");
        ArrayList<Person> people = new ArrayList<>();
        for(String file : files){
            Person p = getPerson(new Long(file.replace(".person", "")));
            if(p.getName().contains(person.getName()) && p.getVorname().contains(person.getVorname()))people.add(p);
        }
        return people;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public ArrayList<Ort> searchOrt(Ort ort) {
       String[] files = searchInDir(".ort");
        ArrayList<Ort> ortlist = new ArrayList<>();
        for(String file : files){
            Ort o = getOrt(new Long(file.replace(".ort", "")));
            if(ort.getName().contains(ort.getName()))ortlist.add(o);
        }
        return ortlist;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Long insertPerson(Person person) {
        Long id = 0L;
        if(getPeople(1,new Integer(countPeople()+"")-2).size() > 0) id = getPeople(1,new Integer(countPeople()+"")-2).size()+1L;
        while(new File(fsdbdir+id+".person").exists()){
           id++;
        }
        person.setId(id);
        updatePerson(person);
        return id;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Long insertOrt(Ort ort) {
       Long id = 0L;
       if(getOrt(1,new Integer(countOrt()+"")-2).size() > 0) id = getOrt(1,new Integer(countOrt()+"")-2).get(0).getOid()+1L;
       while(new File(fsdbdir+id+".ort").exists()){
           id++;
       }
       ort.setOid(id);
       updateOrt(ort);
       return id;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Long updatePerson(Person person) {
        String linesep = System.getProperty("line.separator");
        String data = person.getId()+linesep+person.getName()+linesep+person.getVorname()+linesep+person.getStrasse()+linesep+person.getOid()+linesep+person.getTelefon()+linesep+person.getHandy()+linesep+person.getEmail();
        String file = fsdbdir+person.getId()+".person";
        writeData(data, file);
        redismapPeople.put(person.getId(), person);
        return person.getId();
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Long updateOrt(Ort ort) {
        String linesep = System.getProperty("line.separator");
        String data = ort.getOid()+linesep+ort.getName()+linesep+ort.getPlz();
        String file = fsdbdir+ort.getOid()+".ort";
        writeData(data, file);
        redismapOrt.put(ort.getOid(), ort);
        return ort.getOid();
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void deleteOrt(Ort ort) {
        File f = new File(fsdbdir+ort.getOid()+".ort");
        long references = getPeople(new Integer(countPeople()+""), 0).stream().filter(el -> {
            return el.getOid().equals(ort.getOid());
        }).count();
        if(f.exists() && references == 0 ){
            f.delete();
            redismapOrt.remove(ort.getOid());
        }else{
            throw new Error("References on this place still exist "+ort.getOid());
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void deletePerson(Person person) {
        File f = new File(fsdbdir+person.getId()+".person");
        if(f.exists()){
            f.delete();
            redismapPeople.remove(person.getId());
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public ArrayList<Person> getPeople(int amount, int offset) {
        String[] consideredFiles = searchInDir(".person");
        
        ArrayList<Person> ortlist = new ArrayList<>();
        try{
            for(int i = offset;i<offset+amount;i++){
                if(consideredFiles.length > i){
                    ortlist.add(getPerson(new Long(consideredFiles[i].replace(".person", ""))));
                }
            }
        }catch(Exception e){}
        return ortlist;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public ArrayList<Ort> getOrt() {
        int offset = 0;
        int amount = new Integer(countOrt()+"");
        String[] consideredFiles = searchInDir(".ort");
        
        ArrayList<Ort> ortlist = new ArrayList<>();
        try{
            for(int i = offset;i<offset+amount;i++){
                if(consideredFiles.length > i){
                    ortlist.add(getOrt(new Long(consideredFiles[i].replace(".ort", ""))));
                }
            }
        }catch(Exception e){}
        return ortlist;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public ArrayList<Ort> getOrt(int amount, int offset) {
        String[] consideredFiles = searchInDir(".ort");
        
        ArrayList<Ort> ortlist = new ArrayList<>();
        try{
            for(int i = offset;i<offset+amount;i++){
                if(consideredFiles.length > i){
                    ortlist.add(getOrt(new Long(consideredFiles[i].replace(".ort", ""))));
                }
            }
        }catch(Exception e){}
        return ortlist;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public Long countOrt() {
        Long total = new Long(searchInDir(".ort").length);
        return total;
    }
    
    /**
     * Uses the Hibernate Library to count the people data set in your data base
    * {@inheritDoc}
    */
    @Override
    public Long countPeople() {
        Long total = new Long(searchInDir(".person").length);
        return total;
    }
    
    /**
     * Searches for files wich contain a spefic character chain
    * @param contains String welcher nach welchem im Namen aller vorhanden Dateien im fsdb Ordner gefiltert und gesucht werden soll
    * @return Alle Dateinamen welche die angegebene Karakterreihenfolge beinhalten im fsdb ordner
    * @see FilenameFilter
    */
    private String[] searchInDir(String contains){
        return new File(fsdbdir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(contains);
            }
        });
    }
    
    /**
     * Writes data to the file system
    * @param data Angabe was geschrieben werden soll
    * @param file Angabe an welchem Ort die Daten geschrieben werden sollen
    * @see BufferedWriter
    * @see FileWriter
    */
    private void writeData(String data,String file){
        BufferedWriter br = null;
        FileWriter fr = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(dataWithNewLine);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Cleans all the local data on the file system related to this application
    * @see BufferedWriter
    * @see FileWriter
    */
    public void clean(){
        String[] ortlist = searchInDir(".ort");
        String[] people = searchInDir(".person");
        for(String pstrg : people){
            Person p = new Person();
            p.setId(new Long(pstrg.replace(".person", "")));
            deletePerson(p);
        }
        
        for(String ostrg : ortlist){
            Ort o = new Ort();
            o.setOid(new Long(ostrg.replace(".ort", "")));
            deleteOrt(o);
        }
    }
}
