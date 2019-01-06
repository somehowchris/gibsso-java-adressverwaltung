/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

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
    
    public static void main(String[] args) throws SQLException{
        JFrame adv = new AdressveraltunsForm();
        adv.setVisible(true);
    }
    
    
}
