package com.caribe.stone.domain.dao;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caribe.stone.magic.SorcererStone;

/**
 * @author bsnpbag
 * 
 * @param <E>
 * @param <P>
 */
public class BaseDao<E, P extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<E> entityClass;
	protected Class<P> primeKey;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.entityClass = SorcererStone.getSuperClassGenricType(getClass(), 0);
		this.primeKey = SorcererStone.getSuperClassGenricType(getClass(), 1);
	}

	public P save(E entity) {
		P p = null;
		return p;
	}

	public E update(E entity) {
		return entity;

	}

	public E delete(E entity) {
		return entity;

	}

	public E find(P pk) {
		E entity = null;
		return entity;

	}

	public Collection<E> findAll() {
		return null;
	}
}
