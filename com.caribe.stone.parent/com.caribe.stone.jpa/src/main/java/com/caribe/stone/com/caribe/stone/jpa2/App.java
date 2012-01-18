package com.caribe.stone.com.caribe.stone.jpa2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.Test;

/**
 * Hello world!
 * 
 */
public class App {

	@Test
	public void tests() {
		OpenJPAEntityManagerFactory mf = OpenJPAPersistence.createEntityManagerFactory("teaUnit","META-INF/2.xml");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("teaUnit");
		System.out.println(factory.getClass());

		EntityManager em = factory.createEntityManager();
		System.out.println(em);
		em.getTransaction().begin();

		em.persist(new Message("Hello Persistence!"));

		em.getTransaction().commit();

		em.close();
	}
}

@Entity
class Message {
	@Id
	private long id = System.currentTimeMillis();

	@Basic
	private String message;

	@Basic
	private Date created = new Date();

	public Message() {
	}

	public Message(String msg) {
		message = msg;
	}

	public void setId(long val) {
		id = val;
	}

	public long getId() {
		return id;
	}

	public void setMessage(String msg) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setCreated(Date date) {
		created = date;
	}

	public Date getCreated() {
		return created;
	}
}
