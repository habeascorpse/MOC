/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services.impl;

import com.er.moc.eca.services.GenericService;
import com.er.moc.eca.model.entities.ConfirmationUser;
import com.er.moc.eca.services.ConfirmationServiceAPI;
import com.er.moc.eca.transaction.EnumConnection;
import javax.persistence.NoResultException;

/**
 *
 * @author alan
 */
public class ConfirmationService extends ConfirmationServiceAPI{

    public ConfirmationService() {
    }
    
    @Override
    public ConfirmationUser getByHash(String hash) {
        
        try {
            return (ConfirmationUser)
            pt.createNamedQuery("ConfirmationUser.getByHash")
                .setParameter("hash", hash)
                .getSingleResult();
        }
        catch (NoResultException ex) {
            return null;
        }
        
    }
    
}
