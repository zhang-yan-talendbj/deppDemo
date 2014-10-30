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
	public void testWordNet() {
		ExplainService service = new ExplainService();
		String collins = service.getCollinsExplain("guestroom");
		assertNotNull(collins);
		System.out.println(collins);
	}
	
	@Test
	public void testName() throws Exception {
		String str="start off";
		System.out.println(str.indexOf(" "));
	}

}
