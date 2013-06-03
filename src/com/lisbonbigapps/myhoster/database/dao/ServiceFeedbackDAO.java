package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceFeedback;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class ServiceFeedbackDAO extends GenericDAO<EntityServiceFeedback> {
    public ServiceFeedbackDAO() {
	super(EntityServiceFeedback.class);
    }

    public List<EntityServiceFeedback> findByServiceId(long serviceId) {
	String query = "from %s as feedback where feedback.service.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), serviceId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}