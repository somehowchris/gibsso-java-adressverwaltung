/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.connectors;

import java.util.ArrayList;
import adressverwaltung.models.Adress;

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
    public Object get(String table);
    public Object get(int id,String table);
    public Object put(Object obj,int id,String table);
    public Object post(Object obj, String table);
    public Object delete(int id, String table);
}
