package com.lisbonbigapps.myhoster.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.database.Bootstrap;
import com.lisbonbigapps.myhoster.rest.RestMediaType;
import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootRestResource;

@Path("/bootstrap")
public class BootstrapFacade {
	@GET
	@Produces(RestMediaType.Json)
	public Response initBootstrap() {
		MessageResponseFactory factory = new MessageResponseFactory();		
		RootRestResource response;
		
		try {
			Bootstrap bt = new Bootstrap();
			bt.init();
			response = factory.createMessage("bootstrap initialized!");	
		} catch (Exception e) {
			response = factory.createError("bootstrap error!");
		}
		
		return Response.ok(response).build();
	}
}
