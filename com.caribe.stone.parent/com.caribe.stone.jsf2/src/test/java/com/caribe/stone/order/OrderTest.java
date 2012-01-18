package com.caribe.stone.order;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class OrderTest {

	private WebDriver dw;

	@Before
	public void setUp() {
//		dw = new FirefoxDriver();
		dw = new HtmlUnitDriver();
	}

	@Test
	public void testTitle() throws Exception {
		// dw.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		dw.get("http://localhost:8099/com.caribe.stone.jsf2/faces/orderDishes/index.xhtml");
		assertEquals("Order Dishes", dw.getTitle());
	}

	@After
	public void tearDown() {
		dw.quit();
	}
}
