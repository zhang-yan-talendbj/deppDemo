package com.caribe.stone.j2se.clazz;

import static org.junit.Assert.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class ClassTest {

	@Test
	public void genericSuperclassType() throws Exception {

		Bar bar = new Bar();
		Class<? extends Bar> barClass = bar.getClass();
		assertSame(Foo.class, barClass.getSuperclass());

		Type type = barClass.getGenericSuperclass();
		assertTrue(type instanceof ParameterizedType);

		ParameterizedType genericSuperclass = (ParameterizedType) type;
		Type[] args = genericSuperclass.getActualTypeArguments();

		assertEquals(Foo.class, genericSuperclass.getRawType());
		assertSame(String.class, args[0]);
		assertSame(Long.class, args[1]);
	}

	@Test
	public void test() throws Exception {
		assertTrue(Number.class.isAssignableFrom(Number.class));
		assertTrue(Number.class.isAssignableFrom(Integer.class));
		assertTrue(int.class.isAssignableFrom(int.class));
		assertFalse(Integer.class.isAssignableFrom(int.class));
	}

	@Test
	public void testGetClass() throws Exception {
		Bar bar = new Bar();
		assertEquals("class com.caribe.stone.j2se.clazz.Bar", bar.getGetClassString());
		assertEquals("class com.caribe.stone.j2se.clazz.Foo", bar.getClassName());
	}
}

class Foo<T, P> {
	private String getClassString;

	public String getGetClassString() {
		return getClassString;
	}

	public void setGetClassString(String getClassString) {
		this.getClassString = getClassString;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private String className;

	Foo() {
		className = Foo.class.toString();
		getClassString = getClass().toString();
	}
}

class Bar extends Foo<String, Long> {
}
