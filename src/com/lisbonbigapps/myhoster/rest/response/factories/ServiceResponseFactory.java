package com.lisbonbigapps.myhoster.rest.response.factories;

import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceResource;

public class ServiceResponseFactory {
    public RootResource getService(long userId, Long serviceId) {
	if (serviceId == null) {
	    return null;
	}

	EntityService service = DBAccess.getElement(EntityService.class, "id", String.valueOf(serviceId));

	if (service.getId() == null) {
	    return null;
	}

	if (service.getHoster().getId() == userId || service.getTravel().getId() == userId) {
	    return this.assembleServiceResource(service);
	}

	return null;
    }

    public List<RootResource> getServices(long userId) {
	String query = "from " + EntityService.class.getSimpleName() + " as service where service.hoster.id = " + userId;

	List<EntityService> entities = DBAccess.getDBItem(EntityService.class, query);
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityService entity : entities) {
	    resources.add(this.assembleServiceResource(entity));
	}

	return resources;
    }

    public RootResource createService(long hosterId, long travelerId) {
	if (hosterId == travelerId) {
	    return null;
	}

	EntityUser hoster = this.getUserById(hosterId);
	EntityUser travel = this.getUserById(travelerId);

	if (hoster.getId() == null || travel.getId() == null) {
	    return null;
	}

	EntityService service = new EntityService();
	service.setHoster(hoster);
	service.setTravel(travel);

	DBAccess.saveItem(service);
	return this.assembleServiceResource(service);
    }

    public boolean removeService(long hosterId) {
	return true;
    }

    private RootResource assembleServiceResource(EntityService service) {
	UserResponseFactory userFactory = new UserResponseFactory();

	ServiceResource serviceResource = new ServiceResource();
	serviceResource.setId(service.getId());
	serviceResource.setHoster(userFactory.assembleUserResource(service.getHoster()));
	serviceResource.setTraveller(userFactory.assembleUserResource(service.getHoster()));

	return serviceResource;
    }

    private EntityUser getUserById(long userId) {
	return DBAccess.getElement(EntityUser.class, "id", String.valueOf(userId));
    }
}
