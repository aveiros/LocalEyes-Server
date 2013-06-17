package com.lisbonbigapps.myhoster.rest.response.factories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.app.Application;
import com.lisbonbigapps.myhoster.database.dao.ServiceDAO;
import com.lisbonbigapps.myhoster.database.dao.ServiceProfileDAO;
import com.lisbonbigapps.myhoster.database.dao.UserContactDAO;
import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.dao.UserFeedbackDAO;
import com.lisbonbigapps.myhoster.database.dao.UserInterestDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityService;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceProfile;
import com.lisbonbigapps.myhoster.database.entities.EntityServiceStatus;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.entities.EntityUserContact;
import com.lisbonbigapps.myhoster.database.entities.EntityUserFeedback;
import com.lisbonbigapps.myhoster.database.entities.EntityUserInterest;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.exception.InternalServerException;
import com.lisbonbigapps.myhoster.rest.response.resources.LocationResource;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServiceProfileResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserContactResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserFeedbackResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserResource;
import com.lisbonbigapps.myhoster.rest.util.Eval;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class UserResponseFactory {
    public RootResource getUser(long id) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(id);

	if (user == null || user.getId() == null) {
	    return null;
	}

	return assembleUserResource(user);
    }

    public RootResource getUser(String username, String password) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findByUsernameAndPassword(username, password);

	if (user == null || user.getId() == null) {
	    return null;
	}

	return assembleUserResource(user);
    }

    public RootResource getUser(String username) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findByUsername(username);

	if (user == null || user.getId() == null) {
	    return null;
	}

	return assembleUserResource(user);
    }

    public List<RootResource> getHosts() {
	UserDAO dao = new UserDAO();
	List<EntityUser> users = dao.findByStatus(true);

	if (users == null) {
	    return null;
	}

	return this.assembleUserResourceList(users);
    }

    public List<RootResource> getTravelers() {
	UserDAO dao = new UserDAO();
	List<EntityUser> users = dao.findByStatus(false);

	if (users == null) {
	    return null;
	}

	return this.assembleUserResourceList(users);
    }

    public RootResource createUser(String username, String password) {
	UserDAO userDao = new UserDAO();
	ServiceProfileDAO serviceProfileDao = new ServiceProfileDAO();

	EntityUser user = new EntityUser();
	user.setName("");
	user.setUsername(username);
	user.setPassword(password);

	EntityServiceProfile profile = new EntityServiceProfile();
	profile.setUser(user);

	userDao.create(user);
	serviceProfileDao.create(profile);

	return assembleUserResource(user);
    }

    public RootResource getUserLocation(long userId) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	return this.assembleLocationResource(user);
    }

    public List<RootResource> getUserContacts(long userId) {
	UserContactDAO dao = new UserContactDAO();
	List<EntityUserContact> contacts = dao.findByUserId(userId);

	if (contacts == null) {
	    return null;
	}

	return this.assembleUserContactResourceList(contacts);
    }

    public RootResource createUserContact(long userId, String countryCode, String number) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	UserContactDAO userContactDao = new UserContactDAO();

	EntityUserContact contact = new EntityUserContact();
	contact.setCode(countryCode);
	contact.setNumber(number);
	contact.setUser(user);

	long code = userContactDao.create(contact);
	if (code == -1) {
	    return null;
	}

	return this.assembleUserContactResource(contact);
    }

    public void deleteUserContact(long userId, Long contactId) {
	UserContactDAO userContactDao = new UserContactDAO();
	EntityUserContact contact = userContactDao.findById(contactId);

	if (contact == null || contact.getId() == null) {
	    return;
	}

	EntityUser user = contact.getUser();
	if (user == null || user.getId() == null) {
	    return;
	}

	if (user.getId() == userId) {
	    userContactDao.delete(contact);
	}
    }

    public RootResource updateUserServiceFee(long userId, long fee) {
	ServiceProfileDAO profileDao = new ServiceProfileDAO();
	EntityServiceProfile profile = profileDao.findByUserId(userId);
	EntityUser user = profile.getUser();

	if (profile == null || profile.getId() == null) {
	    return null;
	}

	if (user == null || user.getId() == null) {
	    return null;
	}

	profile.setFee(fee);
	profileDao.update(profile);

	return assembleUserResource(user);
    }

    public RootResource updateUserServiceDescription(long userId, String description) {
	ServiceProfileDAO profileDao = new ServiceProfileDAO();
	EntityServiceProfile profile = profileDao.findByUserId(userId);
	EntityUser user = profile.getUser();

	if (profile == null || profile.getId() == null) {
	    return null;
	}

	if (user == null || user.getId() == null) {
	    return null;
	}

	profile.setDescription(description);
	profileDao.update(profile);

	return assembleUserResource(user);
    }

    public RootResource updateUserLocation(long userId, double latitude, double longitude) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	user.setLatitude(latitude);
	user.setLongitude(longitude);
	DBAccess.updateItem(user);

	return this.assembleLocationResource(user);
    }

    public long updateUserPhoto(long userId, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return -1;
	}

	String fileName = String.format("%d.png", user.getId());
	String fileURI = Application.getInstance().buildUserPhotoStorageURI(fileName);

	long code = saveFile(uploadedInputStream, fileURI);

	if (code != -1) {
	    user.setPhoto(fileName);
	    userDao.update(user);
	}

	return code;
    }

    public void deleteUserPhoto(long userId) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    throw new InternalServerException();
	}

	user.setPhoto(null);
	userDao.update(user);
    }

    public boolean uptateUserStatus(long userId, boolean status) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return false;
	}

	user.setHosting(status);
	DBAccess.updateItem(user);
	return true;
    }

    public List<RootResource> getUserFeedback(long userId) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	UserFeedbackDAO userFeedbackDao = new UserFeedbackDAO();
	List<EntityUserFeedback> feedbacks = userFeedbackDao.findByUserId(userId);

	if (feedbacks == null) {
	    return null;
	}

	return this.assembleUserFeedbackResourceList(feedbacks);
    }

    public RootResource createUserFeedback(long fromUserId, long toUserId, String text) {
	if (fromUserId == toUserId) {
	    return null;
	}

	UserDAO userDao = new UserDAO();
	EntityUser fromUser = userDao.findById(fromUserId);
	EntityUser toUser = userDao.findById(toUserId);

	if (fromUser != null && fromUser.getId() != null && toUser != null && toUser.getId() != null) {
	    EntityUserFeedback feedback = new EntityUserFeedback();
	    feedback.setText(text);
	    feedback.setFrom(fromUser);
	    feedback.setTo(toUser);
	    DBAccess.saveItem(feedback);
	    return this.assembleUserFeedbackResource(feedback);
	}

	return null;
    }

    private long saveFile(InputStream uploadedInputStream, String uploadedFileLocation) {
	try {
	    OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
	    int read = 0;
	    byte[] bytes = new byte[1024];

	    out = new FileOutputStream(new File(uploadedFileLocation));

	    while ((read = uploadedInputStream.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	    }

	    out.flush();
	    out.close();

	    return 1;
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return -1;
    }

    private List<RootResource> assembleUserResourceList(List<EntityUser> users) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityUser user : users) {
	    resources.add(this.assembleUserResource(user));
	}

	return resources;
    }

    public UserResource assembleUserResource(EntityUser user) {
	UserInterestDAO interestDao = new UserInterestDAO();
	ServiceProfileDAO profileDao = new ServiceProfileDAO();
	ServiceDAO serviceDao = new ServiceDAO();

	EntityServiceProfile profile = profileDao.findByUserId(user.getId());
	List<EntityService> services = serviceDao.findByHostId(user.getId());
	List<EntityUserInterest> interests = interestDao.findByUserId(user.getId());

	UserResource userResource = new UserResource();
	userResource.setId(user.getId());
	userResource.setName(user.getName());
	userResource.setUsername(user.getUsername());
	userResource.setPhoto(user.getPhoto() == null ? Application.getInstance().getUserNoPhotoURI() : Application.getInstance().getUserPhotoURL(user.getPhoto()));
	userResource.setLocation(this.assembleUserLocationResource(user));
	userResource.setService(this.assembleUserServiceProfile(profile, services));
	userResource.setInterests(this.assembleUserInterestsList(interests));

	return userResource;
    }

    public LocationResource assembleUserLocationResource(EntityUser user) {
	LocationResource locationResource = new LocationResource();

	locationResource.setLatitude(user.getLatitude());
	locationResource.setLongitude(user.getLongitude());

	return locationResource;
    }

    public ServiceProfileResource assembleUserServiceProfile(EntityServiceProfile profile, List<EntityService> services) {
	ServiceProfileResource profileResource = new ServiceProfileResource();

	profileResource.setDescription(Eval.value(profile.getDescription(), ""));
	profileResource.setFee(profile.getFee() + "€");

	float rates = 0;
	long votes = 0;
	for (EntityService service : services) {
	    if (service.getStatus() == EntityServiceStatus.FINISHED) {
		float rate = service.getTravellerRate();
		if (rate > 0) {
		    votes += 1;
		    rates += service.getTravellerRate();
		}
	    }
	}

	profileResource.setRate(votes > 0 ? (rates / votes) : 0f);
	profileResource.setVotes(votes);

	return profileResource;
    }

    private List<String> assembleUserInterestsList(List<EntityUserInterest> userInterests) {
	if (userInterests == null) {
	    return new ArrayList<String>();
	}

	List<String> interests = new ArrayList<String>();
	for (EntityUserInterest userInterest : userInterests) {
	    interests.add(userInterest.getName());
	}

	return interests;
    }

    private List<RootResource> assembleUserContactResourceList(List<EntityUserContact> contacts) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityUserContact contact : contacts) {
	    resources.add(this.assembleUserContactResource(contact));
	}

	return resources;
    }

    private RootResource assembleUserContactResource(EntityUserContact contact) {
	UserContactResource resource = new UserContactResource();
	resource.setId(contact.getId());
	resource.setNumber(contact.getNumber());
	resource.setCode(contact.getCode());

	return resource;
    }

    private List<RootResource> assembleUserFeedbackResourceList(List<EntityUserFeedback> feedbacks) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityUserFeedback feedback : feedbacks) {
	    resources.add(this.assembleUserFeedbackResource(feedback));
	}

	return resources;
    }

    public RootResource assembleUserFeedbackResource(EntityUserFeedback feedback) {
	UserFeedbackResource resource = new UserFeedbackResource();
	resource.setText(feedback.getText());
	resource.setFrom(this.assembleUserResource(feedback.getFrom()));
	resource.setTo(this.assembleUserResource(feedback.getTo()));

	return resource;
    }

    public LocationResource assembleLocationResource(EntityUser user) {
	LocationResource resource = new LocationResource();
	resource.setLatitude(user.getLatitude());
	resource.setLongitude(user.getLongitude());

	return resource;
    }
}
