package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.softmed.services.PersistentObject;

@Entity
public class EntityCity extends PersistentObject {
    String name;
    String description;

    @Column(precision = 10, scale = 4)
    double longitude;

    @Column(precision = 10, scale = 4)
    double latitude;

    @ManyToOne
    EntityCountry country;

    @OneToMany
    List<EntityCityGroup> groups = new ArrayList<EntityCityGroup>(0);

    @OneToMany
    List<EntityCityPlace> places = new ArrayList<EntityCityPlace>(0);

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public EntityCountry getCountry() {
	return country;
    }

    public void setCountry(EntityCountry country) {
	this.country = country;
    }

    public List<EntityCityGroup> getGroups() {
	return groups;
    }

    public void setGroups(List<EntityCityGroup> groups) {
	this.groups = groups;
    }

    public List<EntityCityPlace> getPlaces() {
	return places;
    }

    public void setPlaces(List<EntityCityPlace> places) {
	this.places = places;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }
}