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
public enum FileTypeEnum {
    CSV("CSV",".csv"), XML("XML",".xml"), XLSX("Excel",".xlsx"), JSON("Json",".json");

    String description;
    String ext;
    FileTypeEnum(String description,String ext){
        this.description = description;
        this.ext = ext;
    }

    public String get() {
        return description;
    }
    
    public String getExtension(){
        return ext;
    }
    
    public String getDescription(){
        return description;
    }
    
    /**
     *
     * @return
     */
    public static String[] getExtensions(){
        ArrayList<String> values = new ArrayList<>();
        for(FileTypeEnum enumType : FileTypeEnum.values()){
            values.add(enumType.getExtension());
        }
        return values.stream().toArray(String[]::new);
    }
}
