package com.lisbonbigapps.myhoster.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.softmed.services.PersistentObject;

@Entity
public class EntityService extends PersistentObject {
    @Enumerated(value = EnumType.STRING)
    EntityServiceStatus status;

    @OneToOne
    EntityUser hoster;

    @OneToOne
    EntityUser travel;

    @Column(precision = 1, scale = 1, nullable = true)
    float hostRate;

    @Column(precision = 1, scale = 1, nullable = true)
    float travellerRate;

    @OneToMany
    List<EntityServiceFeedback> feedback = new ArrayList<EntityServiceFeedback>(0);

    public EntityServiceStatus getStatus() {
	return status;
    }

    public void setStatus(EntityServiceStatus status) {
	this.status = status;
    }

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

    public float getHostRate() {
	return hostRate;
    }

    public void setHostRate(float hostRate) {
	this.hostRate = hostRate;
    }

    public float getTravellerRate() {
	return travellerRate;
    }

    public void setTravellerRate(float travellerRate) {
	this.travellerRate = travellerRate;
    }
}
