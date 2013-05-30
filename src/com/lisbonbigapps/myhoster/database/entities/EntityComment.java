package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityComment extends PersistentObject {
    String timestamp;
    String text;

    @OneToOne
    EntityUser user;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }
}