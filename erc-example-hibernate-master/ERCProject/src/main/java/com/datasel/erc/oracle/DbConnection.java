package com.datasel.erc.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.datasel.patient.entity.Patient;

public class DbConnection {

	private static Session session;

	public static Session getSession() {

		if (session != null)
			return session;

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;

	}


	
	
}
