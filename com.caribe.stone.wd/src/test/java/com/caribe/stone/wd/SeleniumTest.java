package com.caribe.stone.wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	public static void main(String[] args) {

		System.setProperty("webdriver.firefox.bin",
				"D:/Program Files/test/Mozilla Firefox/firefox.exe");
		WebDriver wd = new FirefoxDriver();
		wd.get("http://www.baidu.com");
	}
}
