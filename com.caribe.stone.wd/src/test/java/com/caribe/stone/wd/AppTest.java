package com.caribe.stone.wd;

import static org.junit.Assert.*;

import org.mortbay.jetty.Server;
import org.openqa.selenium.By;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends AbstractTest {

	@org.junit.Test
	public void testName() throws Exception {
		Server server = JettyUtils.buildNormalServer(8080, "/wd");

		server.start();

		wd.get("http://localhost:8080/wd/");

		wd.findElement(By.linkText("Sign up")).click();

		setValueByName("thinkdeeply", "username");
		setValueByName("pw", "password");
		wd.findElement(By.tagName("form")).submit();

		wd.get("http://localhost:8080/wd/");
		wd.findElement(By.linkText("Log in")).click();

		setValueByName("thinkdeeply", "username");
		setValueByName("pw", "password");
		wd.findElement(By.tagName("form")).submit();

		assertEquals("success", wd.findElement(By.id("msg")).getText());
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.firefox;
	}
}
