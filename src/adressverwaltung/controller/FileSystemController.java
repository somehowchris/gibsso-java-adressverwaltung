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
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class FileSystemController implements Controller{

    String fsdbdir;
    
    
    // TODO add redis map
    HashMap<String, Object> redismap = new HashMap<>();
    
    public FileSystemController(String dir,String sep) {
        if(!new File(dir+sep+".fsdb"+sep).exists()) new File(dir+sep+".fsdb"+sep).mkdir();
        fsdbdir = dir+sep+".fsdb"+sep;
    }
    
    @Override
    public Person getPerson(Long id) {
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
            return p;
        } catch (IOException e) {
        }
        }
        return null;
    }

    @Override
    public Ort getOrt(Long id) {
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
            
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return null;
    }

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

    @Override
    public void updatePerson(Person person) {
        String linesep = System.getProperty("line.separator");
        String data = person.getId()+linesep+person.getName()+linesep+person.getVorname()+linesep+person.getStrasse()+linesep+person.getOid()+linesep+person.getTelefon()+linesep+person.getHandy()+linesep+person.getEmail();
        String file = fsdbdir+person.getId()+".person";
        writeData(data, file);
    }
    
    @Override
    public void updateOrt(Ort ort) {
        String linesep = System.getProperty("line.separator");
        String data = ort.getOid()+linesep+ort.getName()+linesep+ort.getPlz();
        String file = fsdbdir+ort.getOid()+".ort";
        writeData(data, file);
    }

    @Override
    public void deleteOrt(Ort ort) {
        File f = new File(fsdbdir+ort.getOid()+".ort");
        long references = getPeople(new Integer(countPeople()+""), 0).stream().filter(el -> el.getOid() == ort.getOid()).count();
        if(f.exists() && references == 0 ){
            f.delete();
        }else{
            throw new Error("References on this place still exist "+ort.getOid());
        }
    }

    @Override
    public void deletePerson(Person person) {
        File f = new File(fsdbdir+person.getId()+".person");
        if(f.exists())f.delete();
    }

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

    @Override
    public Long countOrt() {
        Long total = new Long(searchInDir(".ort").length);
        return total;
    }

    @Override
    public Long countPeople() {
        Long total = new Long(searchInDir(".person").length);
        return total;
    }
    
    private String[] searchInDir(String contains){
        return new File(fsdbdir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(contains);
            }
        });
    }
    
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
}
