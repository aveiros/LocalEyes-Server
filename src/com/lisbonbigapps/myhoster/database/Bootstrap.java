package com.lisbonbigapps.myhoster.database;

import org.softmed.daos.GenericDAO;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;

public class Bootstrap {
    public void init() throws Exception {
	EntityUser user = new EntityUser();
	user.setName("Administrator");
	user.setUsername("admin");
	user.setPassword("12345");
	user.setLongitude(034.11);
	user.setLatitude(-34.1232);
	user.setHosting(true);

	GenericDAO dao = new GenericDAO();
	dao.connect();
	dao.store(user);
	dao.close();
    }
}
