/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocMessage;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.transaction.EnumConnection;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author alan
 */
//@ApplicationScoped
public class MessageService extends GenericService<MocMessage> {
    
    /*
    @Inject
    private MessageControl messageControl;
    */
    private static final Integer maxResult = 50;

    public MessageService() {
        super(MocMessage.class, EnumConnection.MOC);
    }
    
    public List<MocMessage> getMessageByGroup(UserGroup userGroup) {
        
        List<MocMessage> list = pt.createNamedQuery("Message.getMessagesByGroup")
                .setParameter("group", userGroup.getMocGroup())
                .setMaxResults(maxResult)
                .getResultList();
        
        Collections.reverse(list);
        
        return list;
        
    }
    
    public EReturn sendMessage(MocMessage message) {
        return this.save(message);
        
    }
    
}
