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
 * Helper class to connect to a database
 * 
 * @author Christof Weickhardt
 */
public class MySQLConnection {

    /**
     * Function to check on a connection to a database
     * 
     * @param host     Host of the database
     * @param name     Name of the database
     * @param port     Port of the database
     * @param username Username to access the database
     * @param password Password to access the database
     * @return Returns true if the connection has been established
     * @throws CanNotConnectToDatabaseError If not possible to connect to the
     *                                      database
     */
    public static boolean verify(String host, String password, String name, String port, String username)
            throws CanNotConnectToDatabaseError {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
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
