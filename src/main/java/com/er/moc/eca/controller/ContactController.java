/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.AuthControl;
import com.er.moc.eca.services.UserService;
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


@Path("contact")
@RequestScoped

public class ContactController {
    @Inject
    private UserService userService;
    
    @Path("add/{key}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addContact(String contact, @PathParam("key") String key) {
        if (AuthControl.vouchers.containsKey(key)) {
            AuthControl.vouchers.get(key).newInteraction();

            MocUser userContact = userService.getByLogin(contact);
            if (userContact != null) {
                
                if (userService.addContact(AuthControl.vouchers.get(key).getUser(), userContact).isError()) 
                    return Response.serverError().build();
                else
                    return Response.accepted().build();

            }

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {

            return Response.status(Response.Status.FORBIDDEN).build();

        }

    }
    
    @Path("confirm/{key}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response aconfirmContact(String contact, @PathParam("key") String key) {
        if (AuthControl.vouchers.containsKey(key)) {
            AuthControl.vouchers.get(key).newInteraction();

            MocUser userContact = userService.getByLogin(contact);
            if (userContact != null) {
                
                if (userService.confirmContact(AuthControl.vouchers.get(key).getUser(), userContact).isError()) 
                    return Response.serverError().build();
                else
                    return Response.accepted().build();

            }

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {

            return Response.status(Response.Status.FORBIDDEN).build();

        }

    }
    
    @Path("/find/{search}/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUsers(@PathParam("search") String search, @PathParam("key") String key) {

        if (AuthControl.vouchers.containsKey(key)) {
            AuthControl.vouchers.get(key).newInteraction();
            return Response.ok(userService.search(search, AuthControl.vouchers.get(key).getUser()).toArray(new MocUser[]{})).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
}
