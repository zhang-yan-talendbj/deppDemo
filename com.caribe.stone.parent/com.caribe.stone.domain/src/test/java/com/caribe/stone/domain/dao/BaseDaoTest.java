package com.caribe.stone.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.junit.Test;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.caribe.stone.domain.entities.SimpleEntity;

@ContextConfiguration
public class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	// @Resource
	// private BaseDao<SimpleEntity, Long> baseDao;
	@PersistenceContext(unitName = "simpleUnit")
	private EntityManager em;
	@PersistenceUnit(unitName = "simpleUnit")
	private EntityManagerFactory emf;

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// public BaseDao<SimpleEntity, Long> getBaseDao() {
	// return baseDao;
	// }
	//
	// public void setBaseDao(BaseDao<SimpleEntity, Long> baseDao) {
	// this.baseDao = baseDao;
	// }

	@Test
	@Transactional
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
		em.flush();
		em.getTransaction().commit();
		System.out.println(entity.getId());
	}

	private SimpleEntity getEntity() {
		SimpleEntity entity = new SimpleEntity();
		entity.setName("bruce");
		return entity;
	}

	@Test
	public void testName() throws Exception {
		// EntityManager em = baseDao.getEm();

		// System.out.println(em);
		// SimpleEntity entity = getEntity();
		// em.persist(entity);
		// System.out.println(entity.getId());
		// System.out.println(emf);
		// System.out.println(applicationContext.getBean("entityManagerFactory")
		// == emf);
		;
		// EntityManager em = emf.createEntityManager();
		// em.getTransaction().begin();
		SimpleEntity entity = getEntity();
		em.persist(entity);
		em.flush();
		// em.getTransaction().commit();
		System.out.println(entity.getId());
	}

	@Test
	@Transactional
	public void testTranscation() throws Exception {
		JpaTransactionManager bean = (JpaTransactionManager) applicationContext
				.getBean("transactionManager");
	}

}
