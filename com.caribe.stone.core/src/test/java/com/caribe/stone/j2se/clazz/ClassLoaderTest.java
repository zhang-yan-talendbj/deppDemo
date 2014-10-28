package com.caribe.stone.j2se.clazz;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassLoaderTest {

	@Test
	public void classLoader() throws Exception {
		Class c;
		ClassLoader cl;
		cl = ClassLoader.getSystemClassLoader();
		System.out.println(cl);
		while (cl != null) {
			cl = cl.getParent();
			System.out.println(cl);
		}
		try {
			c = Class.forName("java.lang.Object");
			cl = c.getClassLoader();
			System.out.println("java.lang.Object's loader is  " + cl);
			c = Class.forName("com.caribe.stone.j2se.clazz.ClassLoaderTest");
			cl = c.getClassLoader();
			System.out.println("com.caribe.stone.j2se.clazz.ClassLoaderTest's loader is  " + cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void staticMethodExecViaClassForName() throws Exception {
		Class<?> c = Class.forName("com.caribe.stone.j2se.clazz.TestClass");
		//static method executes.
		}
	@Test
	public void staticMethodExecViaClassLoader() throws Exception {
		this.getClass().getClassLoader().loadClass("com.caribe.stone.j2se.clazz.TestClass");
		//static method doesn't execute.
	}
	
	@Test
	public void defineClassLoad() throws Exception {
		ClassLoader cl = this.getClass().getClassLoader();
		ClassLoader cl2 = ClassLoader.getSystemClassLoader();
		assertSame(cl, cl2);
	}
}

class TestClass {
	static {
		System.out.println("TestClass init...");
	}
}
