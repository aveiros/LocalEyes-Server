package com.lisbonbigapps.myhoster.rest.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lisbonbigapps.myhoster.rest.response.resources.UserSessionResource;

public class Authentication {
    final String sessionObjName = "SESSION_OBJECT";
    HttpServletRequest httpRequest;

    public Authentication(HttpServletRequest request) {
	this.httpRequest = request;
    }

    public Authentication() {
    }

    public boolean hasUserSession() {
	UserSessionResource userSession = this.getUserSession();
	return userSession == null ? false : true;
    }

    public void clearUserSession() {
	this.getSession().removeAttribute(sessionObjName);
    }

    public long getUserId() {
	UserSessionResource userSession = this.getUserSession();
	return userSession.getUserId();
    }

    private UserSessionResource getUserSession() {
	Object sessionObj = this.getSession().getAttribute(sessionObjName);
	return (UserSessionResource) sessionObj;
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
}
