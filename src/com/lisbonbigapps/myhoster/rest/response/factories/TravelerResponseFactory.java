package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;

public class TravelerResponseFactory {
    public RootResource getTraveler(long id) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(id);

	if (user == null || user.getId() == null || user.isHosting()) {
	    return null;
	}

	return assembleTravelerResource(user);
    }

    private RootResource assembleTravelerResource(EntityUser user) {
	UserResponseFactory factory = new UserResponseFactory();
	return factory.assembleUserResource(user);
    }
}
