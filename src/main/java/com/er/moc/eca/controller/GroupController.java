/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.GroupServiceAPI;
import com.er.moc.eca.services.impl.AuthControl;
import java.io.Serializable;
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
@Path("/group")
@RequestScoped
public class GroupController implements Serializable {

    @Inject
    private GroupServiceAPI groupModel;

    @Path("/all/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGroups(@PathParam("key") String key) {
        MocGroup groups[] = groupModel.getAllFromUser(AuthControl.vouchers.get(key).getUser()).toArray(new MocGroup[]{});
        return Response.ok(groups).build();
    }

    @Path("/get/{id}/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id, @PathParam("key") String key) {
        Long cod = Long.parseLong(id);
        return Response.ok(groupModel.getByID(cod)).build();

    }

    @Path("/create/{key}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createGroup(String name, @PathParam("key") String key) {

            MocUser user = AuthControl.vouchers.get(key).getUser();
            try {
                groupModel.createGroup(user, name);
            } catch (RuntimeException ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            return Response.status(Response.Status.CREATED).build();

    }

}
