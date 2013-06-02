package com.lisbonbigapps.myhoster.rest.response.resources;

public class XmppUserResource extends RootResource {
    String username;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}