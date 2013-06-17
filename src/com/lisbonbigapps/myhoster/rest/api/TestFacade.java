package com.lisbonbigapps.myhoster.rest.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.rest.util.RestMediaType;

@Path("/test")
public class TestFacade {
    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response test1(@PathParam("id") long id, @QueryParam("x") Double x, @QueryParam("y") Double y) {
	return Response.ok().build();
    }

    @POST
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response test2(@PathParam("id") long id, @QueryParam("x") Double x, @QueryParam("y") Double y) {
	return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response test3(@PathParam("id") long id, @QueryParam("x") Double x, @QueryParam("y") Double y) {
	return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response test4(@PathParam("id") long id, @QueryParam("x") Double x, @QueryParam("y") Double y) {
	return Response.ok().build();
    }
}
