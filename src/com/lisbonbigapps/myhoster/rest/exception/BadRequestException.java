package com.lisbonbigapps.myhoster.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lisbonbigapps.myhoster.rest.response.factories.MessageResponseFactory;

public class BadRequestException extends WebApplicationException {
    private static final long serialVersionUID = 1L;
    private static final String message = "Server could not complete your request due to parameters missing or unsupported!";    
    
    public BadRequestException() {
	super(Response.status(Status.BAD_REQUEST).entity(new MessageResponseFactory().createError(message)).build());
    }

    public BadRequestException(String message) {
	super(Response.status(Status.BAD_REQUEST).entity(new MessageResponseFactory().createError(message)).build());
    }
}