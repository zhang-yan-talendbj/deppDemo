package com.caribe.stone.checkin;

import java.awt.Label;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class CheckInTools {

	public static void main(String[] args) throws InterruptedException {
		String user = System.getProperty("user.name");
		Random r = new Random();
		int nextInt = r.nextInt(60* 10);
		nextInt=0;
		System.out.println("Random Number is "+ nextInt);
		while(nextInt>0){
			System.out.println("Please wait "+nextInt+" seconds.");
			Thread.sleep(1000L);
			nextInt--;
		}
		
		String url = "http://10.69.4.38:9081/TCS_New";
		WebDriver driver = null;
		driver = new HtmlUnitDriver(true);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

		driver.get(url);
		checkIn(user, driver);
		driver.quit();
	}

	public static void checkIn(String user, WebDriver driver) throws InterruptedException {
		WebElement staffId = driver.findElement(By.name("staff_id"));
		WebElement checkIn = driver.findElement(By.xpath("//input[@value='Check In']"));
		staffId.sendKeys(user);
		checkIn.click();
		WebElement msg = driver.findElement(By.id("logoTable"));
		showMsg(msg.getText());
	}

	public static void showMsg(String msg) {
		JFrame j = new JFrame();
		Label comp = new Label();
		comp.setText(msg);
		j.add(comp);
		j.setSize(600, 100);
		j.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		j.dispose();
	}
}
