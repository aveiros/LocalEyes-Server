package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class InternalServerException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "Server could not fulfill your request!";

    public InternalServerException() {
	super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(new MessageResponseFactory().createError(message)).build());
    }

    public InternalServerException(String message) {
	super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(new MessageResponseFactory().createError(message)).build());
    }
}