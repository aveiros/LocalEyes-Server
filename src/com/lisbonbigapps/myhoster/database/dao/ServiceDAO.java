package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class ServiceDAO extends GenericDAO<EntityService> {
    public ServiceDAO() {
	super(EntityService.class);
    }

    public List<EntityService> findByUserId(long userId) {
	String query = "from %s as service where service.hoster.id = %d or service.travel.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId, userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }

    public List<EntityService> findByHostId(long userId) {
	String query = "from %s as service where service.hoster.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }

    public List<EntityService> findByTravellerId(long userId) {
	String query = "from %s as service where service.travel.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}