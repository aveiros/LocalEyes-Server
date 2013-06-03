package com.lisbonbigapps.myhoster.rest.response.factories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.dao.UserFeedbackDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.entities.EntityUserFeedback;
import com.lisbonbigapps.myhoster.database.util.DBAccess;
import com.lisbonbigapps.myhoster.rest.exception.InternalServerException;
import com.lisbonbigapps.myhoster.rest.response.resources.LocationResource;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserFeedbackResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserResource;
import com.lisbonbigapps.myhoster.rest.response.resources.UserSessionResource;
import com.lisbonbigapps.myhoster.rest.util.ServiceSingleton;
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

    public List<RootResource> getHosters() {
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

    public RootResource createUserSession(UserResource u) {
	UserSessionResource r = new UserSessionResource();
	r.setUserId(u.getId());
	return r;
    }

    public RootResource createUser(String username, String password) {
	UserDAO userDao = new UserDAO();

	EntityUser user = new EntityUser();
	user.setName("");
	user.setUsername(username);
	user.setPassword(password);

	return userDao.create(user) == -1 ? null : assembleUserResource(user);
    }

    public RootResource getLocation(long userId) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	return this.assembleLocationResource(user);
    }

    public RootResource updateLocation(long userId, double latitude, double longitude) {
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

    public long updatePhoto(long userId, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    return -1;
	}

	String fileName = String.format("%d.png", user.getId());
	String fileURI = ServiceSingleton.getInstance().buildUserPhotoURI(fileName);

	long code = saveFile(uploadedInputStream, fileURI);

	if (code != -1) {
	    user.setPhoto(fileName);
	    userDao.update(user);
	}

	return code;
    }

    public void deletePhoto(long userId) {
	UserDAO userDao = new UserDAO();
	EntityUser user = userDao.findById(userId);

	if (user == null || user.getId() == null) {
	    throw new InternalServerException();
	}

	user.setPhoto(null);
	userDao.update(user);
    }

    public boolean uptateStatus(long userId, boolean status) {
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
	List<RootResource> r = new ArrayList<RootResource>();

	for (EntityUser user : users) {
	    r.add(this.assembleUserResource(user));
	}

	return r;
    }

    public UserResource assembleUserResource(EntityUser user) {
	UserResource r = new UserResource();
	r.setId(user.getId());
	r.setName(user.getName());
	r.setUsername(user.getUsername());
	String photo = user.getPhoto();
	r.setPhoto(ServiceSingleton.getInstance().getUserPhotoURL(photo == null ? "none.png" : user.getPhoto()));
	
	LocationResource l = new LocationResource();
	l.setLatitude(user.getLatitude());
	l.setLongitude(user.getLongitude());

	r.setLocation(l);
	return r;
    }

    private List<RootResource> assembleUserFeedbackResourceList(List<EntityUserFeedback> feedbacks) {
	List<RootResource> feedbackResourceList = new ArrayList<RootResource>();

	for (EntityUserFeedback feedback : feedbacks) {
	    feedbackResourceList.add(this.assembleUserFeedbackResource(feedback));
	}

	return feedbackResourceList;
    }

    public RootResource assembleUserFeedbackResource(EntityUserFeedback feedback) {
	UserFeedbackResource r = new UserFeedbackResource();
	r.setText(feedback.getText());

	r.setFrom(this.assembleUserResource(feedback.getFrom()));
	r.setTo(this.assembleUserResource(feedback.getTo()));

	return r;
    }

    public LocationResource assembleLocationResource(EntityUser user) {
	LocationResource r = new LocationResource();
	r.setLatitude(user.getLatitude());
	r.setLongitude(user.getLongitude());
	return r;
    }
}
