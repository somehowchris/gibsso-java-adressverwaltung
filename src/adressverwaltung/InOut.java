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

    public ArrayList<Person> searchPerson(String vorname, String name) {
        ArrayList<Person> rows  = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            String query = "select id, name, vorname, strasse, oid, telefon, handy, email from Adresses where name like '%"+name+"%' and vorname like '%"+vorname+"%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Person p = new Person(rs.getInt("id"),rs.getString("name"),rs.getString("vorname"),rs.getString("strasse"),rs.getInt("oid"),rs.getString("telefon"),rs.getString("handy"),rs.getString("email"));
                rows.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }

        return rows;
    }
    
    
}
