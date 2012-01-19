package com.caribe.stone.com.caribe.stone.jpa2;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;

import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.Test;

/**
 * Hello world!
 * 
 */
public class SimpleJPADemo {
//-javaagent:e:\study\program\JavaFramework\maven2\repository\org\apache\openjpa\openjpa\1.2.1\openjpa-1.2.1.jar
	
	@Test
	public void simple() {
		EntityManagerFactory factory = OpenJPAPersistence.createEntityManagerFactory("teaUnit","META-INF/persistence.xml");

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Message("Hello Persistence!"));
		em.getTransaction().commit();
		em.close();
	}
}

//@Entity
// comment this @entity or  spring will load this class Message why???
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
