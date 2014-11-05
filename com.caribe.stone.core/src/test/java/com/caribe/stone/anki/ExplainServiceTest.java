package com.caribe.stone.anki;

import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExplainServiceTest {

	@Test
	public void test1() {

        ExplainService ser = new ExplainService();
        Elements eles = ser.getDirectionElement("predefine");
        System.out.println(eles.html());
    }

}
