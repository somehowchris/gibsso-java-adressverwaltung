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
public enum OrtColumnEnum {
    ID("ID"), NAME("Name"), PLZ("Plz");
    
    String value;
    OrtColumnEnum(String values){
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
        for(OrtColumnEnum enumType : OrtColumnEnum.values()){
            values.add(enumType.get());
        }
        return values.stream().toArray(String[]::new);
    }
}
