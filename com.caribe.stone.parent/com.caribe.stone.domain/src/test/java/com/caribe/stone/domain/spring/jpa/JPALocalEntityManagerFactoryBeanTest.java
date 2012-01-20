package com.caribe.stone.domain.spring.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.caribe.stone.domain.entities.Message;

@ContextConfiguration
public class JPALocalEntityManagerFactoryBeanTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testLocalEntityManagerFactoryBean() throws Exception {
		System.out.println(applicationContext);
		EntityManagerFactory emf = (EntityManagerFactory) applicationContext.getBean("myEmf");
		EntityManager em = emf.createEntityManager();
		Message m = new Message();
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		assertNotNull(m.getId());
	}
}
