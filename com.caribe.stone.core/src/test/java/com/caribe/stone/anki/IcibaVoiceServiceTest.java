package com.caribe.stone.anki;

import static org.junit.Assert.*;

import org.junit.Test;

public class IcibaVoiceServiceTest {

	@Test
	public void test() {
		IcibaVoiceService service = new IcibaVoiceService();
		String industry = service.getIndustry("profession");
		assertNotNull(industry);
		System.out.println(industry.length());
	}

	@Test
	public void test1() {

		String str = "abc";
		StringBuffer buf = new StringBuffer(str);
		buf.append("ZZZ");
		str = buf.toString();
		assertEquals("abcZZZ", str);
	}

}
