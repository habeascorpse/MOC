/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.model.UserModel;
import com.er.moc.eca.model.entities.MocUser;
import java.awt.PageAttributes;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alan
 */
@Path("/usuarios")
public class UserController {
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MocUser> getAllUsers() {
        UserModel userModel = new UserModel();
        List<MocUser> users = userModel.getAll();
        
        
        return users;
    }
    
}
