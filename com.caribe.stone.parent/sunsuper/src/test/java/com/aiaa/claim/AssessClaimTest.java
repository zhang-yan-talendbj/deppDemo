package com.aiaa.claim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AssessClaimTest extends AIAAAbstractTest {
	@Test
	public void recordClaim() throws Exception {
		driver.findElement(By.name("inputPolicyNumber")).clear();
		driver.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		driver.findElement(By.name("inputMemberNumber")).clear();
		driver.findElement(By.name("inputMemberNumber")).sendKeys("545");
		driver.findElement(By.id("recordClaim")).click();
		new Select(driver.findElement(By.name("selectBenefitName")))
				.selectByVisibleText("SCI - WP 90D 2 YRS (DEFAULT) : GROUP SUPER SALARY CONTINUANCE - FT");
		new Select(driver.findElement(By.name("aiaEmbedded"))).selectByVisibleText("Adviser");
		new Select(driver.findElement(By.name("aiaEmbedded$4")))
				.selectByVisibleText("American Home (Non-life Rein)");
		new Select(driver.findElement(By.name("aiaEmbedded$6"))).selectByVisibleText("Agreed Value");
		new Select(driver.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Yes");
		new Select(driver.findElement(By.name("aiaEmbedded$9"))).selectByVisibleText("Lump Sum");
		new Select(driver.findElement(By.name("aiaEmbedded$13"))).selectByVisibleText("AAL");
		driver.findElement(By.name("aiaEmbedded$14")).clear();
		driver.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		driver.findElement(By.name("inputExclusions")).clear();
		driver.findElement(By.name("inputExclusions")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("0.005");
		new Select(driver.findElement(By.name("aiaEmbedded$8"))).selectByVisibleText("Yes");
		new Select(driver.findElement(By.name("aiaEmbedded$10"))).selectByVisibleText("1 Year");
		new Select(driver.findElement(By.name("aiaEmbedded$12"))).selectByVisibleText("Month(s)");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("aiaEmbedded"))).selectByVisibleText("Ms");
		new Select(driver.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Phone");
		driver.findElement(By.name("aiaEmbedded$0")).clear();
		driver.findElement(By.name("aiaEmbedded$0")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$8")).clear();
		driver.findElement(By.name("aiaEmbedded$8")).sendKeys("a@aia.com");
		driver.findElement(By.id("number")).clear();
		driver.findElement(By.id("number")).sendKeys("3411-11");
		driver.findElement(By.name("findOccupationNumber")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$15"))).selectByVisibleText("Employed");
		driver.findElement(By.name("aiaEmbedded$16")).clear();
		driver.findElement(By.name("aiaEmbedded$16")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$17")).clear();
		driver.findElement(By.name("aiaEmbedded$17")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$18")).clear();
		driver.findElement(By.name("aiaEmbedded$18")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$19")).clear();
		driver.findElement(By.name("aiaEmbedded$19")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$20"))).selectByVisibleText("QLD");
		driver.findElement(By.name("aiaEmbedded$21")).clear();
		driver.findElement(By.name("aiaEmbedded$21")).sendKeys("1123");
		driver.findElement(By.name("checkInsuredPosIsResCheckbox")).click();
		driver.findElement(By.name("aiaEmbedded$22")).clear();
		driver.findElement(By.name("aiaEmbedded$22")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$23")).clear();
		driver.findElement(By.name("aiaEmbedded$23")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$24")).clear();
		driver.findElement(By.name("aiaEmbedded$24")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$25")).clear();
		driver.findElement(By.name("aiaEmbedded$25")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$26"))).selectByVisibleText("QLD");
		driver.findElement(By.name("aiaEmbedded$27")).clear();
		driver.findElement(By.name("aiaEmbedded$27")).sendKeys("1123");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("aiaEmbedded")).clear();
		driver.findElement(By.name("aiaEmbedded")).sendKeys("43418292917");
		new Select(driver.findElement(By.name("aiaEmbedded$0"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("sdf");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("12313123");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("1231312");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("1231312");
		driver.findElement(By.name("aiaEmbedded$9")).clear();
		driver.findElement(By.name("aiaEmbedded$9")).sendKeys("a@aia.com");
		new Select(driver.findElement(By.name("aiaEmbedded$15"))).selectByVisibleText("NSW");
		driver.findElement(By.name("aiaEmbedded$11")).clear();
		driver.findElement(By.name("aiaEmbedded$11")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$12")).clear();
		driver.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$13")).clear();
		driver.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$14")).clear();
		driver.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$16")).clear();
		driver.findElement(By.name("aiaEmbedded$16")).sendKeys("2322");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("aiaEmbedded")).clear();
		driver.findElement(By.name("aiaEmbedded")).sendKeys("AIA");
		driver.findElement(By.name("aiaEmbedded$0")).clear();
		driver.findElement(By.name("aiaEmbedded$0")).sendKeys("34534232");
		new Select(driver.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Ms");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("TEST");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$8")).clear();
		driver.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		driver.findElement(By.name("aiaEmbedded$10")).clear();
		driver.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		driver.findElement(By.name("aiaEmbedded$12")).clear();
		driver.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$13")).clear();
		driver.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$14")).clear();
		driver.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$15")).clear();
		driver.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("NSW");
		driver.findElement(By.name("aiaEmbedded$17")).clear();
		driver.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("aiaEmbedded")).clear();
		driver.findElement(By.name("aiaEmbedded")).sendKeys("AIA-PPRU");
		driver.findElement(By.name("aiaEmbedded$0")).clear();
		driver.findElement(By.name("aiaEmbedded$0")).sendKeys("25463521");
		new Select(driver.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$8")).clear();
		driver.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		driver.findElement(By.name("aiaEmbedded$10")).clear();
		driver.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		driver.findElement(By.name("aiaEmbedded$12")).clear();
		driver.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$13")).clear();
		driver.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$14")).clear();
		driver.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$15")).clear();
		driver.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("NSW");
		driver.findElement(By.name("aiaEmbedded$17")).clear();
		driver.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("aiaEmbedded")).clear();
		driver.findElement(By.name("aiaEmbedded")).sendKeys("AIAsdf");
		driver.findElement(By.name("aiaEmbedded$0")).clear();
		driver.findElement(By.name("aiaEmbedded$0")).sendKeys("52463234");
		new Select(driver.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		driver.findElement(By.name("aiaEmbedded$8")).clear();
		driver.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		driver.findElement(By.name("aiaEmbedded$10")).clear();
		driver.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		driver.findElement(By.name("aiaEmbedded$12")).clear();
		driver.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$13")).clear();
		driver.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$14")).clear();
		driver.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$15")).clear();
		driver.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("QLD");
		driver.findElement(By.name("aiaEmbedded$17")).clear();
		driver.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("inputLossDate")).clear();
		driver.findElement(By.name("inputLossDate")).sendKeys("29/2/2012");
		driver.findElement(By.id("number")).clear();
		driver.findElement(By.id("number")).sendKeys("e13");
		driver.findElement(By.name("findCauseCode")).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("test");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.name("inputClaimNumber")).clear();

		String claimNumber = getClaimNumber();
		driver.findElement(By.name("inputClaimNumber")).sendKeys(claimNumber);
		driver.findElement(By.name("search")).click();

		// select the created claim
		driver.findElement(By.linkText(claimNumber)).click();
if(true){
	return ;
}
		driver.findElement(By.id("AssessmentTabLink")).click();
		driver.findElement(By.id("assessAction")).click();
		driver.findElement(By.name("inputAssessedBenefitAmount")).clear();
		driver.findElement(By.name("inputAssessedBenefitAmount")).sendKeys("4000.00");
		driver.findElement(By.name("inputDateOfLoss")).clear();
		driver.findElement(By.name("inputDateOfLoss")).sendKeys("29/02/2011");
		new Select(driver.findElement(By.name("selectDecision"))).selectByVisibleText("Approved");
		new Select(driver.findElement(By.name("selectComplexity"))).selectByVisibleText("Medium");
		driver.findElement(By.name("inputDateOfLoss")).clear();
		driver.findElement(By.name("inputDateOfLoss")).sendKeys("28/2/2011");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("authorisePaymentAction")).click();
		driver.findElement(By.name("inputPaymentAmount")).clear();
		driver.findElement(By.name("inputPaymentAmount")).sendKeys("2500.00");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("selectPayee")))
				.selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(driver.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("ClaimTabLink")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("startReleasePayments")).click();
		// driver.findElement(By.name("$Checkbox$0")).click();
		// driver.findElement(By.name("$Checkbox$0$0")).click();
		// driver.findElement(By.name("$Checkbox$0$1")).click();
		// driver.findElement(By.name("$Checkbox$0$2")).click();
		// driver.findElement(By.name("$Checkbox$0$3")).click();
		// driver.findElement(By.name("$Checkbox$0$4")).click();
		// driver.findElement(By.name("$Checkbox$0$5")).click();
		// driver.findElement(By.name("$Checkbox$0$6")).click();
		// driver.findElement(By.name("$Checkbox$0$7")).click();
		// driver.findElement(By.name("$Checkbox$0$8")).click();
		// driver.findElement(By.name("$Checkbox$0$9")).click();
		// driver.findElement(By.name("$Checkbox$0$10")).click();
		// driver.findElement(By.name("$Checkbox")).click();
		// driver.findElement(By.name("$Checkbox$0$11")).click();
		// driver.findElement(By.name("$Checkbox$0$12")).click();
		// driver.findElement(By.name("$Checkbox$0$13")).click();
		// driver.findElement(By.name("$Checkbox$0$14")).click();
		// driver.findElement(By.name("$Checkbox$0$15")).click();
		// driver.findElement(By.name("$Checkbox$0$16")).click();
		// driver.findElement(By.name("next")).click();
		// driver.findElement(By.name("confirmTask")).click();
		// driver.findElement(By.name("complete")).click();
	}

	private String getClaimNumber() {
		String text = driver.findElement(By.className("info")).getText();
		System.out.println(text);
		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher matcher = p.matcher(text);
		String claimNumber = null;
		if (matcher.find()) {
			claimNumber = matcher.group();
			claimNumber = claimNumber.substring(1, claimNumber.length() - 1);
		}
		return claimNumber;
	}

	private void tt() {
		// home page
		driver.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		driver.findElement(By.name("inputMemberNumber")).sendKeys("545");
		SeleniumUtils.select(driver.findElement(By.name("selectPolicySource")), "Compass");

		driver.findElement(By.id("recordClaim")).click();

		clickByName("next");
		clickByName("next");
		clickByName("next");
		clickByName("next");
		setValueByName("inputCauseDescription test", "inputCauseDescription");
		clickByName("next");

		clickByName("confirmTask");
		clickByName("complete");
		String text = driver.findElement(By.className("info")).getText();
		System.out.println(text);
		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher matcher = p.matcher(text);
		if (matcher.find()) {
			String claimNumber = matcher.group();
			claimNumber = claimNumber.substring(1, claimNumber.length() - 1);

			setValueByName(claimNumber, "inputClaimNumber");
			clickByName("search");

			driver.findElement(By.linkText(claimNumber)).click();

			clickById("AssessmentTabLink");

			clickById("assessAction");
		}
	}

	@Override
	protected DriverType getTyep() {
		return DriverType.html;
	}

	@Before
	public void setUp() {
		super.setUp();
		driver.get("http://localhost:9083/ClaimsAdminWeb/app");
		// driver.findElement(By.id("name")).sendKeys("auth1");
		// driver.findElement(By.id("pswd")).sendKeys("123");
		// driver.findElement(By.name("action")).click();

	}
}
