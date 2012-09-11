package com.depp.hibernate.annotation;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.depp.hibernate.annotation.pojo.Member;
import com.depp.hibernate.annotation.util.HibernateUtil;

public class Client {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Member event = new Member();
		// event.setId(100L);
		event.setTitle("zhaolin");
		event.setBirthday(new Date());
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		session.save(event);
		session.getTransaction().commit();
		System.out.println(0);

	}
}
