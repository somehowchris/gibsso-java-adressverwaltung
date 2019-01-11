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
public class OrtNotFoundError extends Exception{

    public OrtNotFoundError() {
        super("Could not find the requested ort");
    }
    
}
