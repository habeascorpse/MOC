/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.aspect;

import com.er.moc.eca.services.impl.AuthControl;
import java.lang.reflect.Field;
import javax.ws.rs.ForbiddenException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author alan
 */
@Aspect
public class SecurityAspect {
    
    @Before("execution(* com.er.moc.eca.controller..* (..))")
    public void checkVoucher(JoinPoint point) {
        
        for (Object obj: point.getArgs()) {
            
            for (Field f: obj.getClass().getDeclaredFields()) {
                if (f.getName().equals("key")) {
                    String key = (String) obj;
                    if (AuthControl.vouchers.containsKey(key)) {
                        AuthControl.vouchers.get(key).newInteraction();
                    }
                    else {
                        throw new ForbiddenException();
                    }
                }
            }
        }
        
    }
    
}
