package com.caribe.stone.wd;

import static com.caribe.stone.wd.SeleniumUtils.*;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class AbstractTest {
	private static final String DEFAULT_VALUE = "default value";
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
				System.setProperty("webdriver.firefox.bin","d:/Program Files/test/Mozilla Firefox/firefox.exe");
				System.setProperty("webdriver.firefox.profile", "selenium");
				wd = new FirefoxDriver();
				break;
			case html:
				wd = new HtmlUnitDriver(true);
				break;
			case chrome:
				System.setProperty(
						"webdriver.chrome.driver",
						"F:/depp/Documents and Settings/Administrator/Local Settings/Application Data/Google/Chrome/Application/chrome.exe");
				wd = new ChromeDriver();
				break;
			default:
				wd = new HtmlUnitDriver(true);
				break;
			}
			wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}

	protected DriverType getTyep() {
		return DriverType.html;
	}

	protected void setValueByName(String value, String name) {
		wd.findElement(By.name(name)).clear();
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

	protected void selectOptionByName(String selectedOption, String name) {
		WebElement selectElement = wd.findElement(By.name(name));
		select(selectElement, selectedOption);
	}

	protected void setDefaultValueByName(String name) {
		setValueByName(DEFAULT_VALUE, name);
	}
}

enum DriverType {
	ie, firefox, html, chrome
}
