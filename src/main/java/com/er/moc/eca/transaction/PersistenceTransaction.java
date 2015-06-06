/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.transaction;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author alanoliveira
 */
public class PersistenceTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public PersistenceTransaction(String persistenceUnit) {

        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();

    }
    
    private void novaEntityManager() {
        
        em = emf.createEntityManager();
        
    }

    public void begin() {
        em.close();
        novaEntityManager();
        em.getTransaction().begin();

    }

    public void commit() {

        em.getTransaction().commit();
        em.close();
        novaEntityManager();

    }

    public void rollback() {
        em.getTransaction().rollback();
        novaEntityManager();

    }

    public void persist(Object obj) {
        em.persist(obj);
    }

    public Object merge(Object obj) {
        return em.merge(obj);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Query createNamedQuery(String query) {

        return em.createNamedQuery(query);
    }

    public void close() {
        em.close();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.em);
        hash = 97 * hash + Objects.hashCode(this.emf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersistenceTransaction other = (PersistenceTransaction) obj;
        if (!Objects.equals(this.em, other.em)) {
            return false;
        }
        if (!Objects.equals(this.emf, other.emf)) {
            return false;
        }
        return true;
    }

}

