package com.caribe.stone.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class GenericTypeTest {

	@Test
	public void testName() throws Exception {
		MeatRepo dao = new MeatRepo();
		ParameterizedType genericSuperclass = (ParameterizedType) dao.getClass().getGenericSuperclass();
		Type[] x = dao.getClass().getGenericInterfaces();
		// System.out.println(genericSuperclass.getActualTypeArguments()[0]);
		System.out.println(Meat.class.getGenericSuperclass());
	}
}

class BaseRepository<T> implements BaseInterface<T> {
	private Class<T> entityClass;

	public BaseRepository() {
		System.out.println(getClass());
		System.out.println(getClass().getGenericSuperclass());
		System.out.println(getClass().getGenericSuperclass().getClass());
		getClass().getGenericSuperclass();
	}

	public T getInstance() throws InstantiationException, IllegalAccessException {
		return entityClass.newInstance();
	}

	@Override
	public String say() {
		return "say you say me~";
	}
}

interface BaseInterface<T> {
	 String say();
}

class MeatRepo extends BaseRepository<Meat> {
	public MeatRepo() {
		System.out.println(getClass().getGenericSuperclass());
	}

}

class Meat {

}