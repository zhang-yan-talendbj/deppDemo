package com.caribe.stone.effective.singleton;

import static org.junit.Assert.*;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.accessibility.Accessible;

import org.junit.Test;

public class SingletonElvisTest {

	@Test
	public void finalField() {
		assertEquals(Elvis.INSTANCE, Elvis.INSTANCE);
	}

	@Test
	public void staticFactory() throws Exception {
		assertEquals(ElvisStaticFactory.getInstance(), ElvisStaticFactory.getInstance());
	}

	@Test(expected = InvocationTargetException.class)
	public void testName() throws Exception {
		Class c = Elvis.class;
		Constructor constructors = c.getDeclaredConstructor();
		constructors.setAccessible(true);
		Object e1 = constructors.newInstance();
	}

}
