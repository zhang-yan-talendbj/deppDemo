package com.depp.stone.spring;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class TestUserService extends TestCase {
	public ApplicationContext ctx = null;
	public static String[] config_files = new String[] { "src/test/resource/applicationContext.xml" };

	@Override
	protected void setUp() throws Exception {
		ctx = new FileSystemXmlApplicationContext(config_files);
		super.setUp();
	}
	
	public void testDemo() throws Exception {
		System.out.println(99);
		
	}

}