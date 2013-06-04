package com.lisbonbigapps.myhoster.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.rest.exception.InternalServerException;
import com.lisbonbigapps.myhoster.rest.response.factories.ServerStatusResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.util.RestMediaType;

@Path("/server")
public class ServerFacade {
    @GET
    @Produces(RestMediaType.Json)
    public Response getServices() throws Exception {
	ServerStatusResponseFactory factory = new ServerStatusResponseFactory();
	RootResource resource = factory.createServerStatus();

	if (resource == null) {
	    throw new InternalServerException();
	}

	return Response.ok(resource).build();
    }
}