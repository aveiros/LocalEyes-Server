package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityCityGroup extends PersistentObject {
    String name;

    @OneToOne
    EntityCountry country;

    @ManyToMany
    List<EntityUser> users = new ArrayList<EntityUser>(0);

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public EntityCountry getCountry() {
	return country;
    }

    public void setCountry(EntityCountry country) {
	this.country = country;
    }

    public List<EntityUser> getUsers() {
	return users;
    }

    public void setUsers(List<EntityUser> users) {
	this.users = users;
    }
}