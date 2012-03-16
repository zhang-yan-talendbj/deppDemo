package com.aiaa.claim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewPolicyRuleHistoryTest extends AIAAAbstractTest {
	@Test
	public void testViewPolicyRuleHistory() throws Exception {
		clickById("manageFundRepository");
		
		SeleniumUtils.select(driver.findElement(By.name("selectPolicySource")), "Compass");
		clickByName("search");
		
		driver.findElement(By.linkText("view")).click();
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.firefox;
	}

	@Before
	public void setUp() {
		super.setUp();
		driver.get("http://cibwkdp000105:9084/ClaimsAdminWeb/app");
	}

	@After
	public void tearDown() {

	}
}
