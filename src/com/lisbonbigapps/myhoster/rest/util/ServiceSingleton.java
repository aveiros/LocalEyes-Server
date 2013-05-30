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

    public String getRootLocation() {
	return "http://localhost:8080";
    }

    public String getServiceLocation() {
	return this.getRootLocation() + "/hoster";
    }

    public String getUserThumbnailLocation() {
	return this.getServiceLocation() + "/user/photo";
    }

    public String getUserPhoto(String photo) {
	return this.getUserThumbnailLocation() + "/" + photo;
    }

    public String getUserDefaultPhoto() {
	return this.getUserThumbnailLocation() + "/none.png";
    }
}
