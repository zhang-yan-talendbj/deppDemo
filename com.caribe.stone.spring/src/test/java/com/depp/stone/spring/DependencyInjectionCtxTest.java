<<<<<<< HEAD
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
=======
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
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
