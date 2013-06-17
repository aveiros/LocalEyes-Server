package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityServiceFeedback extends PersistentObject {
    String text;

    @OneToOne
    EntityUser user;

    @ManyToOne
    EntityService service;

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public EntityService getService() {
	return service;
    }

    public void setService(EntityService service) {
	this.service = service;
    }
}
