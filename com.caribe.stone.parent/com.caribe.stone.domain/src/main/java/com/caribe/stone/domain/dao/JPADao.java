package com.caribe.stone.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.caribe.stone.magic.SorcererStone;

/**
 * @author bsnpbag
 * 
 * @param <E>
 * @param <PK>
 */
@Repository
public class JPADao<E, PK extends Serializable> implements BaseDao<E, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<E> entityClass;
	protected Class<PK> primeKey;

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	@SuppressWarnings("unchecked")
	public JPADao() {
		this.entityClass = SorcererStone.getSuperClassGenricType(getClass(), 0);
		this.primeKey = SorcererStone.getSuperClassGenricType(getClass(), 1);
	}

	/* (non-Javadoc)
	 * @see com.caribe.stone.domain.dao.BaseDao#save(E)
	 */
	@Override
	public void save(E entity) {
		em.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.caribe.stone.domain.dao.BaseDao#update(E)
	 */
	@Override
	public E update(E entity) {
		em.merge(entity);
		return entity;

	}

	/* (non-Javadoc)
	 * @see com.caribe.stone.domain.dao.BaseDao#delete(E)
	 */
	@Override
	public void delete(E entity) {
		em.remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.caribe.stone.domain.dao.BaseDao#find(PK)
	 */
	@Override
	public E find(PK pk) {
		E entity = null;
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.caribe.stone.domain.dao.BaseDao#findAll()
	 */
	@Override
	public List<E> findAll() {
		Query query = em.createQuery("select t from "+entityClass.getName()+" t");
		return query.getResultList();
	}
}
