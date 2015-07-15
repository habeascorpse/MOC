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
public class GroupModel extends GenericModel<MocGroup> {

    public GroupModel() {
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
    
}
