/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.ConfirmationUser;
import com.er.moc.eca.transaction.EnumConnection;
import javax.persistence.NoResultException;

/**
 *
 * @author alan
 */
public class ConfirmationModel extends GenericModel<ConfirmationUser> {

    public ConfirmationModel() {
        super(ConfirmationUser.class, EnumConnection.MOC);
    }
    
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
