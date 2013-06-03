package com.lisbonbigapps.myhoster.rest.response.factories;

import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.database.dao.ServiceDAO;
import com.lisbonbigapps.myhoster.database.dao.ServiceFeedbackDAO;
import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceFeedback;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceFeedbackResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceResource;

public class ServiceResponseFactory {
    public RootResource getService(long userId, long serviceId) {
	ServiceDAO dao = new ServiceDAO();
	EntityService service = dao.findById(serviceId);

	if (service == null || service.getId() == null) {
	    return null;
	}

	EntityUser host = service.getHoster();
	EntityUser traveller = service.getTravel();

	if (host == null || host.getId() == null) {
	    return null;
	}

	if (traveller == null || traveller.getId() == null) {
	    return null;
	}

	return this.assembleServiceResource(service);
    }

    public List<RootResource> getServices(long userId) {
	ServiceDAO serviceDao = new ServiceDAO();

	List<EntityService> services = serviceDao.findByUserId(userId);

	if (services == null) {
	    return null;
	}

	return this.assembleServiceResourceList(services);
    }

    public RootResource createService(long hostId, long travellerId) {
	if (hostId == travellerId) {
	    return null;
	}

	UserDAO userDao = new UserDAO();
	EntityUser host = userDao.findById(hostId);
	EntityUser traveller = userDao.findById(travellerId);

	if (host == null || host.getId() == null) {
	    return null;
	}

	if (traveller == null || traveller.getId() == null) {
	    return null;
	}

	ServiceDAO serviceDAO = new ServiceDAO();

	EntityService service = new EntityService();
	service.setHoster(host);
	service.setTravel(traveller);

	serviceDAO.create(service);

	return this.assembleServiceResource(service);
    }

    public boolean removeService(long hosterId) {
	return true;
    }

    public List<RootResource> getServiceFeedback(long userId, long serviceId) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);
	if (user == null || user.getId() == null) {
	    return null;
	}

	ServiceDAO serviceDao = new ServiceDAO();
	EntityService service = serviceDao.findById(serviceId);
	if (service == null || service.getId() == null) {
	    return null;
	}

	EntityUser host = service.getHoster();
	EntityUser traveller = service.getTravel();

	if (host == null || host.getId() == null) {
	    return null;
	}

	if (traveller == null || traveller.getId() == null) {
	    return null;
	}

	if (host.getId() == userId || traveller.getId() == userId) {
	    ServiceFeedbackDAO serviceFeedbackDao = new ServiceFeedbackDAO();
	    List<EntityServiceFeedback> feedbacks = serviceFeedbackDao.findByServiceId(serviceId);
	    return feedbacks == null ? null : this.assembleServiceFeedBackResourceList(feedbacks);
	}

	return null;
    }

    public RootResource createServiceFeedback(long userId, long serviceId, String text, double rate) {
	ServiceDAO serviceDao = new ServiceDAO();
	EntityService service = serviceDao.findById(serviceId);

	if (service == null || service.getId() == null) {
	    return null;
	}

	EntityUser host = service.getHoster();
	EntityUser traveller = service.getTravel();
	if (host == null || host.getId() == null) {
	    return null;
	}

	if (traveller == null || traveller.getId() == null) {
	    return null;
	}

	EntityUser user = host.getId() == userId ? host : traveller.getId() == userId ? traveller : null;
	if (user == null) {
	    return null;
	}

	ServiceFeedbackDAO serviceFeedbackDao = new ServiceFeedbackDAO();

	EntityServiceFeedback feedback = new EntityServiceFeedback();
	feedback.setRate(rate);
	feedback.setUser(user);
	feedback.setText(text);
	feedback.setService(service);

	serviceFeedbackDao.create(feedback);

	return null;
    }

    private List<RootResource> assembleServiceResourceList(List<EntityService> services) {
	List<RootResource> r = new ArrayList<RootResource>();

	for (EntityService service : services) {
	    r.add(this.assembleServiceResource(service));
	}

	return r;
    }

    private RootResource assembleServiceResource(EntityService service) {
	UserResponseFactory userFactory = new UserResponseFactory();

	ServiceResource r = new ServiceResource();
	r.setId(service.getId());
	r.setHoster(userFactory.assembleUserResource(service.getHoster()));
	r.setTraveller(userFactory.assembleUserResource(service.getTravel()));

	return r;
    }

    private List<RootResource> assembleServiceFeedBackResourceList(List<EntityServiceFeedback> feedbacks) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityServiceFeedback feedback : feedbacks) {
	    resources.add(this.assembleServiceFeedBackResource(feedback));
	}

	return resources;
    }

    private RootResource assembleServiceFeedBackResource(EntityServiceFeedback feedback) {
	ServiceFeedbackResource r = new ServiceFeedbackResource();

	r.setRate(feedback.getRate());
	r.setText(feedback.getText());
	r.setUser(new UserResponseFactory().assembleUserResource(feedback.getUser()));

	return r;
    }
}
