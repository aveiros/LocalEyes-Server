package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class UserDAO extends GenericDAO<EntityUser> {
    public UserDAO() {
	super(EntityUser.class);
    }

    public EntityUser findByUsername(String username) {
	String query = "from %s as user where user.username = '%s'";
	String fquery = String.format(query, this.getType().getSimpleName(), username);
	return DBAccess.getElement(this.getType(), fquery);
    }

    public EntityUser findByUsernameAndPassword(String username, String password) {
	String query = "from %s as user where user.username = '%s' and user.password = '%s'";
	String fquery = String.format(query, this.getType().getSimpleName(), username, password);
	return DBAccess.getElement(this.getType(), fquery);
    }
    
    public List<EntityUser> findByStatus(boolean isHosting) {
	String query = "from %s as user where user.hosting = %b";
	String fquery = String.format(query, this.getType().getSimpleName(), isHosting);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}