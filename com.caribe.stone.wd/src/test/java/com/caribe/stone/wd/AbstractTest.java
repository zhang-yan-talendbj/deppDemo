package com.caribe.stone.wd;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.mortbay.jetty.Server;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class AbstractTest {
	protected static WebDriver wd;
	private Server server = JettyUtils.buildNormalServer(8080, "/wd");

	@AfterClass
	public static void destoryDriver() {
		 wd.quit();
	}

	@Before
	public void setUp() throws Exception {
		server.start();
		if (wd == null) {
			DriverType type = getTyep();
			switch (type) {
			case ie:
				wd = new InternetExplorerDriver();
				break;
			case firefox:
				System.setProperty("webdriver.firefox.bin",
						"d:/Program Files/test/Mozilla Firefox9/firefox.exe");
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
}
