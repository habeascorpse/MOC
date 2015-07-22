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
public class UserGroupService extends GenericService<UserGroup> {

    public UserGroupService() {
        super(UserGroup.class,EnumConnection.MOC);
    }
    
    
    public UserGroup getByUserAndGroup(MocUser user, MocGroup group) {
        
        return (UserGroup) pt.createNamedQuery("UserGroup.getByUserAndGroup")
                .setParameter("group", group)
                .setParameter("user", user)
                .getSingleResult();
        
    }
    
}
