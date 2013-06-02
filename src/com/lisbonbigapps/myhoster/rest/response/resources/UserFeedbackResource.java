package com.lisbonbigapps.myhoster.rest.response.resources;

public class UserFeedbackResource extends RootResource {
    String text;
    UserResource from;
    UserResource to;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public UserResource getFrom() {
	return from;
    }

    public void setFrom(UserResource from) {
	this.from = from;
    }

    public UserResource getTo() {
	return to;
    }

    public void setTo(UserResource to) {
	this.to = to;
    }
}