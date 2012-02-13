package com.aiaa.claim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AssessClaimTest extends AIAAAbstractTest {
	@Test
	public void testName() throws Exception {

		wd.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		wd.findElement(By.name("inputMemberNumber")).sendKeys("545");
		SeleniumUtils.select(wd.findElement(By.name("selectPolicySource")), "Compass");

		wd.findElement(By.id("recordClaim")).click();
		recordClaimDetail();
	}

	private void recordClaimDetail() {
		// Benefit Name*
		SeleniumUtils.select(wd.findElement(By.name("selectBenefitName")),
				"SCI - WP 90D 2 YRS (DEFAULT) : GROUP SUPER SALARY CONTINUANCE - FT");
		// Restrict Member Info*
		selectOptionByName("Adviser", "aiaEmbedded");
//		Reinsurer
		selectOptionByName("American Home (Non-life Rein)", "aiaEmbedded$4");
		
		setValueByName("0.005", "aiaEmbedded$5");
		selectOptionByName("Agreed Value", "aiaEmbedded$6");
		selectOptionByName("Yes", "aiaEmbedded$7");
		selectOptionByName("Lump Sum", "aiaEmbedded$9");
		selectOptionByName("Yes", "aiaEmbedded$8");
//		Benefit Period
		selectOptionByName("1 Year", "aiaEmbedded$10");
//		Waiting Period Type 	
		selectOptionByName("Month(s)", "aiaEmbedded$12");
//		Terms
		selectOptionByName("AAL", "aiaEmbedded$13");
		
		setDefaultValueByName("aiaEmbedded$14");
		setDefaultValueByName("inputExclusions");
		wd.findElement(By.name("next")).click();
		

		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded")), "Mr");
		selectOptionByName("Phone", "aiaEmbedded$7");//Preferred Contact Method
		
		setValueByName("23478234", "aiaEmbedded$0");
		setValueByName("234782344", "aiaEmbedded$2");
		setValueByName("23478234", "aiaEmbedded$4");
		setValueByName("23478234", "aiaEmbedded$6");
		setValueByName("2@a.com", "aiaEmbedded$8");
		
		
		setValueById("3411-11", "number");
		clickByName("findOccupationNumber");
		selectOptionByName("Employed", "aiaEmbedded$15");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$20")), "QLD");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$26")), "QLD");

		clickByName("checkInsuredPosIsResCheckbox");
		
		if (true) {
			return;
		}
		clickByName("next");
		clickByName("next");
		clickByName("next");
		clickByName("next");
		clickByName("next");
		setValueByName("inputCauseDescription test", "inputCauseDescription");
		clickByName("next");

		clickByName("confirmTask");
		clickByName("complete");
		String text = wd.findElement(By.className("info")).getText();
		System.out.println(text);
		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher matcher = p.matcher(text);
		if (matcher.find()) {
			String claimNumber = matcher.group();
			claimNumber = claimNumber.substring(1, claimNumber.length() - 1);

			setValueByName(claimNumber, "inputClaimNumber");
			clickByName("search");

			wd.findElement(By.linkText(claimNumber)).click();

			clickById("AssessmentTabLink");

			clickById("assessAction");
		}
	}

	@Override
	protected DriverType getTyep() {
		// TODO Auto-generated method stub
		return DriverType.firefox;
	}

	@Before
	public void setUp() {
		super.setUp();
		wd.get("http://localhost:9083/ClaimsAdminWeb/app");
		// wd.findElement(By.id("name")).sendKeys("auth1");
		// wd.findElement(By.id("pswd")).sendKeys("123");
		// wd.findElement(By.name("action")).click();

	}
	// @Test
	// public void testRehab() throws Exception {
	// String linkText = "20152";//23451
	// setValueByName(linkText, "inputClaimNumber");
	// clickByName("search");
	// wd.findElement(By.linkText(linkText)).click();
	//
	// System.out.println(wd.findElement(By.id("startEditRehabilitationTask")));
	//
	// }
}
