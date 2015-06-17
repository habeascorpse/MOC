/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.auth;

import com.er.moc.eca.config.HashGenerator;
import com.er.moc.eca.model.entities.MocUser;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alan
 */
@XmlRootElement
public class Voucher {

    private final int sessionTimeSeconds = 10;

    private String key;

    private final MocUser user;

    private final Date initialAccess;

    private Date lastAccess;

    public Voucher(MocUser user) {
        this.user = user;

        this.initialAccess = new Date(System.currentTimeMillis());
        this.lastAccess = new Date(System.currentTimeMillis());

        this.key = HashGenerator.generateHash(this.user.getLogin() + this.lastAccess);

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MocUser getUser() {
        return user;
    }

    public Date getInitialAccess() {
        return initialAccess;
    }

    public Date getLastAccess() {
        return lastAccess;
    }
    
    public Boolean isSessionActive() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(lastAccess);
        gc.add(Calendar.SECOND, this.sessionTimeSeconds);
        
        GregorianCalendar gc2 = new GregorianCalendar();
        gc2.setTime(new Date());
        
        if (gc.after(gc2))  {
            return Boolean.TRUE;
        }
        else
            return Boolean.FALSE;
    }

    public Boolean newInteraction() {
        
        if (isSessionActive())  {
            this.lastAccess = new Date(System.currentTimeMillis());
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }


    }

}
