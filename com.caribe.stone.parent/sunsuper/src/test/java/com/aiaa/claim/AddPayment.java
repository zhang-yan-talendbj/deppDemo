package com.aiaa.claim;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddPayment {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:9083/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddPayment() throws Exception {
		driver.get(baseUrl + "/ClaimsAdminWeb/app");
		driver.findElement(By.name("inputClaimNumber")).clear();
		driver.findElement(By.name("inputClaimNumber")).sendKeys("21402");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("21402")).click();
		driver.findElement(By.id("Payee DetailsTabLink")).click();
		driver.findElement(By.id("recordPayeeDetails")).click();
		new Select(driver.findElement(By.name("selectPayee"))).selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(driver.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		driver.findElement(By.name("changePaymentDetails")).click();
		driver.findElement(By.name("inputPayeeName")).clear();
		driver.findElement(By.name("inputPayeeName")).sendKeys("thinkdeeply");
		driver.findElement(By.name("inputPayeeName")).clear();
		driver.findElement(By.name("inputPayeeName")).sendKeys("test");
		driver.findElement(By.name("inputDescription")).clear();
		driver.findElement(By.name("inputDescription")).sendKeys("test");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.findElement(By.id("ReserveTabLink")).click();
		driver.findElement(By.id("setReserveAction")).click();
		driver.findElement(By.name("inputReserveAmount")).clear();
		driver.findElement(By.name("inputReserveAmount")).sendKeys("3000");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("AssessmentTabLink")).click();
		driver.findElement(By.id("assessAction")).click();
		driver.findElement(By.name("inputDateOfLoss")).clear();
		driver.findElement(By.name("inputDateOfLoss")).sendKeys("12/3/2012");
		driver.findElement(By.name("inputExpectedDuration")).clear();
		driver.findElement(By.name("inputExpectedDuration")).sendKeys("10");
		new Select(driver.findElement(By.name("selectComplexity"))).selectByVisibleText("Medium");
		new Select(driver.findElement(By.name("selectDecision"))).selectByVisibleText("Approved");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("AssessmentTabLink")).click();
		driver.findElement(By.id("authorisePaymentAction")).click();
		driver.findElement(By.name("calculateSubmit")).click();
		driver.findElement(By.name("inputPaymentAmount")).clear();
		driver.findElement(By.name("inputPaymentAmount")).sendKeys("1.00");
		driver.findElement(By.name("inputToDate")).clear();
		driver.findElement(By.name("inputToDate")).sendKeys("12/3/2012");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("selectPayee"))).selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
