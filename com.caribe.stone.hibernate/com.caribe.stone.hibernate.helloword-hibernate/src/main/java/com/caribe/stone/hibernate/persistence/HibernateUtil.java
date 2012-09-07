package com.caribe.stone.hibernate.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
