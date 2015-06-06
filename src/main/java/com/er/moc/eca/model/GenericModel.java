/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.model;

import com.er.moc.eca.transaction.EnumConnection;
import com.er.moc.eca.transaction.PersistenceFactory;
import com.er.moc.eca.transaction.PersistenceTransaction;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;


/**
 * 
 * 
 * @author alan
 * @param <T> 
 * @since 0.4
 * 
 * Classe genérica que implementa funções para os modelos
 * 
 */

public abstract class GenericModel<T> {
    /**
     * @author alan 
     * Intancia do PersistenceTransaction
     */
    protected  PersistenceTransaction pt;
    protected EnumConnection connection;

    private final Class<T> type;

    
    /**
     * 
     * @param type
     * @param connection EnumConnection 
     */
    protected GenericModel(Class<T> type,EnumConnection connection) {
        this.type = type;
        this.connection = connection;
        pt = PersistenceFactory.getPersistenceTransaction(connection);
    }
    
    
    
    /**
     * Método que retorna a Entidade pelo ID
     * @param id - id da entidade
     * @return T - Retorna a Entidade
     * @author alan
     */
    public  T getByID(Long id) {
        
        try {
            T t = pt.getEntityManager().find(type, id);
            return t;
            

        } catch (NoResultException ex) {
            return null;
        }
        
    }
    
    /**
     * Método que retorna uma Lista de todas Entidades 
     * @return List<T> - Retorna uma lista de Entidade
     * 
     */
    public  List<T> getAll() {
        try {
            
            List lista = pt
                    .getEntityManager()
                    .createQuery("select a from " + type.getName() + " a")
                    .getResultList();
            
            return lista;
        } catch (NoResultException ex) {
            return new ArrayList<T>();
        }

    }
    
    /**
     * Método que implementa persiste uma entidade
     * @param obj Entidade
     * @return Retorna um Enum de Retorno EReturn
     * 
     */
    public EReturn save(T obj) {
        
        try {
            
            pt.begin();
            pt.persist(obj);
            pt.commit();
        }
        catch (Exception e) {
           
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    /**
     * Método que implementa persiste uma lista de entidade
     * @param objs
     * @return Retorna um Enum de Retorno EReturn
     */
    public EReturn save(List<T> objs) {
        
        try {
            pt.begin();
            
            for (T obj: objs) {
                pt.persist(obj);
            }
            
            pt.commit();
        }
        catch (Exception e) {
            
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    /**
     * Método que implementa o merge em uma entidade
     * @param obj
     * @return Retorna um Enum de Retorno EReturn
     */
    public EReturn merge(T obj) {
        
        try {
            pt.begin();
            pt.merge(obj);
            pt.commit();
        }
        catch (Exception e) {
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    
    
    /**
     * Método que implementa o merge em uma lista de entidades
     * @param objs
     * @return Retorna um Enum de Retorno EReturn
     */
    public EReturn merge(List<T> objs) {
        
        try {
            pt.begin();
            
            for (T obj: objs) {
                pt.merge(obj);
            }
            
            pt.commit();
            
        }
        catch (Exception e) {
            
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    /**
     * Método que remove uma entidade
     * @param obj
     * @return Retorna um Enum de Retorno EReturn
     */
    public EReturn remove(T obj) {
        
        try {
            pt.begin();
            obj = pt.getEntityManager().merge(obj);
            pt.getEntityManager().remove(obj);
            pt.commit();
        }
        catch (Exception e) {
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    /**
     * Método que remove uma lista de entidades
     * @param objs
     * @return Retorna um Enum de Retorno EReturn
     */
    public EReturn remove(List<T> objs) {
        
        try {
            pt.begin();
            
            for (T obj: objs) {
                pt.merge(obj);
                pt.getEntityManager().remove(obj);
            }
            
            pt.commit();
            
        }
        catch (Exception e) {
            return EReturn.ERROR;
        }
        
        
        return EReturn.SUCESS;
    }
    
    
    
    

}
