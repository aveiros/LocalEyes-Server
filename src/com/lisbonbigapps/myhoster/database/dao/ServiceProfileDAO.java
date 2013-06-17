package com.lisbonbigapps.myhoster.database.dao;

import com.lisbonbigapps.myhoster.database.entities.EntityServiceProfile;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class ServiceProfileDAO extends GenericDAO<EntityServiceProfile> {
    public ServiceProfileDAO() {
	super(EntityServiceProfile.class);
    }

    public EntityServiceProfile findByUserId(long userId) {
	String query = "from %s as profile where profile.user.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getElement(this.getType(), fquery);
    }
}