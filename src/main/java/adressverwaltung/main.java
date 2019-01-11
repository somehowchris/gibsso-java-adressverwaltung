/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

import adressverwaltung.errors.CanNotConnectToDatabaseError;
import adressverwaltung.forms.OrtForm;
import adressverwaltung.forms.ConnectionForm;
import adressverwaltung.forms.AdressveraltunsForm;
import adressverwaltung.utils.DotEnv;
import adressverwaltung.utils.InOut;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main operator class
 * @author Christof Weickhardt, Nicola Temporal
 */
public class main {
    public static ConnectionForm cn;
    public static InOut io;
    public static AdressveraltunsForm af;
    public static OrtForm of;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            io = new InOut(null);
            
            cn = new ConnectionForm();
            
            af = new AdressveraltunsForm(io);
            
            of = new OrtForm(io);
            if(DotEnv.getDotEnv().keySet().contains("DATABASE_USE")){
                af.setVisible(true);
            }else{
                cn.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CanNotConnectToDatabaseError ex) {
            viewConnectionSettings();
        }
        
    }
    
    public static boolean setupConnection(HashMap<String,String> connection){
        try {
            io = new InOut(connection);
            cn.setVisible(false);
            af = new AdressveraltunsForm(io);
            of = new OrtForm(io);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static void viewAdressverwaltung() throws SQLException, CanNotConnectToDatabaseError{
        if(af == null)af = new AdressveraltunsForm(io);
        af.setVisible(true);
        af.requestFocus();
        
        if(cn != null)cn.setVisible(false);
        if(of != null)of.setVisible(false);
    }
    
    public static void viewOrtverwlatung() throws CanNotConnectToDatabaseError, SQLException{
        if(of == null)of = new OrtForm(io);
        of.setVisible(true);
        of.requestFocus();
        
        if(cn != null)cn.setVisible(false);
        if(af != null)af.setVisible(false);
    }
    
    public static void viewConnectionSettings(){
        if(cn == null)cn = new ConnectionForm();
        cn.setVisible(true);
        cn.requestFocus();
        
        if(of != null)of.setVisible(false);
        if(of != null)af.setVisible(false);
    }
}
