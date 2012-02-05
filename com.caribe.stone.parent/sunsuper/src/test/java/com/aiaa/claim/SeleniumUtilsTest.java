package com.aiaa.claim;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.IMocksControl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SeleniumUtilsTest {

	@Test
	public void testSelect() {
		IMocksControl cc = createControl();
		String selectedOption = "selected";
		List<WebElement> list = new ArrayList<WebElement>();
		WebElement select = cc.createMock(WebElement.class);

		WebElement option1 = cc.createMock(WebElement.class);
		expect(option1.getText()).andReturn("value1");
		WebElement option2 = cc.createMock(WebElement.class);
		expect(option2.getText()).andReturn("selected");
		WebElement option3 = cc.createMock(WebElement.class);
		list.add(option1);
		list.add(option2);
		list.add(option3);
		option2.click();
		expect(select.findElements(By.tagName("option"))).andReturn(list);
		
		cc.replay();
		SeleniumUtils.select(select, selectedOption);
		cc.verify();
	}

}
