package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class ForbiddenException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "User might not have permission to access that resource!";

    public ForbiddenException() {
	super(Response.status(Status.FORBIDDEN).entity(new MessageResponseFactory().createError(message)).build());
    }

    public ForbiddenException(String message) {
	super(Response.status(Status.FORBIDDEN).entity(new MessageResponseFactory().createError(message)).build());
    }
}