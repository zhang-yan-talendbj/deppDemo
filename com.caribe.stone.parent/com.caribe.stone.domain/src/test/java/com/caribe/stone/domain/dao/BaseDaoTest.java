package com.caribe.stone.domain.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.caribe.stone.domain.entities.SimpleEntity;

@ContextConfiguration
public class BaseDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private BaseDao<SimpleEntity, Long> baseDao;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public BaseDao<SimpleEntity, Long> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<SimpleEntity, Long> baseDao) {
		this.baseDao = baseDao;
	}

	@Test
	// @Transactional
	public void testBaseDao() {
		// SimpleEntity entity = new SimpleEntity();
		// entity.setName("bruce");
		// // baseDao.save(entity);
		// EntityManager em = baseDao.getEm();
		// em.getTransaction().begin();
		// em.persist(entity);
		// System.out.println(entity.getId());
		// em.getTransaction().commit();

		EntityManagerFactory emf = (EntityManagerFactory) applicationContext
				.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		System.out.println(em);
		SimpleEntity entity = getEntity();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		System.out.println(entity.getId());
	}

	private SimpleEntity getEntity() {
		SimpleEntity entity = new SimpleEntity();
		entity.setName("bruce");
		return entity;
	}

	@Test
	@Transactional()
	public void testName() throws Exception {
		// EntityManager em = baseDao.getEm();

		System.out.println(em);
		SimpleEntity entity = getEntity();
		em.persist(entity);
		System.out.println(entity.getId());

	}

}
