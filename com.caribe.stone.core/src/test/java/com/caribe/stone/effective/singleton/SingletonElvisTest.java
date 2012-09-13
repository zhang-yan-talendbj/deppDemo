package com.caribe.stone.effective.singleton;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	public void breakSingleton() throws Exception {
		Class<Elvis> c = Elvis.class;
		Constructor<Elvis> constructors = c.getDeclaredConstructor();
		constructors.setAccessible(true);
		Object e1 = constructors.newInstance();
	}

}
