package com.depp.stone.spring;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("deprecation")
public class DependencyInjectionCtxTest extends
		AbstractDependencyInjectionSpringContextTests {
	private User user;
	private UserService serive;
	public UserService getSerive() {
		return serive;
	}

	public void setSerive(UserService serive) {
		this.serive = serive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	protected String[] getConfigLocations() {
		//autowire  
		return new String[]{"classpath:applicationContext.xml"};
	}
	public void testDemo() throws Exception {
		assertEquals(getUser().getUserName(), "bruce");
	}
}
