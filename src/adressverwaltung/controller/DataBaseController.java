/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.controller;

import adressverwaltung.models.Ort;
import adressverwaltung.models.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author chris
 */
public class DataBaseController implements Controller {
    
    EntityManager em;
    EntityManagerFactory emf;
    
    public DataBaseController(HashMap<String, String> connectionValues) {
        Map properties = new HashMap();
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+connectionValues.get("DATABASE_HOST")+":"+connectionValues.get("DATABASE_PORT")+"/"+connectionValues.get("DATABASE_NAME")+"?zeroDateTimeBehavior=convertToNull");
        properties.put("javax.persistence.jdbc.user", connectionValues.get("DATABASE_USER"));
        properties.put("javax.persistence.jdbc.password", connectionValues.get("DATABASE_PASSWORD"));
        properties.put("javax.persistence.schema-generation.database.action", "create");
        try {
            emf = Persistence.createEntityManagerFactory("AdressverwaltungPU", properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        em = (EntityManager) emf.createEntityManager();
    }

    @Override
    public Person getPerson(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Ort getOrt(Long id) {
        return em.find(Ort.class, id);
    }

    @Override
    public List<Person> searchPerson(Person person) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE '%"+person.getName()+"%' AND p.vorname LIKE '%"+person.getVorname()+"%'",Person.class);
        return query.getResultList();
    }

    @Override
    public List<Ort> searchOrt(Ort ort) {
        TypedQuery<Ort> query = em.createQuery("SELECT o FROM Ort o WHERE plz LIKE '%"+ort.getPlz()+"%' AND name LIKE '%"+ort.getName()+"%'",Ort.class);
        return query.getResultList();
    }

    @Override
    public Long insertPerson(Person person) {
      em.getTransaction().begin();
      em.persist(person);
      em.getTransaction().commit();
      return person.getId();
    }

    @Override
    public Long insertOrt(Ort ort) {
      em.getTransaction().begin();
      em.persist(ort);
      em.getTransaction().commit();
      return ort.getOid();
    }

    @Override
    public void updatePerson(Person person) {
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
    }

    @Override
    public void updateOrt(Ort ort) {
        em.getTransaction().begin();
        em.merge(ort);
        em.getTransaction().commit();
    }

    @Override
    public void deleteOrt(Ort ort) {
        em.getTransaction().begin();
        em.remove(ort);
        em.getTransaction().commit();
    }


    @Override
    public void deletePerson(Person person) {
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
    }

    @Override
    public List<Person> getPeople(int amount, int offset) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",Person.class);
        query.setMaxResults(amount);
        query.setFirstResult(offset);
        return query.getResultList();
    }
    @Override
    public List<Ort> getOrt(int amount, int offset) {
        TypedQuery<Ort> query = em.createQuery("SELECT o FROM Ort o",Ort.class);
        query.setMaxResults(amount);
        query.setFirstResult(offset);
        return query.getResultList();
    }

    @Override
    public Long countOrt() {
        String sql = "SELECT COUNT(o.oid) FROM Ort o";
        Query q = em.createQuery(sql);
        return (long)q.getSingleResult();
    }

    @Override
    public Long countPeople() {
        String sql = "SELECT COUNT(p.pid) FROM Person p";
        Query q = em.createQuery(sql);
        return (long)q.getSingleResult();
    }

}