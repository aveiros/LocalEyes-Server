package com.lisbonbigapps.myhoster.rest.response.resources;

public class ServiceFeedbackResource extends RootResource {
    String text;
    double rate;
    UserResource user;

    public double getRate() {
	return rate;
    }

    public void setRate(double rate) {
	this.rate = rate;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public UserResource getUser() {
	return user;
    }

    public void setUser(UserResource user) {
	this.user = user;
    }
}
