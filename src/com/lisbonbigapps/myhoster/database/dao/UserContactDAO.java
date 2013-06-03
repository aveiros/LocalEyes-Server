package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.entities.EntityUserContact;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class UserContactDAO extends GenericDAO<EntityUserContact> {
    public UserContactDAO() {
	super(EntityUserContact.class);
    }

    public List<EntityUserContact> findByUserId(long userId) {
	String query = "from %s as contacts where contacts.user.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}