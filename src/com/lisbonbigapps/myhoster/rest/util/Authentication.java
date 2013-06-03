package com.lisbonbigapps.myhoster.rest.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication {
    final String sessionObjName = "SESSION_OBJECT";
    HttpServletRequest httpRequest;

    public Authentication(HttpServletRequest request) {
	this.httpRequest = request;
    }

    public Authentication() {
    }

    public boolean hasUserSession() {
	UserSession userSession = this.getUserSession();
	return userSession == null ? false : true;
    }

    public void clearUserSession() {
	this.getSession().removeAttribute(sessionObjName);
    }

    public void storeUserSession(long userId) {
	UserSession userSession = new UserSession(userId);
	this.getSession().setAttribute(sessionObjName, userSession);
    }

    public long getUserId() {
	UserSession userSession = this.getUserSession();
	return userSession.getUserId();
    }

    private UserSession getUserSession() {
	Object sessionObj = this.getSession().getAttribute(sessionObjName);
	return (UserSession) sessionObj;
    }

    private HttpSession getSession() {
	return this.httpRequest.getSession(true);
    }

    public HttpServletRequest getHttpRequest() {
	return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
	this.httpRequest = httpRequest;
    }

    private class UserSession {
	long userId;

	public UserSession(long userId) {
	    this.userId = userId;
	}

	public long getUserId() {
	    return userId;
	}
    }
}
