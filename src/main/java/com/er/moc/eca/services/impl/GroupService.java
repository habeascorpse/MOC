/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services.impl;

import com.er.moc.eca.services.EReturn;
import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.services.GroupServiceAPI;
import java.util.List;
/**
 *
 * Service Group class this class is on the Model Business Layer
 *
 * @author alan
 */
public class GroupService extends GroupServiceAPI {

    public GroupService() {
        
    }

    @Override
    public List<MocGroup> getAllFromUser(MocUser user) {

        List<MocGroup> groups = pt.createNamedQuery("Group.getAllByUser")
                .setParameter("user", user)
                .getResultList();
        return groups;

    }

    @Override
    public MocGroup newGroupFromContactConfirmation(MocUser user1, MocUser user2) {

        MocGroup group = MocGroup.newGroupFromContact(user1, user2);

        if (this.save(group).isError()) {
            throw new RuntimeException("Can't persist group on database");
        }

        return group;
    }
    @Override
    public MocGroup getGroupByName(String groupName) {

        return (MocGroup) pt.createNamedQuery("Group.getAllByName")
                .setParameter("name", groupName)
                .getSingleResult();

    }
    @Override
    public MocGroup getGroupByName(MocUser user, String groupName) {

        return (MocGroup) pt.createNamedQuery("Group.getAllByUserAndName")
                .setParameter("user", user)
                .setParameter("group", groupName)
                .getSingleResult();

    }
    @Override
    public MocGroup createGroup(MocUser user, String name) {

        if (this.getGroupByName(name) == null) {

            MocGroup group = new MocGroup();
            group.setName(name);
            group.getListUserGroup().add(new UserGroup(group, user));

            this.save(group);
            return group;
        }
        else {
            throw new RuntimeException("This group already exist!");
        }
    }
    @Override
    public EReturn addUserToGroup(MocUser user, MocGroup group) {
        group.getListUserGroup().add(new UserGroup(group, user));

        this.merge(group);
        return EReturn.SUCESS;
    }

}
