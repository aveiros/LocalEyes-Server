package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.softmed.services.PersistentObject;

@Entity
public class EntityGallery extends PersistentObject {
    String name;
    String description;
    String timestamp;

    @Column(precision = 10, scale = 4)
    double longitude;

    @Column(precision = 10, scale = 4)
    double latitude;

    @ManyToOne
    EntityUser user;

    @OneToMany
    List<EntityPhoto> photos = new ArrayList<EntityPhoto>(0);

    @OneToMany
    List<EntityComment> comments = new ArrayList<EntityComment>(0);

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

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

    public EntityUser getUser() {
	return user;
    }

    public void setUser(EntityUser user) {
	this.user = user;
    }

    public List<EntityComment> getComments() {
	return comments;
    }

    public void setComments(List<EntityComment> comments) {
	this.comments = comments;
    }

    public List<EntityPhoto> getPhotos() {
	return photos;
    }

    public void setPhotos(List<EntityPhoto> photos) {
	this.photos = photos;
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