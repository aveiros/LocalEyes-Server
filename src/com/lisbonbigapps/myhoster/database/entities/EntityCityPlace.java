package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityCityPlace extends PersistentObject {
    String name;
    String description;

    @Column(precision = 10, scale = 4)
    double longitude;

    @Column(precision = 10, scale = 4)
    double latitude;

    @OneToOne
    EntityCity city;

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

    public EntityCity getCity() {
	return city;
    }

    public void setCity(EntityCity city) {
	this.city = city;
    }
}
