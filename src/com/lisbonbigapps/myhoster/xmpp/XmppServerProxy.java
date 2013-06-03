package com.lisbonbigapps.myhoster.xmpp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

@SuppressWarnings("rawtypes")
public class XmppServerProxy {
	private static final String ServerUrl = "http://192.168.1.91:4560";
	private static final String ServerDomain = "localhost";

	public boolean isOnline() {
		String command = "status";
		Map<String, String> params = new HashMap<String, String>();
		HashMap response = (HashMap) this.executeJabberCall(command, params);
		return response == null ? false : true;
	}

	public boolean isUserRegistered(String userName) {
	    	/* TODO: not a safe call works only for online users */
	    
		String command = "user_resources";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user", userName);
		params.put("host", XmppServerProxy.ServerDomain);

		HashMap response = (HashMap) this.executeJabberCall(command, params);

		if (response == null) {
			return false;
		}

		Object[] resources = (Object[]) response.get("resources");

		return resources == null || resources.length == 0 ? false : true;
	}

	public boolean registerUser(String name, String password) {
		String command = "register";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		params.put("host", XmppServerProxy.ServerDomain);
		params.put("password", password);

		HashMap callResponse = (HashMap) this
				.executeJabberCall(command, params);
		return callResponse == null ? false : true;
	}

	public boolean unregisterUser(String name) {
		String command = "unregister";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		params.put("host", XmppServerProxy.ServerDomain);

		HashMap callResponse = (HashMap) this
				.executeJabberCall(command, params);
		return callResponse == null ? false : true;
	}

	private XmlRpcClient newJabberClient() {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
			config.setServerURL(new URL(XmppServerProxy.ServerUrl));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);

		return client;
	}

	private Object executeJabberCall(String command, Object params) {
		XmlRpcClient client = this.newJabberClient();
		Object[] callParams = new Object[] { params };
		Object response = null;

		try {
			response = client.execute(command, callParams);
		} catch (XmlRpcException e) {
			System.err.println("ERROR: executing xmpp command " + command);
		}

		return response;
	}
}
