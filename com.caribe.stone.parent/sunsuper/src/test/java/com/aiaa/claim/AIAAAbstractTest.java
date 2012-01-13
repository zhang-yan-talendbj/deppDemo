package com.aiaa.claim;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class AIAAAbstractTest {
	protected static WebDriver wd;

	@AfterClass
	public static void destoryDriver() {
		// wd.quit();
	}

	@Before
	public void setUp() {
		if (wd == null) {
			DriverType type = getTyep();
			switch (type) {
			case ie:
				wd = new InternetExplorerDriver();
				break;
			case firefox:
				wd = new FirefoxDriver();
				break;
			case html:
				wd = new HtmlUnitDriver(true);
			case chrome:
				System.setProperty("webdriver.chrome.driver","c:/Documents and Settings/bsnpbag/Local Settings/Application Data/Google/Chrome/Application/chrome.exe");
				wd = new ChromeDriver();
			default:
				wd = new HtmlUnitDriver(true);
				break;
			}
			wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}

	protected DriverType getTyep() {
		return DriverType.ie;
	}

	protected void setValueByName(String value, String name) {
		wd.findElement(By.name(name)).sendKeys(value);
	}

	protected void setValueById(String value, String id) {
		wd.findElement(By.id(id)).sendKeys(value);
	}

	protected void clickByName(String name) {
		wd.findElement(By.name(name)).click();
	}

	protected void clickById(String id) {
		wd.findElement(By.id(id)).click();
	}
}

enum DriverType {
	ie, firefox, html, chrome
}
