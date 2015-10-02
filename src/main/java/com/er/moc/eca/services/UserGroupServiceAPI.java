/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.transaction.EnumConnection;

/**
 *
 * @author alan
 */
public abstract class UserGroupServiceAPI extends GenericService<UserGroup> {

    public UserGroupServiceAPI() {
        super(UserGroup.class, EnumConnection.MOC);
    }
    
    public abstract UserGroup getByUserAndGroup(MocUser user, MocGroup group);
    
}
