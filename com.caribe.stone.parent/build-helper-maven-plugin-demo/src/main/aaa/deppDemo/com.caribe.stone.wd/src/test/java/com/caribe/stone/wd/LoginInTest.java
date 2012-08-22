package com.caribe.stone.wd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Server;
import org.openqa.selenium.By;

import com.caribe.stone.junit.SeleniumScreenShotRunner;

//@RunWith(SeleniumScreenShotRunner.class)
public class LoginInTest extends AbstractTest {

	@Test
	public void testName() throws Exception {
//		Server server = JettyUtils.buildNormalServer(8080, "/wd");
//
//		server.start();

		wd.get("http://localhost:8080/wd/");

		wd.findElement(By.linkText("Sign up")).click();

		wd.findElement(By.name("username")).clear();
		wd.findElement(By.name("username")).sendKeys("thinkdeeply");
		wd.findElement(By.name("password")).clear();
		wd.findElement(By.name("password")).sendKeys("pw");
		wd.findElement(By.tagName("form")).submit();

		wd.get("http://localhost:8080/wd/");
		wd.findElement(By.linkText("Log in")).click();

		wd.findElement(By.name("username")).clear();
		wd.findElement(By.name("username")).sendKeys("thinkdeeply");
		wd.findElement(By.name("password")).clear();
		wd.findElement(By.name("password")).sendKeys("pw");
		wd.findElement(By.tagName("form")).submit();

		assertEquals("success", wd.findElement(By.id("msg")).getText());
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.firefox;
	}
}
