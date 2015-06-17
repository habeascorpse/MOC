/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.transaction.EnumConnection;

/**
 *
 * @author alan
 */
public class GroupModel extends GenericModel<MocGroup> {

    public GroupModel() {
        super(MocGroup.class, EnumConnection.MOC);
    }
    
}
