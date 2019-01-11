/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.utils;

import adressverwaltung.errors.CanNotConnectToDatabaseError;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christof Weickhardt
 */
public class MySQLConnection {
    public static boolean verify (String host,String password, String name,String port,String username) throws CanNotConnectToDatabaseError{
        String url = "jdbc:mysql://"+host+":"+port+"/"+name;
        System.out.println("Connecting database...");
        try {
            DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return true;
        } catch (SQLException e) {
            throw new CanNotConnectToDatabaseError();
        }
    }
}
