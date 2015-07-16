/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.moc.eca.controller;

import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.AuthControl;
import com.er.moc.eca.services.UserModel;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/contacts")
@RequestScoped
public class ContactsController {

    @Inject
    private UserModel userModel;
    
    @Path("/add/{contato}/{key}")
    @POST
    public Response add(@PathParam("contato") String contato,@PathParam("key") String key) {
        if (AuthControl.vouchers.containsKey(key)) {
            AuthControl.vouchers.get(key).newInteraction();
            MocUser user = userModel.getByLogin(contato);
            if (user != null) {
                userModel.addContact(AuthControl.vouchers.get(key).getUser(), user);
            }
            else {
                Response.status(Response.Status.NO_CONTENT).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
}
