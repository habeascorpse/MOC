/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;

import com.er.moc.eca.auth.Voucher;
import javax.naming.AuthenticationException;

/**
 *
 * @author alan
 */
public abstract class VoucherServiceAPI {
    
    public abstract Voucher authenticate(String login, String password) throws AuthenticationException;
    
}
