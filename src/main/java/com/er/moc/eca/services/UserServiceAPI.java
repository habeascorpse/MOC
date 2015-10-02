/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.transaction.EnumConnection;
import java.util.List;

/**
 *
 * @author alan
 */
public abstract class UserServiceAPI extends GenericService<MocUser> {

    public UserServiceAPI() {
        super(MocUser.class, EnumConnection.MOC);
    }
    
    public abstract MocUser getByLogin(String login);
    
    public abstract List<MocUser> search(String search, MocUser user);
    
    public abstract MocUser Authenticate(String login, String password);
    
    public abstract MocUser getByEmail(String email);
    
    public abstract EReturn createUser(MocUser user);
    
    public abstract EReturn confirmUser(String hash);
    
    public abstract EReturn addContact(MocUser user, MocUser contact);
    
    public abstract EReturn confirmContact(MocUser user, MocUser contact);
    
}
