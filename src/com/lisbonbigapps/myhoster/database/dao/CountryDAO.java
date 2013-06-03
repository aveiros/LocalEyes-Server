package com.lisbonbigapps.myhoster.database.dao;

import com.lisbonbigapps.myhoster.database.entities.EntityCity;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class CountryDAO extends GenericDAO<EntityCity> {
    public CountryDAO() {
	super(EntityCity.class);
    }

    public EntityCity findByName(String name) {
	String query = "from %s as country where country.name = '%s'";
	String fquery = String.format(query, this.getType().getSimpleName(), name);
	return DBAccess.getElement(this.getType(), fquery);
    }
}