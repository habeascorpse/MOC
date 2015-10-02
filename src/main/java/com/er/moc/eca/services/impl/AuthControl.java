/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services.impl;

import com.er.moc.eca.auth.Voucher;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author alan
 */
public class AuthControl extends Thread {
    
    public static ConcurrentHashMap<String,Voucher> vouchers = new ConcurrentHashMap<String,Voucher>();

    
    @PostConstruct
    private void init() {
        this.start();
    }
    
    @Override
    public void run() {
        
        try {
            
            while (true) {
                sleep(2000);
                for (Voucher voucher: vouchers.values()) {
                    if (!voucher.isSessionActive()) {
                        vouchers.remove(voucher.getKey());
                    }
                    
                }
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(VoucherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
}
