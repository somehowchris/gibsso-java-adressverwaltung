/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.enums;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public enum SystemPropertyEnum {
    
    USER_HOME (System.getProperty("user.home")), USER_DIR (System.getProperty("user.dir")), FILE_SEPERATOR (System.getProperty("file.separator")), JAVA_HOME (System.getProperty("java.home")), USER_NAME(System.getProperty("user.name")), LINE_SEPERATOR(System.getProperty("line.separator"));
    
    String value;
    SystemPropertyEnum(String values){
        this.value = values;
    }

    public String get() {
        return value;
    }
    
    /**
     *
     * @return
     */
    public static String[] getValues(){
        ArrayList<String> values = new ArrayList<>();
        for(SystemPropertyEnum enumType : SystemPropertyEnum.values()){
            values.add(enumType.get());
        }
        return values.stream().toArray(String[]::new);
    }
}
