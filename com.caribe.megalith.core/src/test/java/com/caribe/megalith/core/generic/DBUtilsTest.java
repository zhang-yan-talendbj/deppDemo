package com.caribe.megalith.core.generic;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBUtilsTest {

	@Test
	public void testGetH2Connection() throws Exception {
		assertNotNull(DBUtils.getH2Connection());
	}

}
