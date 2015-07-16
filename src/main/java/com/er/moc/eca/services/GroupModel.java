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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alan
 */
public class GroupModel extends GenericModel<MocGroup> {

    public GroupModel() {
        super(MocGroup.class, EnumConnection.MOC);
    }

    public List<MocGroup> getAllFromUser(MocUser user) {
        try {
            return pt.createNamedQuery("Group.getAllByUser")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<MocGroup>();
        }
    }

    public EReturn newGroupFromContact(Set<MocUser> users) {
        String name = null;
        for (MocUser us : users) {
            name += (us.getName().split(" "))[0];
        }

        MocGroup group = new MocGroup(name);
        group.setInitDate(new Date(System.currentTimeMillis()));

        if (!this.save(group).isError()) {
            try {
                pt.begin();
                for (MocUser us : users) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setMocUser(us);
                    userGroup.setStatus("A");
                    userGroup.setMocGroup(group);

                    pt.persist(userGroup);

                }
                pt.commit();
                return EReturn.SUCESS;
            } catch (Exception ex) {
                return EReturn.ERROR;
            }
        }
        return EReturn.ERROR;
    }

}
