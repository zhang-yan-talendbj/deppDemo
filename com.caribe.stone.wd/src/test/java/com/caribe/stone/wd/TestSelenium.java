package com.caribe.stone.wd;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSelenium extends AbstractTest {
	@Test
	public void testName() throws Exception {
		wd.get("http://www.baidu.com");
	}
}
