package com.depp.stone.spring;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class DependencyInjectionCtxTest extends
		AbstractDependencyInjectionSpringContextTests {


	@Override
	protected String[] getConfigLocations() {
		//autowire  
		return new String[]{"classpath:applicationContext.xml"};
	}
	public void testDemo() throws Exception {
	}
}
