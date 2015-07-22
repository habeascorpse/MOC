/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocMessage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author alan
 */
public class MessageControl extends Thread {
    
    public static ConcurrentLinkedDeque<MocMessage>  messages;
    
    
    @PostConstruct
    private void init() {
        this.start();
    }
    
    @Override
    public void run() {
        
        try {
            
            while (true) {
                sleep(2000);
                
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
}
