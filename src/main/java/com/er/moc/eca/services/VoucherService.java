/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.auth.Voucher;
import com.er.moc.eca.model.entities.MocUser;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

/**
 *
 * @author alan
 */
@ApplicationScoped
public class VoucherService {
    
    
    

    public VoucherService() {
    }
    
    @Inject
    private AuthControl auth;
    
    @Inject
    private UserModel userModel;
    
    
    public Voucher authenticate(String login, String password) throws AuthenticationException {
        
        MocUser user = userModel.Authenticate(login, password);
        if (user != null) {
            Voucher voucher = new Voucher(user);
            
            AuthControl.vouchers.put(voucher.getKey(), voucher);
            
            return voucher;
        }
        else {
            throw new AuthenticationException("Failed in authentication, review your login and password!");
        }
    }
    
    
    
    
}
