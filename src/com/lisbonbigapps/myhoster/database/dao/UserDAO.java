package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;
import java.util.Locale;

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

    public List<EntityUser> findByLocation(long excludeUserId, double minLat, double maxLat, double minLong, double maxLong) {
	String _minLat = String.format(Locale.US, "%f", minLat);
	String _maxLat = String.format(Locale.US, "%f", maxLat);
	String _minLong = String.format(Locale.US, "%f", minLong);
	String _maxLong = String.format(Locale.US, "%f", maxLong);

	String query = "from %s as user where user.id not in (%d) and user.hosting = %b and user.latitude > %s and user.latitude < %s and user.longitude > %s and user.longitude < %s";
	String fquery = String.format(query, this.getType().getSimpleName(), excludeUserId, true, _minLat, _maxLat, _minLong, _maxLong);
	return DBAccess.getDBItem(this.getType(), fquery);
    }
}