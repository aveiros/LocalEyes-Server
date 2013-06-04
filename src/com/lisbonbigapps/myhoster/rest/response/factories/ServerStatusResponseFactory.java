package com.lisbonbigapps.myhoster.rest.response.factories;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import com.lisbonbigapps.myhoster.app.Application;
import com.lisbonbigapps.myhoster.rest.response.resources.RootResource;
import com.lisbonbigapps.myhoster.rest.response.resources.ServerStatusResource;

public class ServerStatusResponseFactory {
    private final int timeout = 100;

    public RootResource createServerStatus() {
	ServerStatusResource resource = new ServerStatusResource();

	String base = System.getenv("CATALINA_BASE");
	String home = System.getenv("CATALINA_HOME");

	Properties vars = Application.getInstance().getProperties();
	String xmpp_server_host = vars.getProperty("XMPP_SERVER_HOST");
	String xmpp_server_port = vars.getProperty("XMPP_SERVER_PORT");
	String xmpp_server_rpc_host = vars.getProperty("XMPP_SERVER_XMLRPC_HOST");
	String xmpp_server_rpc_port = vars.getProperty("XMPP_SERVER_XMLRPC_PORT");

	resource.setServer_catalina_base(base);
	resource.setServer_catalina_home(home);
	resource.setServer_config(vars.size() > 0);
	resource.setServer_config_vars(this.assembleVars(vars));

	resource.setXmpp_server(this.check(xmpp_server_host, xmpp_server_port));
	resource.setXmpp_server_xmlrpc(this.check(xmpp_server_rpc_host, xmpp_server_rpc_port));

	return resource;
    }

    public boolean check(String host, String port) {
	if (host == null || port == null) {
	    return false;
	}

	try {
	    int p = Integer.parseInt(port);
	    Socket socket = new Socket();
	    socket.connect(new InetSocketAddress(host, p), timeout);
	    socket.close();
	    return true;
	} catch (Exception ex) {
	    return false;
	}
    }

    public HashMap<Object, Object> assembleVars(Properties vars) {
	HashMap<Object, Object> variables = new HashMap<Object, Object>();

	for (Entry<Object, Object> entry : vars.entrySet()) {
	    variables.put(entry.getKey(), entry.getValue());
	}

	return variables;
    }
}
