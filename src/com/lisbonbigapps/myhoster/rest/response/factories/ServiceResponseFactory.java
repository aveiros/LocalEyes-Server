package com.lisbonbigapps.myhoster.rest.response.factories;

import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceFeedback;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceFeedbackResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceResource;

public class ServiceResponseFactory {
    public RootResource getService(long userId, Long serviceId) {
	if (serviceId == null) {
	    return null;
	}

	EntityService service = DBAccess.getElement(EntityService.class, "id", String.valueOf(serviceId));

	if (service == null || service.getId() == null) {
	    return null;
	}

	if (service.getHoster().getId() == userId || service.getTravel().getId() == userId) {
	    return this.assembleServiceResource(service);
	}

	return null;
    }

    public List<RootResource> getServices(long userId) {
	String query = "from " + EntityService.class.getSimpleName() + " as service where service.hoster.id = " + userId + " or service.travel.id = " + userId;

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

	if (hoster == null || hoster.getId() == null || travel == null || travel.getId() == null) {
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

    public List<RootResource> getServiceFeedback(long userId, long serviceId) {
	EntityUser user = this.getUserById(userId);
	if (user == null || user.getId() == null) {
	    return null;
	}

	EntityService service = DBAccess.getElement(EntityService.class, "id", String.valueOf(serviceId));
	if (service == null || service.getId() == null) {
	    return null;
	}

	if (service.getHoster().getId() == userId || service.getTravel().getId() == userId) {
	    String query = "from " + EntityServiceFeedback.class.getSimpleName() + " as feedback where service.id = " + service.getId();
	    List<EntityServiceFeedback> feedback = DBAccess.getDBItem(EntityServiceFeedback.class, query);
	    return this.assembleServiceFeedBackResource(feedback);
	}

	return null;
    }

    public void createServiceFeedback(long userId, long serviceId, String text, double rate) {
	String query = "from " + EntityService.class.getSimpleName() + " as service where service.hoster.id = " + userId;

	EntityService service = DBAccess.getElement(EntityService.class, query);
	if (service == null || service.getId() == null) {
	    return;
	}

	EntityUser user = DBAccess.getElement(EntityUser.class, "id", String.valueOf(userId));
	if (user == null || user.getId() == null) {
	    return;
	}

	EntityServiceFeedback feedback = new EntityServiceFeedback();
	feedback.setRate(rate);
	feedback.setUser(user);
	feedback.setText(text);
	feedback.setService(service);
	DBAccess.saveItem(feedback);
    }

    private RootResource assembleServiceResource(EntityService service) {
	UserResponseFactory userFactory = new UserResponseFactory();

	ServiceResource serviceResource = new ServiceResource();
	serviceResource.setId(service.getId());
	serviceResource.setHoster(userFactory.assembleUserResource(service.getHoster()));
	serviceResource.setTraveller(userFactory.assembleUserResource(service.getTravel()));

	return serviceResource;
    }

    private List<RootResource> assembleServiceFeedBackResource(List<EntityServiceFeedback> feedback) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityServiceFeedback f : feedback) {
	    ServiceFeedbackResource r = new ServiceFeedbackResource();
	    r.setRate(f.getRate());
	    r.setText(f.getText());
	    r.setUser(new UserResponseFactory().assembleUserResource(f.getUser()));
	    resources.add(r);
	}

	return resources;
    }

    private EntityUser getUserById(long userId) {
	return DBAccess.getElement(EntityUser.class, "id", String.valueOf(userId));
    }
}
