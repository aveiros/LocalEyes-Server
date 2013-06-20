package com.lisbonbigapps.myhoster.rest.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.rest.exception.BadRequestException;
import com.lisbonbigapps.myhoster.rest.exception.NotFoundException;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;
import com.lisbonbigapps.myhoster.rest.response.factories.ServiceResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.util.Authentication;
import com.lisbonbigapps.myhoster.rest.util.RestMediaType;

@Path("/services")
public class ServiceFacade {
    @Context
    HttpServletRequest req;

    Authentication auth = new Authentication();

    @GET
    @Produces(RestMediaType.Json)
    public Response getServices() throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	List<RootResource> resources = factory.getServices(this.auth.getUserId());

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @GET
    @Path("host")
    @Produces(RestMediaType.Json)
    public Response getHostingServices() throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	List<RootResource> resources = factory.getServicesHost(this.auth.getUserId());

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @GET
    @Path("tourist")
    @Produces(RestMediaType.Json)
    public Response getxServices() throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	List<RootResource> resources = factory.getServicesTourist(this.auth.getUserId());

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @POST
    @Produces(RestMediaType.Json)
    public Response createService(@QueryParam("host") Long hosterId) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (hosterId == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	RootResource resource = factory.createService(this.auth.getUserId(), hosterId);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @PUT
    @Path("{id}/reply")
    @Produces(RestMediaType.Json)
    public Response serviceReply(@PathParam("id") Long serviceId, @QueryParam("answer") String reply) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null || reply == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	RootResource resource = factory.serviceReply(this.auth.getUserId(), serviceId, reply);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @PUT
    @Path("{id}/finish")
    @Produces(RestMediaType.Json)
    public Response serviceFinish(@PathParam("id") Long serviceId) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	RootResource resource = factory.serviceFinish(this.auth.getUserId(), serviceId);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @PUT
    @Path("{id}/rate")
    @Produces(RestMediaType.Json)
    public Response serviceRate(@PathParam("id") Long serviceId, @QueryParam("score") Integer score) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null || score == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	RootResource resource = factory.serviceRate(this.auth.getUserId(), serviceId, score);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response getService(@PathParam("id") Long serviceId) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	RootResource resource = factory.getService(this.auth.getUserId(), serviceId);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("{id}/feedback")
    @Produces(RestMediaType.Json)
    public Response getServiceFeedback(@PathParam("id") Long serviceId) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	List<RootResource> resources = factory.getServiceFeedback(this.auth.getUserId(), serviceId);

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @POST
    @Path("{id}/feedback")
    @Produces(RestMediaType.Json)
    public Response createServiceFeedback(@PathParam("id") Long serviceId, @QueryParam("text") String text) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (serviceId == null || text == null) {
	    throw new BadRequestException();
	}

	ServiceResponseFactory factory = new ServiceResponseFactory();
	factory.createServiceFeedback(this.auth.getUserId(), serviceId, text);

	return Response.ok().build();
    }
}
