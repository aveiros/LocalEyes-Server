package com.lisbonbigapps.myhoster.database.dao;

import com.lisbonbigapps.myhoster.database.entities.EntityCity;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class CityDAO extends GenericDAO<EntityCity> {
    public CityDAO() {
	super(EntityCity.class);
    }

    public EntityCity findByName(String name) {
	String query = "from %s as city where city.name = '%s'";
	String fquery = String.format(query, this.getType().getSimpleName(), name);
	return DBAccess.getElement(this.getType(), fquery);
    }
}