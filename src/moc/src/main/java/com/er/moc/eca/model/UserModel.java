/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.model;

import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.transaction.EnumConnection;

/**
 *
 * @author alan
 */
public class UserModel extends GenericModel<MocUser> {

    public UserModel() {
        super(MocUser.class, EnumConnection.SIGA);
    }
    
    
    
}
