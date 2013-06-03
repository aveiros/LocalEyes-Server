package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityUserContact extends PersistentObject {
    String code;

    String number;

    @ManyToOne
    EntityUser user;

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

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }
}