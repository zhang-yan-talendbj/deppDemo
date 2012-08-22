package com.caribe.stone.domain.dao;

import java.util.List;


public interface BaseDao<E, PK> {

	public abstract void save(E entity);

	public abstract E update(E entity);

	public abstract void delete(E entity);

	public abstract E find(PK pk);

	public abstract List<E> findAll();

}