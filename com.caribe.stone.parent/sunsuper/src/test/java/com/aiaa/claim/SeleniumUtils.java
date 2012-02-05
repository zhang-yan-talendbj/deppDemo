package com.aiaa.claim;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {

	public static void select(WebElement select, String selectedOption) {
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals(selectedOption)) {
				option.click();
				break;
			}
		}
	}
}
