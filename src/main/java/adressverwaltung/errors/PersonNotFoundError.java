/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.errors;

/**
 *
 * @author chris
 */
public class PersonNotFoundError extends Exception{

    public PersonNotFoundError() {
        super("Cloud not find the requested person");
    }
    
}
