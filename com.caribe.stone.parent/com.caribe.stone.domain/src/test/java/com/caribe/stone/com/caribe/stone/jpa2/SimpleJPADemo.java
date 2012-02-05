package com.caribe.stone.com.caribe.stone.jpa2;


import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import junit.framework.Assert;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.Test;

import com.caribe.stone.domain.entities.Message;
import com.caribe.stone.domain.entities.SimpleEntity;

/**
 * Hello world!
 * 
 */
public class SimpleJPADemo {
	// -javaagent:e:\study\program\JavaFramework\maven2\repository\org\apache\openjpa\openjpa\1.2.1\openjpa-1.2.1.jar

	@Test
	public void defaultPersistence() {
		EntityManagerFactory factory = OpenJPAPersistence.createEntityManagerFactory("teaUnit",
				"META-INF/persistence.xml");

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Message m = new Message("Hello Persistence!");
		em.persist(m);
		em.getTransaction().commit();
		em.close();
		Assert.assertNotNull(m.getId());
	}

	@Test
	public void configPersistenPath() {
		OpenJPAEntityManagerFactory mf = OpenJPAPersistence.createEntityManagerFactory("simpleUnit1",
				"jpa/simpleJPA.xml");
		OpenJPAEntityManager em = mf.createEntityManager();
		SimpleEntity se = new SimpleEntity();
		se.setName("simple");
		em.getTransaction().begin();
		em.persist(se);
		em.getTransaction().commit();

		SimpleEntity simple = em.find(SimpleEntity.class, se.getId());
		assertEquals("simple", simple.getName());
	}

}
