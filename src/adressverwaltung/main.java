/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

/**
 *
 * @author chris
 */
public class main {
    public static InOut c;
    public static AdressveraltunsForm a;
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
        c = new InOut("jdbc:mysql://localhost:3306/Adressverwaltung", "root", "root");
        a = new AdressveraltunsForm(c);
        a.setVisible(true);
    }
    
    
}
