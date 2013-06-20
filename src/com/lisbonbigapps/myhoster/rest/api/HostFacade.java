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

import com.lisbonbigapps.myhoster.rest.exception.BadRequestException;
import com.lisbonbigapps.myhoster.rest.exception.NotFoundException;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;
import com.lisbonbigapps.myhoster.rest.response.factories.HostResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.util.Authentication;
import com.lisbonbigapps.myhoster.rest.util.RestMediaType;

@Path("/hosts")
public class HostFacade {
    @Context
    HttpServletRequest request;
    Authentication auth = new Authentication();

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response getHostById(@PathParam("id") Long id) throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (id == null) {
	    throw new BadRequestException();
	}

	HostResponseFactory factory = new HostResponseFactory();
	RootResource resource = factory.getHost(id);
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("find")
    @Produces(RestMediaType.Json)
    public Response getHostByDistance(@QueryParam("distance") Double distance, @QueryParam("latitude") Double latitude, @QueryParam("longitude") Double longitude, @QueryParam("store") Boolean store)
	    throws Exception {
	this.auth.setHttpRequest(this.request);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (distance == null) {
	    throw new BadRequestException();
	}

	HostResponseFactory factory = new HostResponseFactory();
	List<RootResource> resources = factory.getHostsByDistance(this.auth.getUserId(), latitude, longitude, distance, store);

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }
}
