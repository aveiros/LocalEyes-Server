package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityServiceProfile extends PersistentObject {
    @OneToOne
    EntityUser user;

    String description;

    long fee;

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public long getFee() {
	return fee;
    }

    public void setFee(long fee) {
	this.fee = fee;
    }
}