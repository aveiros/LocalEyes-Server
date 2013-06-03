package com.lisbonbigapps.myhoster.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.lisbonbigapps.myhoster.rest.exception.BadRequestException;
import com.lisbonbigapps.myhoster.rest.exception.NotFoundException;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;
import com.lisbonbigapps.myhoster.rest.response.factories.HosterResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.factories.UserResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.util.Authentication;
import com.lisbonbigapps.myhoster.rest.util.RestMediaType;

@Path("/hosters")
public class HosterFacade {
    @Context
    HttpServletRequest request;
    Authentication auth = new Authentication();

    @GET
    @Produces(RestMediaType.Json)
    public Response getHosters() throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	return Response.ok(factory.getHosters()).build();
    }

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response getHosterById(@PathParam("id") Long id) throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (id == null) {
	    throw new BadRequestException();
	}

	HosterResponseFactory factory = new HosterResponseFactory();
	RootResource resource = factory.getHoster(id);
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("find")
    @Produces(RestMediaType.Json)
    public Response getHosterUsingRange(@QueryParam("range") Long range) throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (range == null) {
	    throw new BadRequestException();
	}

	LatLng point1 = new LatLng(32.666933, -16.924055);
	LatLng point2 = new LatLng(32.666933, -16.954055);
	double distance = LatLngTool.distance(point1, point2, LengthUnit.METER);
	return Response.ok((int) distance).build();
    }
}
