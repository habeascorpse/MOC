/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services.impl;

import com.er.moc.eca.services.EReturn;
import com.er.moc.eca.model.entities.MocMessage;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.services.MessageServiceAPI;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alan
 */
//@ApplicationScoped
public class MessageService extends MessageServiceAPI {
    
    /*
    @Inject
    private MessageControl messageControl;
    */
    private static final Integer maxResult = 50;

    public MessageService() {
    }
    
    @Override
    public List<MocMessage> getMessageByGroup(UserGroup userGroup) {
        
        List<MocMessage> list = pt.createNamedQuery("Message.getMessagesByGroup")
                .setParameter("group", userGroup.getMocGroup())
                .setMaxResults(maxResult)
                .getResultList();
        
        Collections.reverse(list);
        
        return list;
        
    }
    
    @Override
    public EReturn sendMessage(MocMessage message) {
        return this.save(message);
        
    }
    
}
