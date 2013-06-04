package com.lisbonbigapps.myhoster.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
	try {
	    Application.getInstance().initConfig();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    Application.getInstance().initDAO();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}