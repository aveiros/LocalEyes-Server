package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;

import org.softmed.services.PersistentObject;

@Entity
public class EntityUserPreferences extends PersistentObject {
    String language;

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }
}
