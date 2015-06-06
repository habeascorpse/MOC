/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.model;

import com.er.moc.eca.config.SendMail;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.transaction.EnumConnection;
import javax.persistence.NoResultException;

/**
 *
 * @author alan
 */
public class UserModel extends GenericModel<MocUser> {

    public UserModel() {
        super(MocUser.class, EnumConnection.SIGA);
    }
    
    
    public MocUser getByLogin(String login) {
        
        try {
            return (MocUser) pt
                   .createNamedQuery("getByLogin")
                   .setParameter("login", login)
                   .getSingleResult();
        }
        catch(NoResultException e) {
            return null;
        }
        
        
    }
    
    
    public EReturn createUser(MocUser user) {
        
        if (getByLogin(user.getLogin()) != null)
            return EReturn.LOGIN_JA_EXISTE;
        user.setStatus(-1);
        if (!this.merge(user).isError()) {
            // Todo Enviar email com confirmação de usuário e senha Moc23P)(44 mocsysbr
            String msgMail = " Thank You for chose MOC a PQP member\n"
                    + "please click <a href=\"http://localhost/blablabla\" here </a> to confirm your account";
            SendMail mail = new SendMail("MocSysBR",user.getEmail(),"MOC - Account Confirmation",msgMail);
            mail.start();
            return EReturn.SUCESS;
        }
        
        return EReturn.ERROR;
    }
    
}
