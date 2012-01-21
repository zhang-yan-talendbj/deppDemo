package com.depp.stone.spring.asser;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.util.Assert;

public class AssertTest {

	@Test(expected=IllegalArgumentException.class)
	public void assertTest() throws Exception {
		Assert.notNull(null, "entity不能为空");
		
	}
}
