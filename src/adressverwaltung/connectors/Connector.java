/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.connectors;

import adressverwaltung.models.Address;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public interface Connector {
    
    /**
     *
     * @param id
     * @return
     */
    public Address getById(int id);
    public ArrayList<Address> getAll();
    public ArrayList<Address> search(String input);
}
