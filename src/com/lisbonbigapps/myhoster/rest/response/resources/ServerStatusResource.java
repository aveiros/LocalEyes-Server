package com.lisbonbigapps.myhoster.rest.response.resources;

import java.util.HashMap;

public class ServerStatusResource extends RootResource {
    String server_catalina_base;
    String server_catalina_home;

    boolean server_config;
    HashMap<Object, Object> server_config_vars;

    boolean xmpp_server;
    boolean xmpp_server_xmlrpc;

    public String getServer_catalina_base() {
	return server_catalina_base;
    }

    public String getServer_catalina_home() {
	return server_catalina_home;
    }

    public void setServer_catalina_home(String server_catalina_home) {
	this.server_catalina_home = server_catalina_home;
    }

    public boolean isServer_config() {
	return server_config;
    }

    public void setServer_config(boolean server_config) {
	this.server_config = server_config;
    }

    public boolean isXmpp_server() {
	return xmpp_server;
    }

    public void setXmpp_server(boolean xmpp_server) {
	this.xmpp_server = xmpp_server;
    }

    public boolean isXmpp_server_xmlrpc() {
	return xmpp_server_xmlrpc;
    }

    public void setXmpp_server_xmlrpc(boolean xmpp_server_xmlrpc) {
	this.xmpp_server_xmlrpc = xmpp_server_xmlrpc;
    }

    public void setServer_catalina_base(String server_catalina_base) {
	this.server_catalina_base = server_catalina_base;
    }

    public HashMap<Object, Object> getServer_config_vars() {
	return server_config_vars;
    }

    public void setServer_config_vars(HashMap<Object, Object> server_config_vars) {
	this.server_config_vars = server_config_vars;
    }
}