package com.lisbonbigapps.myhoster.database;

import com.lisbonbigapps.myhoster.database.entities.EntityCity;
import com.lisbonbigapps.myhoster.database.entities.EntityCountry;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceProfile;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.entities.EntityUserContact;
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

	EntityServiceProfile profile1 = new EntityServiceProfile();
	profile1.setUser(user1);
	profile1.setFee(20);

	EntityUserContact contact1 = new EntityUserContact();
	contact1.setCode("+351");
	contact1.setNumber("960000001");
	contact1.setUser(user1);

	EntityUser user2 = new EntityUser();
	user2.setName("Cláudio Teixeira");
	user2.setUsername("claudiotx");
	user2.setPassword("12345");
	user2.setLongitude(0);
	user2.setLatitude(0);
	user2.setHosting(true);

	EntityServiceProfile profile2 = new EntityServiceProfile();
	profile2.setUser(user2);
	profile2.setFee(18);

	EntityUserContact contact2 = new EntityUserContact();
	contact2.setCode("+351");
	contact2.setNumber("960000002");
	contact2.setUser(user2);

	EntityUser user3 = new EntityUser();
	user3.setName("Micaela Vieira");
	user3.setUsername("mikasvieira");
	user3.setPassword("12345");
	user3.setLongitude(0);
	user3.setLatitude(0);
	user3.setHosting(true);

	EntityServiceProfile profile3 = new EntityServiceProfile();
	profile3.setUser(user3);
	profile3.setFee(22);

	EntityUserContact contact3 = new EntityUserContact();
	contact3.setCode("+351");
	contact3.setNumber("960000003");
	contact3.setUser(user3);

	DBAccess.saveItem(user1);
	DBAccess.saveItem(user2);
	DBAccess.saveItem(user3);

	DBAccess.saveItem(profile1);
	DBAccess.saveItem(profile2);
	DBAccess.saveItem(profile3);

	DBAccess.saveItem(contact1);
	DBAccess.saveItem(contact2);
	DBAccess.saveItem(contact3);
    }
}
