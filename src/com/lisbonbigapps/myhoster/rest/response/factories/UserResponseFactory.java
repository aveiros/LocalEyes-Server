package com.lisbonbigapps.myhoster.rest.response.factories;

import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.resources.UserResourceFactory;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.response.resources.LocationResource;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserSessionResource;
import com.lisbonbigapps.myhoster.rest.util.ServiceSingleton;

public class UserResponseFactory {
    public RootResource getUser(long id) {
	EntityUser user = null;

	try {
	    user = UserResourceFactory.getById(id);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (user == null || user.getId() == null) {
	    return null;
	}

	return assembleUserResource(user);
    }

    public RootResource getUser(String username, String password) {
	if (username == null || password == null) {
	    return null;
	}

	EntityUser u = null;

	try {
	    u = UserResourceFactory.getUser(username, password);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (u == null || u.getId() == null) {
	    return null;
	}

	return assembleUserResource(u);
    }

    public List<RootResource> getUsers() {
	List<RootResource> users = new ArrayList<RootResource>();

	try {
	    List<EntityUser> entities = UserResourceFactory.getList();
	    for (EntityUser entityUser : entities) {
		users.add(assembleUserResource(entityUser));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return users;
    }

    public List<RootResource> getHosters() {
	List<RootResource> hosters = new ArrayList<RootResource>();

	try {
	    List<EntityUser> entities = UserResourceFactory.getList();
	    for (EntityUser entityUser : entities) {
		if (entityUser.isHosting()) {
		    hosters.add(assembleUserResource(entityUser));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hosters;
    }

    public List<RootResource> getTravelers() {
	List<RootResource> travelers = new ArrayList<RootResource>();

	try {
	    List<EntityUser> entities = UserResourceFactory.getList();
	    for (EntityUser entityUser : entities) {
		if (!entityUser.isHosting()) {
		    travelers.add(assembleUserResource(entityUser));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return travelers;
    }

    public RootResource getUser(String username) {
	EntityUser u = null;

	try {
	    u = UserResourceFactory.getUser(username);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (u == null || u.getId() == null) {
	    return null;
	}

	return assembleUserResource(u);
    }

    public RootResource createUserSession(UserResource u) {
	UserSessionResource r = new UserSessionResource();
	r.setUserId(u.getId());
	return r;
    }

    public RootResource registerUser(String username, String password) {
	EntityUser user = new EntityUser();
	user.setUsername(username);
	user.setPassword(password);
	DBAccess.saveItem(user);
	return assembleUserResource(user);
    }

    public RootResource getLocation(long userId) {
	LocationResource location = null;
	EntityUser user = this.getUserEntity(userId);

	if (user != null) {
	    location = new LocationResource();
	    location.setLatitude(user.getLatitude());
	    location.setLongitude(user.getLongitude());
	}

	return location;
    }

    public RootResource updateLocation(long userId, Double latitude, Double longitude) {
	if (latitude == null || longitude == null) {
	    return null;
	}

	LocationResource location = null;
	EntityUser user = this.getUserEntity(userId);

	if (user != null) {
	    location = new LocationResource();
	    location.setLatitude(latitude);
	    location.setLongitude(longitude);
	    user.setLatitude(latitude);
	    user.setLongitude(longitude);
	    DBAccess.updateItem(user);
	}

	return location;
    }

    public boolean updateHostingStatus(long userId, Boolean status) {
	if (status == null) {
	    return false;
	}

	EntityUser user = this.getUserEntity(userId);
	if (user != null) {
	    user.setHosting(status);
	    DBAccess.updateItem(user);
	    return true;
	}

	return false;
    }

    private EntityUser getUserEntity(long userId) {
	EntityUser user = null;

	try {
	    user = UserResourceFactory.getById(userId);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (user == null || user.getId() == null) {
	    return null;
	}

	return user;
    }

    private RootResource assembleUserResource(EntityUser user) {
	UserResource r = new UserResource();
	r.setId(user.getId());
	r.setName(user.getName());
	r.setUsername(user.getUsername());
	r.setPhoto(ServiceSingleton.getInstance().getUserDefaultPhoto());

	LocationResource l = new LocationResource();
	l.setLatitude(user.getLatitude());
	l.setLongitude(user.getLongitude());

	r.setLocation(l);
	return r;
    }

}
