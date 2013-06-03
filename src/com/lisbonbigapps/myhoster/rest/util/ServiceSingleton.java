package com.lisbonbigapps.myhoster.rest.util;

public class ServiceSingleton {
    static ServiceSingleton instance = null;

    protected ServiceSingleton() {
    }

    public static ServiceSingleton getInstance() {
	if (instance == null) {
	    instance = new ServiceSingleton();
	}
	return instance;
    }

    public String getRootURL() {
	return "http://localhost:8080";
    }

    public String getServiceURL() {
	return this.getRootURL() + "/local";
    }

    public String getImagesURL() {
	return this.getServiceURL() + "/images";
    }

    public String getUserPhotoURL() {
	return this.getImagesURL() + "/users/photos";
    }

    public String getUserPhotoURL(String fileName) {
	return this.getUserPhotoURL() + "/" + fileName;
    }

    public String getUserNoPhotoURI() {
	return this.getUserPhotoURL() + "/none.jpg";
    }

    public String buildBaseURI() {
	return "c://eclipse-juno//local";
    }

    public String buildUserPhotoURI(String fileName) {
	return String.format("%s//users//photos//%s", this.buildBaseURI(), fileName);
    }
}