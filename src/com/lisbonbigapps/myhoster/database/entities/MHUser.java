package com.lisbonbigapps.myhoster.database.entities;

import javax.persistence.Entity;

import org.softmed.services.PersistentObject;

@Entity
public class MHUser extends PersistentObject {
	String name;
	String username;
	String password;

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
}
