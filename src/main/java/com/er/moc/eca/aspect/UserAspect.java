/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.aspect;

import com.er.moc.eca.model.entities.ConfirmationUser;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.ConfirmationServiceAPI;
import com.er.moc.eca.services.EReturn;
import com.er.moc.eca.services.UserServiceAPI;
import com.er.moc.eca.services.impl.ConfirmationService;
import com.er.moc.eca.services.impl.GroupService;
import com.er.moc.eca.services.impl.UserService;
import java.lang.reflect.Field;
import javax.inject.Inject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author alan
 */
@Aspect
public class UserAspect {
    
    
    @After(value = "execution(* com.er.moc.eca.services.impl.UserService.confirmContact(..))")
    public void afterConfirmContact(JoinPoint point) {
        Long id_user = ((MocUser) point.getArgs()[0]).getId();
        long id_contact = ((MocUser) point.getArgs()[1]).getId();
        UserService userService = new UserService();
        MocUser user = userService.getByID(id_user);
        MocUser contact = userService.getByID(id_contact);
        System.out.println("Usuário: "+user.getLogin() + ", contato: "+ contact.getLogin());
        
        new GroupService().newGroupFromContactConfirmation(user, contact);
    }
    
    @AfterReturning(pointcut = "execution(* com.er.moc.eca.services.impl.UserService.confirmUser(..))",
                    returning = "result")
    public void afterConfirmUser(JoinPoint point, Object result) {
        if (((EReturn) result).equals(EReturn.SUCESS)) {
            String hash = (String) point.getArgs()[0];
            ConfirmationServiceAPI confirmationService = new ConfirmationService();
            ConfirmationUser confirm = confirmationService.getByHash(hash);
            confirmationService.remove(confirm);
        }
        
    }
}
