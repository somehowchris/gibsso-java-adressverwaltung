/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.services;

import adressverwaltung.models.Person;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import adressverwaltung.models.Town;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * A Database controller using Hibernate to communicate with the database
 * @author Christof Weickhardt
 */
public class DatabaseService implements Service {
    
    EntityManager em;
    EntityManagerFactory emf;
    
    /**
     *
     * @param connectionValues
     */
    public DatabaseService(HashMap<String, String> connectionValues) {
        Map properties = new HashMap();
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+connectionValues.get("DATABASE_HOST")+":"+connectionValues.get("DATABASE_PORT")+"/"+connectionValues.get("DATABASE_NAME")+"?zeroDateTimeBehavior=convertToNull");
        properties.put("javax.persistence.jdbc.user", connectionValues.get("DATABASE_USER"));
        properties.put("javax.persistence.jdbc.password", connectionValues.get("DATABASE_PASSWORD"));
        properties.put("javax.persistence.schema-generation.database.action", "create");
        System.out.println(properties.get("javax.persistence.jdbc.url"));
        try {
            emf = Persistence.createEntityManagerFactory("AdressverwaltungPU", properties);
        } catch (Exception e) {
        }
        em = (EntityManager) emf.createEntityManager();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Person getPerson(Long id) {
        return em.find(Person.class, id);
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Town getTown(Long id) {
        return em.find(Town.class, id);
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public List<Person> searchPerson(Person person) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE '%"+person.getLastName()+"%' AND p.vorname LIKE '%"+person.getFirstName()+"%'",Person.class);
        return query.getResultList();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public List<Town> searchTown(Town Town) {
        TypedQuery<Town> query = em.createQuery("SELECT o FROM Town o WHERE o.plz LIKE '%"+Town.getPlz()+"%' AND o.name LIKE '%"+Town.getName()+"%'",Town.class);
        return query.getResultList();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long insertPerson(Person person) {
      em.getTransaction().begin();
      em.persist(person);
      em.getTransaction().commit();
      return person.getId();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long insertTown(Town Town) {
      em.getTransaction().begin();
      em.persist(Town);
      em.getTransaction().commit();
      return Town.getTid();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long updatePerson(Person person) {
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        return person.getId();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long updateTown(Town Town) {
        em.getTransaction().begin();
        em.merge(Town);
        em.getTransaction().commit();
        return Town.getTid();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public void deleteTown(Town Town) throws Error{
        em.getTransaction().begin();
        String sql = "SELECT COUNT(p.pid) FROM Person p WHERE p.oid='"+Town.getTid()+"'";
        Query q = em.createQuery(sql);
        long count =  (long)q.getSingleResult();
        em.getTransaction().commit();
        
        if(count == 0){
            em.getTransaction().begin();
            em.remove(Town);
            em.getTransaction().commit();
        }else{
            throw new Error("References on this place still exist "+Town.getTid());
        }
        
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public void deletePerson(Person person) {
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public List<Person> getPeople(int amount, int offset) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",Person.class);
        query.setMaxResults(amount);
        query.setFirstResult(offset);
        return query.getResultList();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public List<Town> getTown() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Town> cq = cb.createQuery(Town.class);
        Root<Town> rootEntry = cq.from(Town.class);
        CriteriaQuery<Town> all = cq.select(rootEntry);
        TypedQuery<Town> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public List<Town> getTown(int amount, int offset) {
        TypedQuery<Town> query = em.createQuery("SELECT o FROM Town o",Town.class);
        query.setMaxResults(amount);
        query.setFirstResult(offset);
        return query.getResultList();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long countTown() {
        String sql = "SELECT COUNT(o.plz) FROM Town o";
        Query q = em.createQuery(sql);
        return (long)q.getSingleResult();
    }
    
    /**
    *
    * {@inheritDoc}
    */
    @Override
    public Long countPeople() {
        String sql = "SELECT COUNT(p.pid) FROM Person p";
        Query q = em.createQuery(sql);
        return (long)q.getSingleResult();
    }

}
