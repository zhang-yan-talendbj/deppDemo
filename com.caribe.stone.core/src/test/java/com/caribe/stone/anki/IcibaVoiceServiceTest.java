package com.caribe.stone.anki;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;

public class IcibaVoiceServiceTest {

	@Test
	public void test() {
		IcibaVoiceService service = new IcibaVoiceService();
		String industry = service.getIndustry("affect");
		assertNotNull(industry);
		System.out.println(industry.length());
        System.out.println(industry);
    }

	@Test
	public void test1() {

		String str = "abc";
		StringBuffer buf = new StringBuffer(str);
		buf.append("ZZZ");
		str = buf.toString();
		assertEquals("abcZZZ", str);
	}

    @Test
    public void downloadPhraseMp3(){
        IcibaVoiceService service = new IcibaVoiceService();
        String url = service.downloadPhraseMp3("turn off");


    }

}
