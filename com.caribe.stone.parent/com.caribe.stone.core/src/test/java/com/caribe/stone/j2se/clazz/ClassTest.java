package com.caribe.stone.j2se.clazz;

import static org.junit.Assert.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class ClassTest {

	@Test
	public void genericSuperclassType() throws Exception {
		Class<?> superClass = Bar.class.getSuperclass();
		assertSame(Foo.class, superClass);// super class

		Type genericSuperclass = Bar.class.getGenericSuperclass();// generic
																	// class
		assertTrue(genericSuperclass instanceof ParameterizedType);

		Type[] args = ((ParameterizedType) genericSuperclass).getActualTypeArguments();

		assertSame(String.class, args[0]);
		assertSame(Long.class, args[1]);
	}
}

class Foo<T, P> {
}

class Bar extends Foo<String, Long> {
}
