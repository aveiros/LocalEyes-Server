package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityPhoto extends PersistentObject {
    String timestamp;
    String description;

    @OneToOne
    EntityUser user;

    @OneToMany
    List<EntityComment> comments = new ArrayList<EntityComment>(0);

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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
}