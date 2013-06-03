package com.lisbonbigapps.myhoster.rest.response.resources;

public class UserContactResource extends RootResource {
    long id;
    String code;
    String number;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }
}