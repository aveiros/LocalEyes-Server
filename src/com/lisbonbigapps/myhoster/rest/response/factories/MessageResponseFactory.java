package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.rest.response.resources.*;

public class MessageResponseFactory {
	public RootRestResource createError(String message) {
		ErrorRestResource r = new ErrorRestResource();
		r.setMessage(message);	
		return r;
	}
	
	public RootRestResource createMessage(String message) {
		MessageRestResource r = new MessageRestResource();
		r.setMessage(message);	
		return r;
	}
}
