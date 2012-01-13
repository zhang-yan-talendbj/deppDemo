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

import org.junit.Test;

/**
 * Hello world!
 * 
 */
public class App {

	@Test
	public void tests() {
		Map m = new HashMap();
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("teaUnit");
		m.put("dbdriver", "org.h2.Driver");
		m.put("dburl", "jdbc:h2:tcp://localhost/~/teaMilk");
		m.put("dbuser", "sa");
		m.put("dbpass", "");

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
