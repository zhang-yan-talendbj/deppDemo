package com.caribe.stone.db.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBUtilsTest {

	@Test
	
	public void testGetDB2Connection() throws Exception {
		assertNotNull(DBUtils.getDB2Connection());
	}

	@Test
	public void testGetH2Connection() {
	// TODO testH2connection
	}

}
