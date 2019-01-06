/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.controller;

import adressverwaltung.models.Ort;
import java.util.ArrayList;

import adressverwaltung.models.Person;
import java.util.List;

/**
 *
 * @author Christof Weickhardt
 */
public interface Controller {

  public Person getPerson(Long id);

  public Ort getOrt(Long id);

  public List<Person> searchPerson(Person person);

  public List<Ort> searchOrt(Ort ort);

  public Long insertPerson(Person person);

  public Long insertOrt(Ort ort);

  public void updatePerson(Person person);

  public void updateOrt(Ort ort);

  public void deleteOrt(Ort ort);

  public void deletePerson(Person person);

  public List<Person> getPeople(int amount, int offset);

  public List<Ort> getOrt(int amount, int offset);

  public Long countOrt();
  public Long countPeople();
}
