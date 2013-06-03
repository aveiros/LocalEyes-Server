package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.rest.response.resources.*;

public class MessageResponseFactory {
    public RootResource createError(String message) {
	ErrorResource r = new ErrorResource();
	r.setMessage(message);
	return r;
    }

    public RootResource createMessage(String message) {
	MessageResource r = new MessageResource();
	r.setMessage(message);
	return r;
    }
}
