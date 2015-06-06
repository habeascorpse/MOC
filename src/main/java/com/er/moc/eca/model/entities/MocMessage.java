/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alan
 */
@Entity
@XmlRootElement
public class MocMessage implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 1000)
    private String text;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date sendDate;
    
    @ManyToOne
    private UserGroup userGroup;

    public MocMessage() {
        
        sendDate = new Date(System.currentTimeMillis());
    }

    public MocMessage(String text, UserGroup userGroup) {
        this.text = text;
        this.userGroup = userGroup;
        sendDate = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Date getSendDate() {
        return sendDate;
    }
    
    
    
    
    
}
