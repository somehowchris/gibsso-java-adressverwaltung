/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung;

import adressverwaltung.models.Person;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Nicola Temporal
 */
public class InOut {
    
    private Connection c = null;
    
    public InOut(String connectionString,String user,String password) {
        try{    
            Class.forName("com.mysql.jdbc.Driver");
            if (this.c == null) {
                this.c = DriverManager.getConnection(connectionString, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public ArrayList<Person> searchPerson(String vorname, String name) throws SQLException {
        ArrayList<Person> rows  = new ArrayList<>();
        
            Statement stmt = c.createStatement();
            String query = "select pid, name, vorname, strasse, oid, telefon, handy, email from Adresses where name like '%"+name+"%' and vorname like '%"+vorname+"%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Person p = new Person(rs.getInt("pid"),rs.getString("name"),rs.getString("vorname"),rs.getString("strasse"),rs.getInt("oid"),rs.getString("telefon"),rs.getString("handy"),rs.getString("email"));
                rows.add(p);
            }

        return rows;
    }
    
    public void savePerson(Person p) throws SQLException
    {
        if(p.getPid() == -1)
        {
            Statement stmt = c.createStatement();
            String query = "insert into Adresses (name, vorname, strasse, oid, telefon, handy, email) values ("
                            + "'"+p.getName()+"',"
                            + "'"+p.getVorname()+"',"
                            + "'"+p.getStrasse()+"',"
                            + "null"+","
                            + "'"+p.getTelefon()+"',"
                            + "'"+p.getHandy()+"',"
                            + "'"+p.getEmail()+"')";
            stmt.execute(query);
        }else{
            Statement stmt = c.createStatement();
            String query = "update Adresses set"
                            + " name = '"+p.getName()+"',"
                            + " vorname = '"+p.getVorname()+"',"
                            + " strasse = '"+p.getStrasse()+"',"
                            + " oid = null,"
                            + " telefon = '"+p.getTelefon()+"',"
                            + " handy = '"+p.getHandy()+"',"
                            + " email = '"+p.getEmail()+"'"
                            + " where pid = '"+p.getPid()+"'";
            stmt.execute(query);
        }
    
    }
    
    public void deletePerson(Person p) throws SQLException
    {
        Statement stmt = c.createStatement();
            String query = "delete from Adresses where pid = '"+p.getPid()+"'";
            stmt.execute(query);
    }
}
