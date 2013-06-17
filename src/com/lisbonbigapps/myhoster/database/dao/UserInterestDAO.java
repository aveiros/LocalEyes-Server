package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityUserInterest;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class UserInterestDAO extends GenericDAO<EntityUserInterest> {
    public UserInterestDAO() {
	super(EntityUserInterest.class);
    }

     public List<EntityUserInterest> findByUserId(long userId) {
	String query = "from %s as interest where interest.user.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}