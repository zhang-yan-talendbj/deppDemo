package com.caribe.stone.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import com.caribe.stone.junit.SeleniumScreenShotRunner;
import com.caribe.stone.wd.AbstractTest;
import com.caribe.stone.wd.DriverType;

@RunWith(SeleniumScreenShotRunner.class)
public class Demo extends AbstractTest {
	@Test
	public void testDemo() throws Exception {

		String baseUrl = "http://localhost:8080";
		wd.get(baseUrl + "/wd/staff.jsp");
		wd.findElement(By.name("staff_id")).clear();
		wd.findElement(By.name("staff_id")).sendKeys("bsnpbag");
		wd.findElement(By.name("actionType")).click();
		assertEquals("Good Morning!", wd.findElement(By.id("msg")).getText());
		
		wd.get(baseUrl + "/wd/staff.jsp");
		wd.findElement(By.name("staff_id")).clear();
		wd.findElement(By.name("staff_id")).sendKeys("bsnpbag");
		wd.findElement(By.xpath("(//input[@name='actionType'])[2]")).click();
		assertEquals("Byebye1!", wd.findElement(By.id("msg")).getText());
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.ie;
	}
}
