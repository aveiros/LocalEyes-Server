package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.rest.response.resources.LocationResource;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserResource;

public class HosterResponseFactory {
    public RootResource getHoster(long id) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(id);

	if (user == null || user.getId() == null || !user.isHosting()) {
	    return null;
	}

	return assembleHosterResource(user);
    }

    private RootResource assembleHosterResource(EntityUser user) {
	UserResource r = new UserResource();
	r.setId(user.getId());
	r.setName(user.getName());
	r.setUsername(user.getUsername());

	LocationResource l = new LocationResource();
	l.setLatitude(user.getLatitude());
	l.setLongitude(user.getLongitude());

	r.setLocation(l);
	return r;
    }
}
