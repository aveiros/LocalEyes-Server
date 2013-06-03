package com.lisbonbigapps.myhoster.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.rest.RestMediaType;

@Path("/test")
public class TestFacade {

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response test(@PathParam("id") long id) {

	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(id);
	user.setName("Aquilino de Sousa Viveiros");
	dao.update(user);

	return Response.ok().build();
    }
}
