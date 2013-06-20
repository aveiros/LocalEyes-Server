package com.lisbonbigapps.myhoster.database;

import com.lisbonbigapps.myhoster.database.entities.EntityServiceProfile;
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
	user1.setPhoneNumber("+351966455966");

	EntityServiceProfile profile1 = new EntityServiceProfile();
	profile1.setUser(user1);
	profile1.setFee(20);

	EntityUser user2 = new EntityUser();
	user2.setName("Cláudio Teixeira");
	user2.setUsername("claudiotx");
	user2.setPassword("12345");
	user2.setLongitude(0);
	user2.setLatitude(0);
	user2.setHosting(true);
	user2.setPhoneNumber("+351968648616");

	EntityServiceProfile profile2 = new EntityServiceProfile();
	profile2.setUser(user2);
	profile2.setFee(18);

	EntityUser user3 = new EntityUser();
	user3.setName("Micaela Vieira");
	user3.setUsername("mikasvieira");
	user3.setPassword("12345");
	user3.setLongitude(0);
	user3.setLatitude(0);
	user3.setHosting(true);
	user3.setPhoneNumber("+351968277447");

	EntityServiceProfile profile3 = new EntityServiceProfile();
	profile3.setUser(user3);
	profile3.setFee(22);

	EntityUser user4 = new EntityUser();
	user4.setName("John Edward");
	user4.setUsername("tourist");
	user4.setPassword("12345");
	user4.setLongitude(0);
	user4.setLatitude(0);
	user4.setHosting(true);
	user4.setPhoneNumber("+351900000000");

	EntityServiceProfile profile4 = new EntityServiceProfile();
	profile4.setUser(user4);
	profile4.setFee(19);

	DBAccess.saveItem(user1);
	DBAccess.saveItem(user2);
	DBAccess.saveItem(user3);
	DBAccess.saveItem(user4);

	DBAccess.saveItem(profile1);
	DBAccess.saveItem(profile2);
	DBAccess.saveItem(profile3);
	DBAccess.saveItem(profile4);
    }
}
