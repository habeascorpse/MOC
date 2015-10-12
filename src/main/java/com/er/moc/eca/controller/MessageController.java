/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.model.entities.MocGroup;
import com.er.moc.eca.model.entities.MocMessage;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.model.entities.UserGroup;
import com.er.moc.eca.services.GroupServiceAPI;
import com.er.moc.eca.services.MessageServiceAPI;
import com.er.moc.eca.services.UserGroupServiceAPI;
import com.er.moc.eca.services.impl.AuthControl;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

/**
 *
 * @author alan
 */
@Path("message")
@RequestScoped
public class MessageController {

    @Inject
    private MessageServiceAPI messageService;

    @Inject
    private GroupServiceAPI groupService;

    @Inject
    private UserGroupServiceAPI userGroupService;

    @Path("/get/{group}/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MocMessage> getMessageByGroup(@PathParam("key") String key, @PathParam("group") String groupName) throws NoContentException {

        MocUser user = AuthControl.vouchers.get(key).getUser();
        MocGroup group = groupService.getGroupByName(user, groupName);
        if (group != null) {

            UserGroup userGroup = userGroupService.getByUserAndGroup(user, group);
            List<MocMessage> lista = messageService.getMessageByGroup(userGroup);

            return lista;
        } else {
            throw new NoContentException("");
        }

    }

    @Path("send/{group}/{key}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(MocMessage message, @PathParam("group") String groupName, @PathParam("key") String key) {

        MocUser user = AuthControl.vouchers.get(key).getUser();
        MocGroup group = groupService.getGroupByName(user, groupName);
        if (group != null) {
            message.setUserGroup(userGroupService.getByUserAndGroup(user, group));
            messageService.sendMessage(message);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

    }

}
