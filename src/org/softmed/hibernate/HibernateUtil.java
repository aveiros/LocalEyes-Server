package org.softmed.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// new Configuration().setInterceptor( new
			// PersistentObjectInterceptor() );
			// Create the SessionFactory from hibernate.cfg.xml
			// AnnotationConfiguration config = new AnnotationConfiguration();
			// config.setInterceptor(new PersistentObjectInterceptor());
			// AnnotationConfiguration cfg = config.configure();
			// cfg.setInterceptor(new PersistentObjectInterceptor());
			// SessionFactory factory = cfg.buildSessionFactory();

			Configuration cfg = new Configuration()
					.setInterceptor(new PersistentObjectInterceptor());
			SessionFactory factory = cfg.configure().buildSessionFactory();

			return factory;

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
