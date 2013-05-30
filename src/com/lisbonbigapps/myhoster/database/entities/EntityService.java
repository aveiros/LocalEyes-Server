package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityService extends PersistentObject {
    @OneToOne
    EntityUser hoster;

    @OneToOne
    EntityUser travel;

    @OneToMany
    List<EntityServiceFeedback> feedback = new ArrayList<EntityServiceFeedback>(0);

    public EntityUser getHoster() {
	return hoster;
    }

    public void setHoster(EntityUser hoster) {
	this.hoster = hoster;
    }

    public EntityUser getTravel() {
	return travel;
    }

    public void setTravel(EntityUser travel) {
	this.travel = travel;
    }

    public List<EntityServiceFeedback> getFeedback() {
	return feedback;
    }

    public void setFeedback(List<EntityServiceFeedback> feedback) {
	this.feedback = feedback;
    }
}
