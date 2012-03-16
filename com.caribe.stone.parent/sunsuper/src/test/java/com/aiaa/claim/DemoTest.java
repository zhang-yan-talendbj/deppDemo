package com.aiaa.claim;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoTest extends AIAAAbstractTest {

	@Test
	public void testName() throws Exception {
		driver.get("c:/Documents and Settings/bsnpbag/My Documents/Downloads/app(2).htm");
		List<WebElement> trs = driver.findElements(By.xpath("id('payments')/tbody/tr[@class]"));
		System.out.println(trs.size());
		for (WebElement tr : trs) {
			WebElement fromDate = tr.findElement(By.className("fromColumnValue"));
			WebElement toDate = tr.findElement(By.className("toColumnValue"));
			System.out.println(fromDate.getText());
			System.out.println(toDate.getText());
		}
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.html;
	}

}
