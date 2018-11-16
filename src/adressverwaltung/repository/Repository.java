/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.repository;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public interface Repository <T>{
    
    public T getById(int id);
    public T[] getAll();
    public T update(int id, T obj);
    public boolean delete(int id);
    public T create(T obj);
    
}
