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
import java.util.List;

/**
 *
 * @author alan
 */
public abstract class GroupServiceAPI extends GenericService<MocGroup> {

    public GroupServiceAPI() {
        super(MocGroup.class, EnumConnection.MOC);
    }
    
    public abstract List<MocGroup> getAllFromUser(MocUser user);

    public abstract MocGroup newGroupFromContactConfirmation(MocUser user1, MocUser user2);

    public abstract MocGroup getGroupByName(String groupName);

    public abstract MocGroup getGroupByName(MocUser user, String groupName);

    public abstract MocGroup createGroup(MocUser user, String name);

    public abstract EReturn addUserToGroup(MocUser user, MocGroup group);
    
}
