/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.aspect;

import com.er.moc.eca.services.impl.AuthControl;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import javax.ws.rs.ForbiddenException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;

/**
 *
 * @author alan
 */
@Aspect
public class SecurityAspect {

    @Before("execution(* com.er.moc.eca.controller..* (..))")
    public void checkVoucher(JoinPoint point) {
        boolean isSecurity = false;
        for (String str : ((CodeSignature) point.getStaticPart().getSignature()).getParameterNames()) {
            System.out.println("nome: " + str);
            if (str.equals("key")) {
                isSecurity = true;
            }
        }
        if (!isSecurity) {
            return;
        }

        for (Object obj : point.getArgs()) {

            if (AuthControl.vouchers.containsKey(obj.toString())) {
                AuthControl.vouchers.get(obj.toString()).newInteraction();
                return;
            }
            
        }
        throw new ForbiddenException();
    }

}
