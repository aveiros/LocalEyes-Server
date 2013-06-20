package com.lisbonbigapps.myhoster.rest.api;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.rest.exception.BadRequestException;
import com.lisbonbigapps.myhoster.rest.exception.InternalServerException;
import com.lisbonbigapps.myhoster.rest.exception.NotFoundException;
import com.lisbonbigapps.myhoster.rest.exception.UnauthorizedException;
import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.factories.UserResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.factories.XmppResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserResource;
import com.lisbonbigapps.myhoster.rest.util.Authentication;
import com.lisbonbigapps.myhoster.rest.util.RestMediaType;
import com.lisbonbigapps.myhoster.xmpp.XmppServerProxy;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/user")
public class UserFacade {
    @Context
    HttpServletRequest req;

    Authentication auth = new Authentication();

    @GET
    @Produces(RestMediaType.Json)
    public Response getUser() {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource resource = factory.getUser(this.auth.getUserId());
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("login")
    @Produces(RestMediaType.Json)
    public Response getLogin(@QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
	this.auth.setHttpRequest(this.req);

	if (username == null || username.equals("")) {
	    throw new BadRequestException();
	}

	if (password == null || password.equals("")) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	UserResource resource = (UserResource) factory.getUser(username, password);

	if (resource == null) {
	    throw new NotFoundException();
	}

	this.auth.storeUserSession(resource.getId());
	return Response.ok(resource).build();
    }

    @GET
    @Path("logout")
    @Produces(RestMediaType.Json)
    public Response getLogout() throws Exception {
	this.auth.setHttpRequest(this.req);
	boolean hadSession = this.auth.hasUserSession();
	this.auth.clearUserSession();

	MessageResponseFactory factory = new MessageResponseFactory();
	RootResource resource = factory.createMessage(hadSession ? "User has to perform a login!" : "User logged out successfully!");
	return Response.ok(resource).build();
    }

    @POST
    @Path("register")
    @Produces(RestMediaType.Json)
    public Response registerUser(@QueryParam("name") String name, @QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
	if (username == null || username.equals("")) {
	    throw new BadRequestException();
	}

	if (password == null || password.equals("")) {
	    throw new BadRequestException();
	}

	XmppResponseFactory xmppFactory = new XmppResponseFactory();
	UserResponseFactory userFactory = new UserResponseFactory();

	RootResource xmppUser = xmppFactory.getUser(username);
	RootResource localUser = userFactory.getUser(username);

	if (xmppUser == null || localUser == null) {
	    MessageResponseFactory msgFactory = new MessageResponseFactory();
	    return Response.ok(msgFactory.createError("User already exists")).build();
	} else {
	    xmppFactory.createUser(name, username, password);
	    userFactory.createUser(name, username, password);
	    return Response.ok().build();
	}
    }

    @GET
    @Path("contacts")
    @Produces(RestMediaType.Json)
    public Response getUserContacts() {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	List<RootResource> resources = factory.getUserContacts(this.auth.getUserId());

	if (resources == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resources).build();
    }

    @POST
    @Path("contacts")
    @Produces(RestMediaType.Json)
    public Response getUserContacts(@QueryParam("code") String code, @QueryParam("number") String number) {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (code == null || code.equals("") || number == null || number.equals("")) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource resource = factory.createUserContact(this.auth.getUserId(), code, number);

	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @DELETE
    @Path("contacts/{id}")
    @Produces(RestMediaType.Json)
    public Response deleteUserContact(@PathParam("id") Long contactId) {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (contactId == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	factory.deleteUserContact(this.auth.getUserId(), contactId);

	return Response.ok().build();
    }

    @POST
    @Path("photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updatePhoto(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	long code = factory.updateUserPhoto(this.auth.getUserId(), uploadedInputStream, fileDetail);

	if (code == -1) {
	    throw new InternalServerException();
	}

	return Response.ok().build();
    }

    @DELETE
    @Path("photo")
    @Produces(RestMediaType.Json)
    public Response deletePhoto() {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	factory.deleteUserPhoto(this.auth.getUserId());

	return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(RestMediaType.Json)
    public Response getUserById(@PathParam("id") Long id) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (id == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource userResource = factory.getUser(id);
	if (userResource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(userResource).build();
    }

    @PUT
    @Path("/hosting")
    @Produces(RestMediaType.Json)
    public Response updateHostingStatus(@QueryParam("status") Boolean status) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (status == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	factory.uptateUserStatus(this.auth.getUserId(), status);

	return Response.ok().build();
    }

    @GET
    @Path("/available")
    @Produces(RestMediaType.Json)
    public Response userAvailable(@QueryParam("name") String userName) throws Exception {
	if (userName == null || userName.equals("")) {
	    throw new BadRequestException();
	}

	XmppServerProxy xmppServer = new XmppServerProxy();
	if (!xmppServer.isOnline()) {
	    throw new InternalServerException();
	}

	boolean xmppHasUser = xmppServer.isUserRegistered(userName);
	boolean hostHasUser = new UserResponseFactory().getUser(userName) == null ? false : true;

	MessageResponseFactory factory = new MessageResponseFactory();
	return Response.ok(factory.createMessage(String.valueOf(hostHasUser || xmppHasUser))).build();
    }

    @GET
    @Path("/location")
    @Produces(RestMediaType.Json)
    public Response getLocation() throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource resource = factory.getUserLocation(this.auth.getUserId());
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @PUT
    @Path("/location")
    @Produces(RestMediaType.Json)
    public Response updateLocation(@QueryParam("latitude") Double latitude, @QueryParam("longitude") Double longitude) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (latitude == null || longitude == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource resource = factory.updateUserLocation(this.auth.getUserId(), latitude, longitude);
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @GET
    @Path("{id}/feedback")
    @Produces(RestMediaType.Json)
    public Response getUserFeedback(@PathParam("id") Long userId) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (userId == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	List<RootResource> resource = factory.getUserFeedback(userId);
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @POST
    @Path("/feedback")
    @Produces(RestMediaType.Json)
    public Response createUserFeedback(@QueryParam("id") Long userId, @QueryParam("text") String text) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (text == null || text.equals("") || userId == null) {
	    throw new BadRequestException();
	}

	UserResponseFactory factory = new UserResponseFactory();
	RootResource resource = factory.createUserFeedback(this.auth.getUserId(), userId, text);
	if (resource == null) {
	    throw new NotFoundException();
	}

	return Response.ok(resource).build();
    }

    @PUT
    @Path("/service/fee")
    @Produces(RestMediaType.Json)
    public Response updateUserServiceFee(@QueryParam("value") String value) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (value == null || value.equals("")) {
	    throw new BadRequestException();
	}

	return Response.ok().build();
    }

    @PUT
    @Path("/service/description")
    @Produces(RestMediaType.Json)
    public Response updateUserServiceDescription(@QueryParam("text") String text) throws Exception {
	this.auth.setHttpRequest(this.req);
	if (!this.auth.hasUserSession()) {
	    throw new UnauthorizedException();
	}

	if (text == null || text.equals("")) {
	    throw new BadRequestException();
	}

	return Response.ok().build();
    }

}
