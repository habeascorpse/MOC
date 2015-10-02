/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocMessage;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.transaction.EnumConnection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alan
 */
public abstract class MessageServiceAPI extends GenericService<MocMessage> {

    public MessageServiceAPI() {
        super(MocMessage.class, EnumConnection.MOC);
    }
    
    public abstract List<MocMessage> getMessageByGroup(UserGroup userGroup);
    
    public abstract EReturn sendMessage(MocMessage message);
    
}
