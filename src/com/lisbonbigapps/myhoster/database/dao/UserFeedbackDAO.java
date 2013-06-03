package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityUserFeedback;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class UserFeedbackDAO extends GenericDAO<EntityUserFeedback> {
    public UserFeedbackDAO() {
	super(EntityUserFeedback.class);
    }

    public List<EntityUserFeedback> findByUserId(long userId) {
	String query = "from %s as feedback where feedback.to.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), userId);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}
