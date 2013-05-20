package com.lisbonbigapps.myhoster.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.lisbonbigapps.myhoster.jabber.JabberServerFacade;
import com.lisbonbigapps.myhoster.rest.RestMediaType;
import com.lisbonbigapps.myhoster.rest.RestMessage;
import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.factories.UserResponseFactory;
import com.lisbonbigapps.myhoster.rest.response.resources.RootRestResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserRestResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserSessionRestResource;

@Path("/user")
public class UserFacade {
	@Context
	HttpServletRequest req;

	@GET
	@Produces(RestMediaType.Json)
	public Response getUser() {
		HttpSession session = req.getSession(true);
		UserSessionRestResource userSession = (UserSessionRestResource) session
				.getAttribute("SESSION_OBJECT");

		if (userSession == null) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.UserSessionNotFound))
					.build();
		}

		RootRestResource response = new UserResponseFactory()
				.getUser(userSession.getUserId());

		if (response == null) {
			response = new MessageResponseFactory()
					.createError(RestMessage.UserNotFound);
		}

		return Response.ok(response).build();
	}

	@GET
	@Path("login")
	@Produces(RestMediaType.Json)
	public Response getLogin(@QueryParam("username") String username,
			@QueryParam("password") String password) throws Exception {

		HttpSession session = req.getSession(true);
		
		UserResponseFactory userFactory = new UserResponseFactory();
		RootRestResource userResource = userFactory.getUser(username, password);

		if (userResource == null) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.UserOrPasswordInvalid))
					.build();
		}

		session.setAttribute("SESSION_OBJECT",
				userFactory.createUserSession((UserRestResource) userResource));

		// HttpClient httpClient = new DefaultHttpClient();
		// HttpGet httpGetRequest = new
		// HttpGet("http://localhost:9090/plugins/userService/userservice?type=add&secret=bigsecret&username=kafka&password=drowssap&name=franz&email=franz@kafka.com");
		// HttpResponse httpGetResponse = httpClient.execute(httpGetRequest);
		//
		// if (httpGetResponse.getStatusLine().getStatusCode() == 200) {
		// System.out.print("all good!!");
		// } else {
		// System.out.print("something went wrong!!!");
		// }

		return Response.ok(userResource).build();
	}

	@GET
	@Path("logout")
	@Produces(RestMediaType.Json)
	public Response getLogout() throws Exception {
		HttpSession session = req.getSession(true);

		UserSessionRestResource userSession = (UserSessionRestResource) session
				.getAttribute("SESSION_OBJECT");

		if (userSession == null) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.UserSessionNotFound))
					.build();
		}

		session.removeAttribute("SESSION_OBJECT");

		return Response.ok(
				new MessageResponseFactory()
						.createMessage(RestMessage.UserSessionLogout)).build();
	}
	
	@GET
	@Path("register")
	@Produces(RestMediaType.Json)
	public Response registerUser(@QueryParam("username") String username,
			@QueryParam("password") String password) throws Exception {

		if (username == null || username.equals("")) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.ResourceMissingParam))
					.build();
		}
		
		if (password == null || password.equals("")) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.ResourceMissingParam))
					.build();
		}

		JabberServerFacade jserver = new JabberServerFacade();

		if (!jserver.isServerOnline()) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.ServerInternalError))
					.build();
		}
		
		UserResponseFactory userFactory = new UserResponseFactory();
		
		boolean jabberHasUser = jserver.isUserRegistered(username);
		boolean myHosterHasUser = userFactory.getUser(username) == null ? false : true;
		
		if(jabberHasUser || myHosterHasUser) {
			return Response.ok(
					new MessageResponseFactory()
							.createError("User already exists"))
					.build();
		} else {
			jserver.registerUser(username, password);
			return Response.ok(userFactory.registerUser(username, password)).build();
		}
	}

	@GET
	@Path("{id}")
	@Produces(RestMediaType.Json)
	public Response getUserById(@PathParam("id") long id) throws Exception {

		MessageResponseFactory factory = new MessageResponseFactory();
		HttpSession session = req.getSession(true);
		UserSessionRestResource userSession = (UserSessionRestResource) session
				.getAttribute("SESSION_OBJECT");

		if (userSession == null) {
			return Response.ok(
					factory.createError(RestMessage.UserSessionNotFound))
					.build();
		}

		RootRestResource r = new UserResponseFactory().getUser(id);

		if (r == null) {
			return Response.ok(factory.createError(RestMessage.UserNotFound))
					.build();
		}

		return Response.ok(r).build();
	}

	@GET
	@Path("/available")
	@Produces(RestMediaType.Json)
	public Response userExists(@QueryParam("name") String userName)
			throws Exception {

		if (userName == null || userName.equals("")) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.ResourceMissingParam))
					.build();
		}

		JabberServerFacade jserver = new JabberServerFacade();

		if (!jserver.isServerOnline()) {
			return Response.ok(
					new MessageResponseFactory()
							.createError(RestMessage.ServerInternalError))
					.build();
		}

		boolean jabberHasUser = jserver.isUserRegistered(userName);
		boolean myHosterHasUser = new UserResponseFactory().getUser(userName) == null ? false
				: true;

		return Response.ok(new MessageResponseFactory().createMessage(String.valueOf(myHosterHasUser || jabberHasUser))).build();
	}
}
