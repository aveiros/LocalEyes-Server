package com.lisbonbigapps.myhoster.rest.response.resources;

public class ServiceResource extends RootResource {
    long id;
    UserResource hoster;
    UserResource traveller;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public UserResource getHoster() {
	return hoster;
    }

    public void setHoster(UserResource hoster) {
	this.hoster = hoster;
    }

    public UserResource getTraveller() {
	return traveller;
    }

    public void setTraveller(UserResource traveller) {
	this.traveller = traveller;
    }
}