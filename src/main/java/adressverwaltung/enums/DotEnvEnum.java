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
public enum DotEnvEnum {

    USER("DATABASE_USER"), PASSWORD("DATABASE_PASSWORD"), HOST("DATABASE_HOST"), PORT("DATABASE_PORT"), TABLE_NAME("DATABASE_NAME"), DB_USE("DATABASE_USE");
    
    String value;
    DotEnvEnum(String values){
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
        for(DotEnvEnum enumType : DotEnvEnum.values()){
            values.add(enumType.get());
        }
        return values.stream().toArray(String[]::new);
    }
}
