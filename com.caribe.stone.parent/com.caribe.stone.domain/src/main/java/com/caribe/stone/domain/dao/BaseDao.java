package com.caribe.stone.domain.dao;

import java.io.Serializable;
import java.util.Collection;
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
public class BaseDao<E, PK extends Serializable> {

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
	public BaseDao() {
		this.entityClass = SorcererStone.getSuperClassGenricType(getClass(), 0);
		this.primeKey = SorcererStone.getSuperClassGenricType(getClass(), 1);
	}

	public void save(E entity) {
		em.persist(entity);
	}

	public E update(E entity) {
		em.merge(entity);
		return entity;

	}

	public void delete(E entity) {
		em.remove(entity);
	}

	public E find(PK pk) {
		E entity = null;
		return entity;
	}

	public Collection<E> findAll() {
		Query query = em.createQuery("select t from "+entityClass.getName()+" t");
		return query.getResultList();
	}
}
