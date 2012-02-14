package com.caribe.stone.wd;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSelenium extends AbstractTest {
	@Test
	public void testName() throws Exception {
		wd.get("http://192.168.1.104:8082");
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.chrome;
	}
}
