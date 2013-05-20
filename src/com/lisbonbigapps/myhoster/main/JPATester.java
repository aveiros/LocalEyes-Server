package com.lisbonbigapps.myhoster.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.miti.footzon.cache.XMLObjectCache;
import org.softmed.daos.GenericDAO;
import org.softmed.hibernate.HibernateDAOFactory;

public class JPATester implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		GenericDAO.setDaoFactory(new HibernateDAOFactory());
		GenericDAO dao = new GenericDAO();
		
		try {
			dao.connect();
			dao.close();
			//XMLObjectCache.setup();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}
}