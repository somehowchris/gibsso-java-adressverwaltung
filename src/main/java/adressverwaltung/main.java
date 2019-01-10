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
import java.util.HashMap;

/**
 * Main operator class
 * @author Christof Weickhardt, Nicola Temporal
 */
// TODO setup & join flow
public class main {
    public static InOut io;
    public static AdressveraltunsForm af;
    public static ConnectionForm cn;
    public static OrtForm of;
    
    /**
     * @param args the command line arguments
     * @throws SQLException throws a exception if needed
     */
    public static void main(String[] args) throws SQLException{
        io = new InOut(null);
        af = new AdressveraltunsForm(io);
        of = new OrtForm(io);
        cn = new ConnectionForm();
        af.setVisible(true);
    }
    
    public static boolean setupConnection(HashMap<String,String> connection){
        try {
            io = new InOut(connection);
            cn.setVisible(false);
            af = new AdressveraltunsForm(io);
            of = new OrtForm(io);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static void viewAdressverwaltung(){
        af.setVisible(true);
        af.requestFocus();
        
        cn.setVisible(false);
        of.setVisible(false);
    }
    
    public static void viewOrtverwlatung(){
        of.setVisible(true);
        of.requestFocus();
        
        cn.setVisible(false);
        af.setVisible(false);
    }
    
    public static void viewConnectionSettings(){
        cn.setVisible(true);
        cn.requestFocus();
        
        of.setVisible(false);
        af.setVisible(false);
    }
}
