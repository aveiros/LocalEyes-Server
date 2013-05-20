package com.lisbonbigapps.myhoster.rest.response.resources;

public class MessageRestResource extends RootRestResource {
	protected String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}