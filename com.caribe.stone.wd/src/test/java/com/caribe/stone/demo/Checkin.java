package com.caribe.stone.demo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Checkin {
	private WebDriver wd;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		 wd= new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCheckin() throws Exception {
		baseUrl = "http://localhost:8080";
		wd.findElement(By.linkText("Download Selenium")).click();
		wd.get(baseUrl + "/wd/staff.jsp");
		wd.findElement(By.name("staff_id")).clear();
		wd.findElement(By.name("staff_id")).sendKeys("bsnpbag");
		wd.findElement(By.name("actionType")).click();
		assertEquals("Good Morning!", wd.findElement(By.id("msg")));
		wd.findElement(By.xpath("(//input[@name='actionType'])[2]")).click();
		assertEquals("Byebye!", wd.findElement(By.id("msg")));
	}

	@After
	public void tearDown() throws Exception {
		wd.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
