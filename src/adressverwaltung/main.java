/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

import adressverwaltung.forms.OrtForm;
import adressverwaltung.forms.ConnectionForm;
import adressverwaltung.forms.AdressveraltunsForm;
import adressverwaltung.operators.InOut;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 * Main operator class
 * @author chris
 */
public class main {
    public static InOut c;
    public static AdressveraltunsForm af;
    public static ConnectionForm cn;
    public static OrtForm of;
    /**
     * @param args the command line arguments
     * @throws SQLException throws a exception if needed
     */
    public static void main(String[] args) throws SQLException{
        JFrame adv = new AdressveraltunsForm();
        adv.setVisible(true);
    }
    
    
}
