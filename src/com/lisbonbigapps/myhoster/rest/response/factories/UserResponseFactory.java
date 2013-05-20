package com.lisbonbigapps.myhoster.rest.response.factories;

import com.lisbonbigapps.myhoster.database.entities.MHUser;
import com.lisbonbigapps.myhoster.database.resources.UserResource;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.response.resources.RootRestResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserRestResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserSessionRestResource;

public class UserResponseFactory {
	public RootRestResource getUser(long id) {
		MHUser user = null;

		try {
			user = UserResource.getById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user == null || user.getId() == null) {
			return null;
		}

		return assembleUserResource(user);
	}

	public RootRestResource getUser(String username, String password) {
		if (username == null || password == null) {
			return null;
		}

		MHUser u = null;

		try {
			u = UserResource.getUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u == null || u.getId() == null) {
			return null;
		}

		return assembleUserResource(u);
	}
	
	public RootRestResource getUser(String username) {
		MHUser u = null;

		try {
			u = UserResource.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u == null || u.getId() == null) {
			return null;
		}

		return assembleUserResource(u);
	}

	public RootRestResource createUserSession(UserRestResource u) {
		UserSessionRestResource r = new UserSessionRestResource();
		r.setUserId(u.getId());
		return r;
	}
	
	public RootRestResource registerUser(String username, String password) {
		MHUser user = new MHUser();
		user.setUsername(username);
		user.setPassword(password);		
		DBAccess.saveItem(user);		
		return assembleUserResource(user);
	}

	private RootRestResource assembleUserResource(MHUser user) {
		UserRestResource r = new UserRestResource();
		r.setId(user.getId());
		r.setName(user.getName());
		r.setUsername(user.getUsername());
		return r;
	}
}
