package com.depp.stone.spring;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import foo.domain.entity.one2Many.Box;



@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AppTest extends AbstractJUnit4SpringContextTests {
	@Test
	public void testName() throws Exception {
		EntityManagerFactory bean = (EntityManagerFactory) applicationContext
				.getBean("entityManagerFactory");
		System.out.println(bean);
		EntityManager em = bean.createEntityManager();
		List l = em.createQuery("select a from Address a").getResultList();
		System.out.println(l);
		Box b=new Box();
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
	}
}
