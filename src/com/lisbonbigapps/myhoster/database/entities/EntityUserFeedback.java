package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityUserFeedback extends PersistentObject {
    String text;

    @OneToOne
    EntityUser from;

    @OneToOne
    EntityUser to;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public EntityUser getFrom() {
	return from;
    }

    public void setFrom(EntityUser from) {
	this.from = from;
    }

    public EntityUser getTo() {
	return to;
    }

    public void setTo(EntityUser to) {
	this.to = to;
    }
}
