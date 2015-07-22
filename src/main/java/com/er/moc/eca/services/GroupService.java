/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.transaction.EnumConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alan
 */
public class GroupService extends GenericService<MocGroup> {

    public GroupService() {
        super(MocGroup.class, EnumConnection.MOC);
    }

    public List<MocGroup> getAllFromUser(MocUser user) {
        try {
            return pt.createNamedQuery("Group.getAllByUser")
                    .setParameter("user", user)
                    .getResultList();
        }
        catch (Exception e) {
            return new ArrayList<MocGroup>();
        }
    }
    
    public MocGroup newGroupFromContactConfirmation(MocUser user1, MocUser user2) {
        
        MocGroup group = MocGroup.newGroupFromContact(user1, user2);
        
        
        if (this.save(group).isError())
            throw new RuntimeException("Can't persist group on database");
        
        return group;
    }
    
    public MocGroup getGroupByName(MocUser user, String groupName) {
        
        return (MocGroup) pt.createNamedQuery("Group.getAllByUserAndName")
                .setParameter("user", user)
                .setParameter("group", groupName)
                .getSingleResult();
        
    }
    
}
