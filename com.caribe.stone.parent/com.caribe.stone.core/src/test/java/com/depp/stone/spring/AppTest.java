package com.depp.stone.spring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;

import foo.domain.entity.one2Many.Box;



@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AppTest extends AbstractJUnit4SpringContextTests {
	@Test
	public void testName() throws Exception {
		ClassPathResource cp = new ClassPathResource("application.test.properties");
		FileReader x = new FileReader(cp.getFile());
		BufferedReader br = new BufferedReader(x);
		while(br.readLine()!=null){
			
			
			
			
			System.out.println(br.readLine());
		}
		
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
