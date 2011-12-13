package com.caribe.stone.checkin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Hello world!
 * 
 */
public class CheckInTools {

	public static void main(String[] args) throws InterruptedException {
		String user = System.getProperty("user.name");
		String url = "http://10.69.4.38:9081/TCS_New";
		WebDriver driver = null;
//		driver=new InternetExplorerDriver();
//		driver=new HtmlUnitDriver();
		driver=new FirefoxDriver();
		driver.get(url);
		System.out.println(driver.getPageSource());
		checkIn(user, driver);
//		getCheckInTime(driver);
//		driver.quit();
	}

	public static void getCheckInTime(WebDriver driver) {
		driver.navigate().back();
		WebElement inquiry = driver.findElement(By.xpath("//input[@value='Inquiry']"));
		inquiry.click();
		inquiry = driver.findElement(By.xpath("//input[@value='Inquiry']"));
		inquiry.click();

		WebElement checkInDate = driver.findElement(By.xpath("//table/tbody/tr/td[4]"));
		WebElement checkInTime = driver.findElement(By.xpath("//table/tbody/tr/td[2]"));
		System.out.println("Check in time: " + checkInDate.getText() + ":" + checkInTime.getText());
	}

	public static void checkIn(String user, WebDriver driver) throws InterruptedException {
		System.out.println(driver.getPageSource());
		WebElement staffId = driver.findElement(By.tagName("BODY"));
		System.out.println(staffId.getAttribute("type"));
		if(true){
			return ;
		}
		WebElement checkIn = driver.findElement(By.xpath("//input[@value='Check In']"));
		staffId.sendKeys("123123123123123123123123123123123123123123");
		Thread.sleep(500);
		System.out.println(staffId.getText());
		checkIn.click();
		System.out.println(driver.getCurrentUrl());
//		WebElement msg = driver.findElement(By.id("logoTable"));
//		System.out.println(msg.getText());
	}
}
