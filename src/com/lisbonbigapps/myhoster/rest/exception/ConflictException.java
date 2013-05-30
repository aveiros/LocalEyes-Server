package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class ConflictException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "Server could not complete your request due a confict with the current state of the resource!";

    public ConflictException() {
	super(Response.status(Status.CONFLICT).entity(new MessageResponseFactory().createError(message)).build());
    }

    public ConflictException(String message) {
	super(Response.status(Status.CONFLICT).entity(new MessageResponseFactory().createError(message)).build());
    }
}