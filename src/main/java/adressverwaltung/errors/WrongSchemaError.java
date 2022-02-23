/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.errors;

/**
 *
 * @author Christof Weickhardt
 */
public class WrongSchemaError extends Exception{
    /**
     * Constructor to create a custom WrongSchemaError exception
     */
    public WrongSchemaError() {
        super("Could not read database schema");
    }
}
