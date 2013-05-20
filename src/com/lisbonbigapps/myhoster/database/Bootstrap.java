package com.lisbonbigapps.myhoster.database;

import org.softmed.daos.GenericDAO;

import com.lisbonbigapps.myhoster.database.entities.MHUser;

public class Bootstrap {

	public void init() throws Exception {
		MHUser u = new MHUser();
		u.setName("Administrator");
		u.setUsername("admin");
		u.setPassword("12345");

		GenericDAO dao = new GenericDAO();
		dao.connect();
		dao.store(u);
		dao.close();
	}
	
}
