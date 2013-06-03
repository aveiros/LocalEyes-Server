package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.softmed.services.PersistentObject;
import org.hibernate.annotations.Type;

@Entity
public class EntityUser extends PersistentObject {
    String name;
    String username;
    String password;
    String photo;

    @Column(nullable = false, length = 1)
    @Type(type = "true_false")
    boolean hosting;

    @Column(precision = 10, scale = 4)
    double longitude;

    @Column(precision = 10, scale = 4)
    double latitude;

    @OneToMany
    List<EntityService> services = new ArrayList<EntityService>(0);

    @OneToMany
    List<EntityService> galleries = new ArrayList<EntityService>(0);

    @ManyToMany
    List<EntityCityGroup> groups = new ArrayList<EntityCityGroup>(0);

    @OneToMany
    List<EntityUserFeedback> userFeedback = new ArrayList<EntityUserFeedback>(0);

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
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

    public boolean isHosting() {
	return hosting;
    }

    public void setHosting(boolean hosting) {
	this.hosting = hosting;
    }

    public List<EntityService> getServices() {
	return services;
    }

    public void setServices(List<EntityService> services) {
	this.services = services;
    }

    public List<EntityService> getGalleries() {
	return galleries;
    }

    public void setGalleries(List<EntityService> galleries) {
	this.galleries = galleries;
    }

    public List<EntityCityGroup> getGroups() {
	return groups;
    }

    public void setGroups(List<EntityCityGroup> groups) {
	this.groups = groups;
    }

    public List<EntityUserFeedback> getUserFeedback() {
	return userFeedback;
    }

    public void setUserFeedback(List<EntityUserFeedback> userFeedback) {
	this.userFeedback = userFeedback;
    }

    public String getPhoto() {
	return photo;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }
}