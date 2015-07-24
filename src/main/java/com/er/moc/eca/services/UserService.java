/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.config.HashGenerator;
import com.er.moc.eca.config.SendMail;
import com.er.moc.eca.model.entities.ConfirmationUser;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.transaction.EnumConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;

/**
 *
 * @author alan
 */
public class UserService extends GenericService<MocUser> {

    public UserService() {
        super(MocUser.class, EnumConnection.MOC);
    }

    public MocUser getByLogin(String login) {

        try {
            return (MocUser) pt
                    .createNamedQuery("User.getByLogin")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public List<MocUser> search(String search, MocUser user) {

        try {
            
            List<MocUser> contacts =  pt
                    .createNamedQuery("User.search")
                    .setParameter("search", "%"+search+"%")
                    .getResultList();
            contacts.remove(user);
            contacts.removeAll(user.getContacts());
            
            return contacts;
            
        } catch (NoResultException e) {
            return new ArrayList<MocUser>();
        }

    }
    
    public MocUser Authenticate(String login, String password) {
        
        password = HashGenerator.generateHash(password);

        try {
            return (MocUser) pt
                    .createNamedQuery("User.authenticate")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public MocUser getByEmail(String email) {

        try {
            return (MocUser) pt
                    .createNamedQuery("User.getByEmail")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
    private void sendMailConfirmation(ConfirmationUser confirm) {
        
        // Todo Enviar email com confirmação de usuário e senha Moc23P)(44 mocsysbr
        String msgMail = "Hello "+ confirm.getMocUser().getName() +", Thank You for choosen MOC, a PQP member<br />"
                + "please click <a href=\"http://cloudmessenger.com.br/moc/rs/users/confirm/" + confirm.getConfirmationHash() + "\"> here </a> to confirm your account!";
        SendMail mail = new SendMail("MocSysBR", confirm.getMocUser().getEmail(), "MOC - Account Confirmation", msgMail);
        mail.start();
        
    }

    public EReturn createUser(MocUser user) {

        if ((getByLogin(user.getLogin()) != null) || (getByEmail(user.getEmail()) != null)) {
            return EReturn.LOGIN_JA_EXISTE;
        }
        user.setStatus(-1);
        System.out.println(user.getPassword());
        user.setPassword(HashGenerator.generateHash(user.getPassword()));
        ConfirmationUser confirmation = new ConfirmationUser(user);
        
        new ConfirmationModel().merge(confirmation);
        
        this.sendMailConfirmation(confirmation);

        return EReturn.SUCESS;

        
    }
    
    
    public EReturn confirmUser(String hash) {
        ConfirmationUser confirm = new ConfirmationModel().getByHash(hash);
        if (confirm != null) {
            confirm.getMocUser().setStatus(1);
            this.merge(confirm.getMocUser());
            
            new ConfirmationModel().remove(confirm);
            return EReturn.SUCESS;
        }
        else        
            return EReturn.ERROR;
    }
    
    private Boolean hasContact(MocUser user, MocUser contact) {
        if (user.getId().equals(contact.getId()))
            return Boolean.TRUE;
        
        for (MocUser mu :user.getContacts())
            if (mu.getId().equals(contact.getId()))
                return Boolean.TRUE;
        
        return Boolean.FALSE;
    }
    
    public EReturn addContact(MocUser user, MocUser contact) {
        
        if (hasContact(user, contact))
            return EReturn.ERROR;
        
        if (user.getContacts() == null)
            user.setContacts(new ArrayList<MocUser>());
        
        user.getContacts().add(contact);
        
        user  = merge(user);
        return confirmContact(contact, user);
    }
    
    public EReturn confirmContact(MocUser user, MocUser contact) {
        
        if (!hasContact(user, contact)) {
            if (user.getContacts() == null)
                user.setContacts(new ArrayList<MocUser>());
            user.getContacts().add(contact);
            user = merge(user);
            new GroupService().newGroupFromContactConfirmation(user, contact);
            return EReturn.SUCESS;
            
        }
        else {
            return EReturn.CONTATO_NAO_ENCONTRADO;
        }
        
        
    }
    

}
