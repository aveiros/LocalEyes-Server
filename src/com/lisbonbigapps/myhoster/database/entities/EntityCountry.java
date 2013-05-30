package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.softmed.services.PersistentObject;

@Entity
public class EntityCountry extends PersistentObject {
    String name;

    @OneToMany
    List<EntityCity> cities = new ArrayList<EntityCity>(0);

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<EntityCity> getCities() {
	return cities;
    }

    public void setCities(List<EntityCity> cities) {
	this.cities = cities;
    }
}