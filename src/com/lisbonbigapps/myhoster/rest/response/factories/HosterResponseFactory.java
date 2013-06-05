package com.lisbonbigapps.myhoster.rest.response.factories;

import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.lisbonbigapps.myhoster.database.dao.UserDAO;
import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;

public class HosterResponseFactory {
    public RootResource getHoster(long id) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(id);

	if (user == null || user.getId() == null || !user.isHosting()) {
	    return null;
	}

	return assembleHostResource(user);
    }

    public List<RootResource> getHostsByDistance(long userId, Double latitude, Double longitude, double distance) {
	UserDAO dao = new UserDAO();
	EntityUser user = dao.findById(userId);

	if (user == null || user.getId() == null) {
	    return null;
	}

	distance = Math.max(0, distance);

	LatLng referencePoint;
	if (latitude == null || longitude == null) {
	    referencePoint = new LatLng(user.getLatitude(), user.getLongitude());
	} else {
	    referencePoint = new LatLng(latitude, longitude);
	}

	LatLng westPoint = LatLngTool.travel(referencePoint, -90, distance, LengthUnit.METER);
	LatLng eastPoint = LatLngTool.travel(referencePoint, 90, distance, LengthUnit.METER);
	LatLng northPoint = LatLngTool.travel(referencePoint, 0, distance, LengthUnit.METER);
	LatLng southPoint = LatLngTool.travel(referencePoint, 180, distance, LengthUnit.METER);

	double minLat = southPoint.getLatitude();
	double maxLat = northPoint.getLatitude();
	double minLong = westPoint.getLongitude();
	double maxLong = eastPoint.getLongitude();

	List<EntityUser> users = dao.findByLocation(minLat, maxLat, minLong, maxLong);
	if (users == null) {
	    return null;
	}

	return this.assembleHostsResourceList(users);
    }

    private List<RootResource> assembleHostsResourceList(List<EntityUser> users) {
	List<RootResource> resources = new ArrayList<RootResource>();

	for (EntityUser user : users) {
	    resources.add(this.assembleHostResource(user));
	}

	return resources;
    }

    private RootResource assembleHostResource(EntityUser user) {
	UserResponseFactory factory = new UserResponseFactory();
	return factory.assembleUserResource(user);
    }
}
