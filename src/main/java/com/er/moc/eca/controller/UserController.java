/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.auth.Voucher;
import com.er.moc.eca.services.CountryModel;
import com.er.moc.eca.services.UserModel;
import com.er.moc.eca.model.entities.Country;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.VoucherService;
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
    @Inject
    private VoucherService voucherService;
    
    
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
            return Response.status(Response.Status.CONFLICT).build();
        else
            return Response.status(Response.Status.CREATED).build();
    }
    
    @Path("/countries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> getCountries() {
        
        return countryModel.getAll();
    }
    
    @Path("/confirm/{hash}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response confirm(@PathParam("hash") String hash) {
        
        
        if (!userModel.confirmUser(hash).isError()) {
            
            
            String message = "Congratulation, now you has ready to join in our team!"
                    + "<br> <a href=\"http://localhost:8080/moc/login\">Login</a> ";
            
            return Response.ok(message).build();
        }
        else
            return Response.status(Response.Status.CONFLICT).build();
        
    }
    
    
    
    @Path("authenticate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(MocUser user) {
        
        try {
            Voucher voucher = voucherService.authenticate(user.getLogin(), user.getPassword());
            
            return Response.ok(voucher.getKey()).build();
        }
        catch (Exception ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        
    }
    
    
    
    
    
}
