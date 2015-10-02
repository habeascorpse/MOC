/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.ConfirmationUser;
import com.er.moc.eca.transaction.EnumConnection;

/**
 *
 * @author alan
 */
public abstract class ConfirmationServiceAPI extends GenericService<ConfirmationUser> {

    public ConfirmationServiceAPI() {
        super(ConfirmationUser.class, EnumConnection.MOC);
    }
    
    public abstract ConfirmationUser getByHash(String hash);
    
}
