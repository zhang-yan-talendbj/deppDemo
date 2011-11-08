package com.depp.stone.spring.annotation;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class HelloTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testname() throws Exception {
		System.out.println(123);
	}
}
