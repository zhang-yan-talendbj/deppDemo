package com.aiaa.claim;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Study {

	public static void main(String[] args)  {
		System.setProperty("webdriver.firefox.bin",
				"d:/Program Files/test/Mozilla Firefox9/firefox.exe");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.baicizhan.com/login");
//		driver.quit();baseUrl = "http://www.baicizhan.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("bruce-y.zhang@aia.com");
		driver.findElement(By.name("raw_pwd")).clear();
		driver.findElement(By.name("raw_pwd")).sendKeys("19850930");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.linkText("开始背单词吧")).click();
//		driver.findElement(By.cssSelector("#8264 > h3")).click();
//		driver.findElement(By.cssSelector("#17528 > img")).click();
//		driver.findElement(By.cssSelector("h3")).click();
//		driver.findElement(By.cssSelector("#19275 > img")).click();
	}


}
