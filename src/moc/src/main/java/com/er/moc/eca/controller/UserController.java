/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.model.CountryModel;
import com.er.moc.eca.model.UserModel;
import com.er.moc.eca.model.entities.Country;
import com.er.moc.eca.model.entities.MocUser;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alan
 */
@Path("/users")
@RequestScoped
public class UserController implements Serializable {
    
    @Inject
    private UserModel userModel;
    @Inject
    private CountryModel countryModel;
    
    
    @Path("/get/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MocUser> getAllUsers() {
        List<MocUser> users = userModel.getAll();
        return users;
    }
    
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MocUser getById(@PathParam("id") String id) {
        Long cod = Long.parseLong(id);
        return userModel.getByID(cod);
        
    }
    
    
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(MocUser user) {
        System.out.println(user.getCountry());
        if (userModel.createUser(user).isError()) 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        else
            return Response.status(Response.Status.CREATED).build();
    }
    
    @Path("/countries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> getCountries() {
        
        return countryModel.getAll();
    }
    
    
}
