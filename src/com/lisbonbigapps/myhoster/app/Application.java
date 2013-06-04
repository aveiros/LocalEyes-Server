package com.lisbonbigapps.myhoster.app;

import java.io.FileInputStream;
import java.util.Properties;

import org.softmed.daos.GenericDAO;
import org.softmed.hibernate.HibernateDAOFactory;

public class Application {
    private final Properties properties = new Properties();

    private Application() {
    }

    public static Application getInstance() {
	return SingletonHolder.INSTANCE;
    }

    public void initConfig() throws Exception {
	String path = System.getenv("CATALINA_BASE");

	if (path == null) {
	    throw new Exception("Could not find CATALINA_BASE environment variable.");
	}

	String file = "application.properties";
	FileInputStream in = new FileInputStream(path + "//" + file);
	this.properties.load(in);

	System.out.println(this.properties);

	in.close();
    }

    public void initDAO() throws Exception {
	GenericDAO.setDaoFactory(new HibernateDAOFactory());
	GenericDAO dao = new GenericDAO();
	dao.connect();
	dao.close();
    }

    private static class SingletonHolder {
	public static final Application INSTANCE = new Application();
    }

    public String getProperty(String name) {
	return this.properties.getProperty(name);
    }

    public String getServerURL() {
	String host = this.getProperty("SERVER_HOST");
	String port = this.getProperty("SERVER_PORT");
	return port == null ? "http://" + host : "http://" + host + ":" + port;
    }

    public String getApplicationURL() {
	String dir = this.getProperty("SERVER_APP_DIR");
	return this.getServerURL() + dir;
    }

    public String getImagesURL() {
	String dir = this.getProperty("SERVER_IMAGES_DIR");
	return this.getServerURL() + dir;
    }

    public String getUserPhotoURL(String fileName) {
	String dir = this.getProperty("SERVER_USER_PHOTOS_DIR");
	return this.getServerURL() + dir + "/" + fileName;
    }

    public String getUserNoPhotoURI() {
	String file = this.getProperty("SERVER_USER_NOPHOTO");
	return this.getUserPhotoURL(file);
    }

    public String buildRootStorageURI() {
	String dir = this.getProperty("STORAGE_ROOT_DIR");
	return dir;
    }

    public String buildUserPhotoStorageURI(String fileName) {
	String dir = this.getProperty("STORAGE_USER_PHOTOS_DIR");
	return dir + "//" + fileName;
    }

    public String getXmppServerHost() {
	String host = this.getProperty("XMPP_SERVER_HOST");
	return host;
    }

    public String getXmppServerXmlRpcHost() {
	String port = this.getProperty("XMPP_SERVER_XMLRPC_HOST");
	return port;
    }

    public String getXmppServerXmlRpcPort() {
	String port = this.getProperty("XMPP_SERVER_XMLRPC_PORT");
	return port;
    }

    public String getXmppServerXmlRpcUrl() {
	String host = this.getXmppServerXmlRpcHost();
	String port = this.getXmppServerXmlRpcPort();
	return "http://" + host + ":" + port;
    }
}