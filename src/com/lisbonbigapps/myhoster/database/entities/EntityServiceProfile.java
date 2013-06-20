package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityServiceProfile extends PersistentObject {
    @OneToOne
    EntityUser user;

    String description;

    @Column(precision = 10, scale = 2)
    float fee;

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

    public float getFee() {
	return fee;
    }

    public void setFee(float fee) {
	this.fee = fee;
    }
}