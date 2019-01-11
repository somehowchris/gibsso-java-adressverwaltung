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
public enum PersonColumnEnum {
    ID("ID"), LAST_NAME("Name"), FIRST_NAME("Vorname"), STREET("Strasse"),TOWN_NAME("Ort_Name"), TOWN_PLZ("Ort_Plz"),PHONE("Telefon"),MOBILE("Handy"),EMAIL("Email");
    
    String value;
    PersonColumnEnum(String values){
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
        for(PersonColumnEnum enumType : PersonColumnEnum.values()){
            values.add(enumType.get());
        }
        return values.stream().toArray(String[]::new);
    }
}
