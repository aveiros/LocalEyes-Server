package com.lisbonbigapps.myhoster.rest.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import com.lisbonbigapps.myhoster.rest.RestMediaType;
import com.lisbonbigapps.myhoster.rest.exception.BadRequestException;
import com.lisbonbigapps.myhoster.rest.exception.NotFoundException;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;
import com.lisbonbigapps.myhoster.rest.response.factories.TravelerResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.factories.UserResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.util.Authentication;

@Path("/travelers")
public class TravelerFacade {
    @Context
    HttpServletRequest request;
    Authentication auth = new Authentication();

    @GET
    @Produces(RestMediaType.Json)
    public Response getTravelers() throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	List<RootResource> resources = factory.getTravelers();

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response getTravelerById(@PathParam("id") Long id) throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (id == null) {
	    throw new BadRequestException();
	}

	TravelerResponseFactory factory = new TravelerResponseFactory();
	RootResource resource = factory.getTraveler(id);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("find")
    @Produces(RestMediaType.Json)
    public Response getTravelerByGeoRange(@QueryParam("range") Long range) throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (range == null) {
	    throw new BadRequestException();
	}

	// return Response.ok(GeoHelper.distanceFrom(32.666933, -16.924055,
	// 32.666933, -16.954055)).build();
	return null;
    }
}
