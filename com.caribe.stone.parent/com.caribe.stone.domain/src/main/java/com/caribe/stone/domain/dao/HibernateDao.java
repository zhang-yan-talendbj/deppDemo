package com.caribe.stone.domain.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.caribe.stone.magic.SorcererStone;

public class HibernateDao<E, PK extends Serializable> implements BaseDao<E, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;
	protected Class<E> entityClass;
	protected Class<PK> pkClass;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public HibernateDao() {
		this.entityClass = SorcererStone.getSuperClassGenricType(getClass(), 0);
	}

	public HibernateDao(final SessionFactory sessionFactory, final Class<E> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	@Override
	public void save(E entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("Save entity: {}" + entity);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public E update(E entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
		logger.debug("Update entity: {}" + entity);
		return entity;
	}

	@Override
	public void delete(E entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("Delete entity: {}" + entity);
	}

	@Override
	public E find(PK id) {
		Assert.notNull(id, "Id不能为空");
		return (E) getSession().load(entityClass, id);
	}

	
	@Override
	public List<E> findAll() {
		return find();
	}

	private List<E> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	private Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

}
