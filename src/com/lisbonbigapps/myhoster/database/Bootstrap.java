package com.lisbonbigapps.myhoster.database;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class Bootstrap {
    public void init() throws Exception {
	EntityUser user1 = new EntityUser();
	user1.setName("Aquilino Viveiros");
	user1.setUsername("aveiros");
	user1.setPassword("12345");
	user1.setLongitude(0);
	user1.setLatitude(0);
	user1.setHosting(true);

	EntityUser user2 = new EntityUser();
	user2.setName("Cláudio Teixeira");
	user2.setUsername("claudiotx");
	user2.setPassword("12345");
	user2.setLongitude(0);
	user2.setLatitude(0);
	user2.setHosting(true);

	EntityUser user3 = new EntityUser();
	user3.setName("Micaela Vieira");
	user3.setUsername("mikasvieira");
	user3.setPassword("12345");
	user3.setLongitude(0);
	user3.setLatitude(0);
	user3.setHosting(true);
	
	DBAccess.saveItem(user1);
	DBAccess.saveItem(user2);
	DBAccess.saveItem(user3);
    }
}
