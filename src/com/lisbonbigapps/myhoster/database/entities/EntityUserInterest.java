package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityUserInterest extends PersistentObject {
    String name;

    @OneToOne
    EntityUser user;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }
}