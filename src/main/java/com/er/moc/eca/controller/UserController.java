/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.controller;

import com.er.moc.eca.auth.Voucher;
import com.er.moc.eca.model.entities.Country;
import com.er.moc.eca.model.entities.MocUser;
import com.er.moc.eca.services.CountryServiceAPI;
import com.er.moc.eca.services.UserServiceAPI;
import com.er.moc.eca.services.VoucherServiceAPI;
import com.er.moc.eca.services.impl.AuthControl;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
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
    private UserServiceAPI userModel;
    @Inject
    private CountryServiceAPI countryModel;
    @Inject
    private VoucherServiceAPI voucherService;

    @Path("/get/all/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@NotNull @PathParam("key") String key) {

        return Response.ok(userModel.getAll().toArray(new MocUser[]{})).build();

    }

    @Path("/get/{id}/{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MocUser getById(@PathParam("id") String id, @PathParam("key") String key) {

        Long cod = Long.parseLong(id);

        return userModel.getByID(cod);

    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(MocUser user) {
        System.out.println(user.getCountry());
        if (userModel.createUser(user).isError()) {
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            return Response.status(Response.Status.CREATED).build();
        }
    }

    @Path("/countries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> getCountries() {

        return countryModel.getAll();
    }

    @Path("/confirm/{key}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response confirm(@PathParam("hash") String hash) {

        if (!userModel.confirmUser(hash).isError()) {

            String message = "Congratulation, now you has ready to join in our team!"
                    + "<br> <a href=\"http://cloudmessenger.com.br/moc\">Login</a> ";

            return Response.ok(message).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

    @Path("authenticate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(MocUser user) {

        try {
            Voucher voucher = voucherService.authenticate(user.getLogin(), user.getPassword());

            return Response.ok(voucher.getKey()).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

    }

}
