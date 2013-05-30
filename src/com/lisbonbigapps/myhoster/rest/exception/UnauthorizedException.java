package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class UnauthorizedException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "User session not found! Please authenticate!";

    public UnauthorizedException() {
	super(Response.status(Status.UNAUTHORIZED).entity(new MessageResponseFactory().createError(message)).build());
    }

    public UnauthorizedException(String message) {
	super(Response.status(Status.UNAUTHORIZED).entity(new MessageResponseFactory().createError(message)).build());
    }
}