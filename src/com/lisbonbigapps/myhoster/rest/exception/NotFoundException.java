package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class NotFoundException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "Server could not found that resource!";

    public NotFoundException() {
	super(Response.status(Status.NOT_FOUND).entity(new MessageResponseFactory().createError(message)).build());
    }

    public NotFoundException(String message) {
	super(Response.status(Status.NOT_FOUND).entity(new MessageResponseFactory().createError(message)).build());
    }
}