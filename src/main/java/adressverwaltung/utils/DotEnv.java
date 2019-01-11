/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.operators;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A small class to store and update values of a .env file in the users home directory
 * @author chris
 */
public class DotEnv {
    static String sep = System.getProperty("file.separator");
    static String dir = System.getProperty("user.home");
    /**
     * @return A list of key and values paires found in the .env file
     * @see BufferedReader
     * @see HashMap
     */
    public static HashMap<String, String> getDotEnv() {
        HashMap<String, String> values = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dir + sep + ".env"));
            List<String> keys = Arrays.asList("DATABASE_USER", "DATABASE_PASSWORD", "DATABASE_HOST", "DATABASE_PORT", "DATABASE_NAME","DATABASE_USE");
            String line = reader.readLine();
            while (line != null) {
                if (keys.contains(line.split("=")[0])) values.put(line.split("=")[0], line.split("=").length > 1 ? line.split("=")[1] : "");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
        }
        return values;
    }
    /**
     * A function to store data which the user put
     * @throws FileNotFoundException In the event of not having a .env file
     * @throws IOException In the event of having to the needed rights on the file
     * @param dotEnv A key values pair list to be set in the .env file of the current users home directory
     */
    public static void setDotEnv(HashMap<String,String> dotEnv) throws FileNotFoundException, IOException {
        if(!new File(dir).canRead()) dir = System.getProperty("user.dir");
        File f = new File(dir + sep + ".env");
        String data = "";
        BufferedReader reader = new BufferedReader(new FileReader(f));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains("=")){
                if(!dotEnv.containsKey(currentLine.split("=")[0])){
                    data+=currentLine+System.getProperty("line.separator");;
                }
            }else{
                data+=currentLine+System.getProperty("line.separator");;
            }
        }
        
        for(String key : dotEnv.keySet()){
            data+= key+"="+dotEnv.get(key)+System.getProperty("line.separator");
        }   
        
        BufferedWriter br = null;
        FileWriter fr = null;
 
        try{
            fr = new FileWriter(f);
            br = new BufferedWriter(fr);
            br.append(data);
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
