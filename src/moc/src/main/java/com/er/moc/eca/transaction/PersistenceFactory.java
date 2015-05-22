/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.transaction;


/**
 *
 * @author alanoliveira
 */
public class PersistenceFactory {
    
    /*
     * Ninguém deve herdar ou instanciar esta classe
     */
    private PersistenceFactory() {
        
    }
    
    public static PersistenceTransaction getPersistenceTransaction(EnumConnection persistenceUnit) {
        
        
        return new PersistenceTransaction(persistenceUnit.getStringConnection());
    } 
    
}
