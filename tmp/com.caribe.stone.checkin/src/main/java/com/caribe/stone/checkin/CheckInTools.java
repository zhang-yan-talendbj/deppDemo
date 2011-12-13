package com.caribe.stone.checkin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Hello world!
 * 
 */
public class CheckInTools {

	public static void main(String[] args) throws InterruptedException {
		String user = System.getProperty("user.name");
		String url = "http://10.69.4.38:9081/TCS_New/";
		WebDriver driver = new InternetExplorerDriver();
		driver.get(url);
		checkIn(user, driver);
		getCheckInTime(driver);
	}

	public static void getCheckInTime(WebDriver driver) {
		WebElement home = driver.findElement(By.linkText("here"));

		home.click();
		WebElement inquiry = driver.findElement(By.xpath("//input[@value='Inquiry']"));
		inquiry.click();
		inquiry = driver.findElement(By.xpath("//input[@value='Inquiry']"));
		inquiry.click();

		WebElement checkInDate = driver.findElement(By.xpath("//form[@name='Form_Main']/table/tbody/tr[@class='middlerow']/td[3]"));
		WebElement checkInTime = driver.findElement(By.xpath("//form[@name='Form_Main']/table/tbody/tr[@class='middlerow']/td[4]"));
		System.out.println("Check in time: " + checkInDate.getText() + ":" + checkInTime.getText());
	}

	public static void checkIn(String user, WebDriver driver) throws InterruptedException {
		WebElement staffId = driver.findElement(By.xpath("//table[@id='contentTable']/tbody/tr/td/input[1]"));
		WebElement checkIn = driver.findElement(By.xpath("//input[@value='Check In']"));
		staffId.sendKeys(user);
		checkIn.click();
		WebElement msg = driver.findElement(By.xpath("//table[@id='logoTable']/tbody/tr/td"));
		System.out.println(msg.getText());
	}
}
