package com.lisbonbigapps.myhoster.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.lisbonbigapps.myhoster.rest.RestMediaType;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;

@Path("/test")
public class TestFacade {
    @GET
    @Produces(RestMediaType.Json)
    public Response getUser() {
	//return Response.ok("ok").build();	
//	return Response.status(Status.UNAUTHORIZED).entity(new MessageResponseFactory()
//	.createError(RestMessage.UserSessionNotFound)).build();
	
	throw new UnauthorizedException();
    }

}
