package com.aiaa.claim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class AssessClaimTest extends AIAAAbstractTest {
	@Test
	public void testName() throws Exception {
		wd.findElement(By.id("recordClaim")).click();
		
		recordClaim();
	}
	
	private void recordClaim() {
		SeleniumUtils.select(wd.findElement(By.name("selectBenefitName")),"DEATH COVER : GROUP LIFE DEATH SUPER");
		setValueByName("Exclusions Test", "inputExclusions");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$4")),"Swiss Reinsurance");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$6")),"Agreed Value");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$7")),"Yes");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$9")),"Lump Sum");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$8")),"Yes");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$13")),"AAL");
		
		wd.findElement(By.name("next")).click();
		
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded")),"Mr");
		setValueById("1111-11", "number");
		clickByName("findOccupationNumber");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$15")),"Self Employed");
		SeleniumUtils.select(wd.findElement(By.name("aiaEmbedded$20")),"VIC");
		
		clickByName("checkInsuredPosIsResCheckbox");
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
		Pattern p=Pattern.compile("\\(.*\\)");
		Matcher matcher = p.matcher(text);
		if(matcher.find()){
		String claimNumber = matcher.group();
		claimNumber=claimNumber.substring(1, claimNumber.length()-1);
		
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
	public void setUp(){
		super.setUp();
		wd.get("http://localhost:9084/ClaimsAdminWeb/app");
//		wd.findElement(By.id("name")).sendKeys("auth1");
//		wd.findElement(By.id("pswd")).sendKeys("123");
//		wd.findElement(By.name("action")).click();
		
		wd.findElement(By.name("inputPolicyNumber")).sendKeys("MP9883");
		wd.findElement(By.name("inputMemberNumber")).sendKeys("4");
		SeleniumUtils.select(wd.findElement(By.name("selectPolicySource")),"Compass");
	}
	@Test
	public void testRehab() throws Exception {
		String linkText = "20152";//23451
		setValueByName(linkText, "inputClaimNumber");
		clickByName("search");
		wd.findElement(By.linkText(linkText)).click();
		
		System.out.println(wd.findElement(By.id("startEditRehabilitationTask")));
	
	}
}
