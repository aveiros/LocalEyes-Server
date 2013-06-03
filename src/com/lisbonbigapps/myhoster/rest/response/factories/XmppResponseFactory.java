package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.rest.exception.InternalServerException;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.XmppUserResource;
import com.lisbonbigapps.myhoster.xmpp.XmppServerProxy;

public class XmppResponseFactory {
    public RootResource getUser(String username) {
	XmppServerProxy server = new XmppServerProxy();
	if (!server.isOnline()) {
	    throw new InternalServerException();
	}

	boolean hasUser = server.isUserRegistered(username);
	return hasUser ? this.assembleResource(username) : null;
    }

    public RootResource createUser(String username, String password) {
	XmppServerProxy server = new XmppServerProxy();
	if (!server.isOnline()) {
	    throw new InternalServerException();
	}

	server.registerUser(username, password);
	return this.assembleResource(username);
    }

    public RootResource assembleResource(String username) {
	XmppUserResource resource = new XmppUserResource();
	resource.setUsername(username);
	return resource;
    }
}
