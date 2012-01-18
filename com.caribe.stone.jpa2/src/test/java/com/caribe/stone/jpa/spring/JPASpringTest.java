package com.caribe.stone.jpa.spring;
import static junit.framework.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.caribe.stone.jpa.entities.SimpleEntity;

@ContextConfiguration(locations = { "classpath:/JPA-CONFIG/spring/context.xml" })
public class JPASpringTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testName() throws Exception {
		 EntityManagerFactory emf = (EntityManagerFactory)
		 applicationContext.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		 em.getTransaction().begin();
		 SimpleEntity simple = new SimpleEntity();
		 simple.setName("simple");
		 em.persist(simple);
		 em.getTransaction().commit();
		 assertNotNull(simple.getId());
		 SimpleEntity s = em.find(SimpleEntity.class, simple.getId());
		 assertEquals("simple", s.getName());
	}
}
