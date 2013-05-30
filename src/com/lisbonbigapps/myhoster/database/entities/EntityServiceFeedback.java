package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityServiceFeedback extends PersistentObject {
    String text;

    @Column(precision = 1, scale = 1)
    double rate;

    @OneToOne
    EntityUser user;

    public double getRate() {
	return rate;
    }

    public void setRate(double rate) {
	this.rate = rate;
    }

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
}
