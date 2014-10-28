package com.caribe.stone.anki;

import static org.junit.Assert.*;

import org.junit.Test;

public class YouDaoVoiceServiceTest {

	@Test
	public void test() {
		ExplainService service = new ExplainService();
		String collins = service.getCollinsExplain("share");
		assertNotNull(collins);
	}
	
	@Test
	public void testName() throws Exception {
		String str="start off";
		System.out.println(str.indexOf(" "));
	}

}
