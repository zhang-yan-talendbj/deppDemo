package com.depp.stone.spring.bean.instantiation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration
public class InstantiatingBeanTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private InitBean initBean;

	public InitBean getInitBean() {
		return initBean;
	}

	public void setInitBean(InitBean initBean) {
		this.initBean = initBean;
	}

	@Test
	public void testBean() throws Exception {
		assertEquals("have been initialed", getInitBean().getInitMsg());
		// TODO Collection merging
		// TODO Custom scope

	}

}
