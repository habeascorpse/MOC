/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.services.GroupModel;
import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.services.AuthControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alan
 */
@Path("/group")
@RequestScoped
public class GroupController implements Serializable {
    
    @Inject
    private GroupModel groupModel;
    
    @Path("/all/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGroups(@PathParam("key") String key) {
        if (AuthControl.vouchers.containsKey(key)) {
            AuthControl.vouchers.get(key).newInteraction();
            MocGroup groups[] = groupModel.getAllFromUser(AuthControl.vouchers.get(key).getUser()).toArray(new MocGroup[]{});
            return Response.ok(groups).build();
        }
        else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MocGroup getById(@PathParam("id") String id) {
        Long cod = Long.parseLong(id);
        return groupModel.getByID(cod);
        
    }
    
    
    
    
}
