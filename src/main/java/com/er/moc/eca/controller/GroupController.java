/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.services.GroupModel;
import com.er.moc.eca.model.entities.MocGroup;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alan
 */
@Path("/group")
@RequestScoped
public class GroupController implements Serializable {
    
    @Inject
    private GroupModel groupModel;
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MocGroup> getAllGroups() {
        
        return groupModel.getAll();
        
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MocGroup getById(@PathParam("id") String id) {
        Long cod = Long.parseLong(id);
        return groupModel.getByID(cod);
        
    }
    
    
}
