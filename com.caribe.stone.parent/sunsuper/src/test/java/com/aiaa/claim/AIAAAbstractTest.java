package com.aiaa.claim;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class AIAAAbstractTest {
	private static final String DEFAULT_VALUE = "default value";
	protected static WebDriver driver;

	@AfterClass
	public static void destoryDriver() {
//		driver.quit();
	}

	@Before
	public void setUp() {
		if (driver == null) {
			DriverType type = getType();
			switch (type) {
			case ie:
				driver = new InternetExplorerDriver();
				break;
			case firefox:
				System.setProperty("webdriver.firefox.bin",
						"d:/Program Files/test/Mozilla Firefox9/firefox.exe");

				ProfilesIni allProfiles = new ProfilesIni();
				FirefoxProfile profile = allProfiles.getProfile("selenium");
				if (profile != null) {
					driver = new FirefoxDriver(profile);
				} else {
					driver = new FirefoxDriver();
				}
				break;
			case html:
				driver = new HtmlUnitDriver(true);
				break;
			case chrome:
				System.setProperty("webdriver.chrome.driver",
						"c:/Documents and Settings/bsnpbag/Local Settings/Application Data/Google/Chrome/Application/chrome.exe");
				driver = new ChromeDriver();
				break;
			default:
				driver = new HtmlUnitDriver(true);
				break;
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	protected DriverType getType() {
		return DriverType.ie;
	}

}

enum DriverType {
	ie, firefox, html, chrome
}
