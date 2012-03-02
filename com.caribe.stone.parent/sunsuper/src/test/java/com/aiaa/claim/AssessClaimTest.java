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
		wd.findElement(By.name("inputPolicyNumber")).clear();
		wd.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		wd.findElement(By.name("inputMemberNumber")).clear();
		wd.findElement(By.name("inputMemberNumber")).sendKeys("545");
		wd.findElement(By.id("recordClaim")).click();
		new Select(wd.findElement(By.name("selectBenefitName")))
				.selectByVisibleText("SCI - WP 90D 2 YRS (DEFAULT) : GROUP SUPER SALARY CONTINUANCE - FT");
		new Select(wd.findElement(By.name("aiaEmbedded"))).selectByVisibleText("Adviser");
		new Select(wd.findElement(By.name("aiaEmbedded$4")))
				.selectByVisibleText("American Home (Non-life Rein)");
		new Select(wd.findElement(By.name("aiaEmbedded$6"))).selectByVisibleText("Agreed Value");
		new Select(wd.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Yes");
		new Select(wd.findElement(By.name("aiaEmbedded$9"))).selectByVisibleText("Lump Sum");
		new Select(wd.findElement(By.name("aiaEmbedded$13"))).selectByVisibleText("AAL");
		wd.findElement(By.name("aiaEmbedded$14")).clear();
		wd.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		wd.findElement(By.name("inputExclusions")).clear();
		wd.findElement(By.name("inputExclusions")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$5")).clear();
		wd.findElement(By.name("aiaEmbedded$5")).sendKeys("0.005");
		new Select(wd.findElement(By.name("aiaEmbedded$8"))).selectByVisibleText("Yes");
		new Select(wd.findElement(By.name("aiaEmbedded$10"))).selectByVisibleText("1 Year");
		new Select(wd.findElement(By.name("aiaEmbedded$12"))).selectByVisibleText("Month(s)");
		wd.findElement(By.name("next")).click();
		new Select(wd.findElement(By.name("aiaEmbedded"))).selectByVisibleText("Ms");
		new Select(wd.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Phone");
		wd.findElement(By.name("aiaEmbedded$0")).clear();
		wd.findElement(By.name("aiaEmbedded$0")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$2")).clear();
		wd.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		wd.findElement(By.name("aiaEmbedded$4")).clear();
		wd.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$6")).clear();
		wd.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$8")).clear();
		wd.findElement(By.name("aiaEmbedded$8")).sendKeys("a@aia.com");
		wd.findElement(By.id("number")).clear();
		wd.findElement(By.id("number")).sendKeys("3411-11");
		wd.findElement(By.name("findOccupationNumber")).click();
		new Select(wd.findElement(By.name("aiaEmbedded$15"))).selectByVisibleText("Employed");
		wd.findElement(By.name("aiaEmbedded$16")).clear();
		wd.findElement(By.name("aiaEmbedded$16")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$17")).clear();
		wd.findElement(By.name("aiaEmbedded$17")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$18")).clear();
		wd.findElement(By.name("aiaEmbedded$18")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$19")).clear();
		wd.findElement(By.name("aiaEmbedded$19")).sendKeys("test");
		new Select(wd.findElement(By.name("aiaEmbedded$20"))).selectByVisibleText("QLD");
		wd.findElement(By.name("aiaEmbedded$21")).clear();
		wd.findElement(By.name("aiaEmbedded$21")).sendKeys("1123");
		wd.findElement(By.name("checkInsuredPosIsResCheckbox")).click();
		wd.findElement(By.name("aiaEmbedded$22")).clear();
		wd.findElement(By.name("aiaEmbedded$22")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$23")).clear();
		wd.findElement(By.name("aiaEmbedded$23")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$24")).clear();
		wd.findElement(By.name("aiaEmbedded$24")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$25")).clear();
		wd.findElement(By.name("aiaEmbedded$25")).sendKeys("test");
		new Select(wd.findElement(By.name("aiaEmbedded$26"))).selectByVisibleText("QLD");
		wd.findElement(By.name("aiaEmbedded$27")).clear();
		wd.findElement(By.name("aiaEmbedded$27")).sendKeys("1123");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("aiaEmbedded")).clear();
		wd.findElement(By.name("aiaEmbedded")).sendKeys("43418292917");
		new Select(wd.findElement(By.name("aiaEmbedded$0"))).selectByVisibleText("Mr");
		wd.findElement(By.name("aiaEmbedded$2")).clear();
		wd.findElement(By.name("aiaEmbedded$2")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$4")).clear();
		wd.findElement(By.name("aiaEmbedded$4")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$6")).clear();
		wd.findElement(By.name("aiaEmbedded$6")).sendKeys("sdf");
		wd.findElement(By.name("aiaEmbedded$3")).clear();
		wd.findElement(By.name("aiaEmbedded$3")).sendKeys("12313123");
		wd.findElement(By.name("aiaEmbedded$5")).clear();
		wd.findElement(By.name("aiaEmbedded$5")).sendKeys("1231312");
		wd.findElement(By.name("aiaEmbedded$7")).clear();
		wd.findElement(By.name("aiaEmbedded$7")).sendKeys("1231312");
		wd.findElement(By.name("aiaEmbedded$9")).clear();
		wd.findElement(By.name("aiaEmbedded$9")).sendKeys("a@aia.com");
		new Select(wd.findElement(By.name("aiaEmbedded$15"))).selectByVisibleText("NSW");
		wd.findElement(By.name("aiaEmbedded$11")).clear();
		wd.findElement(By.name("aiaEmbedded$11")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$12")).clear();
		wd.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$13")).clear();
		wd.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$14")).clear();
		wd.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$16")).clear();
		wd.findElement(By.name("aiaEmbedded$16")).sendKeys("2322");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("aiaEmbedded")).clear();
		wd.findElement(By.name("aiaEmbedded")).sendKeys("AIA");
		wd.findElement(By.name("aiaEmbedded$0")).clear();
		wd.findElement(By.name("aiaEmbedded$0")).sendKeys("34534232");
		new Select(wd.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Ms");
		wd.findElement(By.name("aiaEmbedded$3")).clear();
		wd.findElement(By.name("aiaEmbedded$3")).sendKeys("TEST");
		wd.findElement(By.name("aiaEmbedded$5")).clear();
		wd.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$7")).clear();
		wd.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$3")).clear();
		wd.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$2")).clear();
		wd.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		wd.findElement(By.name("aiaEmbedded$4")).clear();
		wd.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$6")).clear();
		wd.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$8")).clear();
		wd.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		wd.findElement(By.name("aiaEmbedded$10")).clear();
		wd.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		wd.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		wd.findElement(By.name("aiaEmbedded$12")).clear();
		wd.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$13")).clear();
		wd.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$14")).clear();
		wd.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$15")).clear();
		wd.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(wd.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("NSW");
		wd.findElement(By.name("aiaEmbedded$17")).clear();
		wd.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("aiaEmbedded")).clear();
		wd.findElement(By.name("aiaEmbedded")).sendKeys("AIA-PPRU");
		wd.findElement(By.name("aiaEmbedded$0")).clear();
		wd.findElement(By.name("aiaEmbedded$0")).sendKeys("25463521");
		new Select(wd.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		wd.findElement(By.name("aiaEmbedded$3")).clear();
		wd.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$5")).clear();
		wd.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$7")).clear();
		wd.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$2")).clear();
		wd.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		wd.findElement(By.name("aiaEmbedded$4")).clear();
		wd.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$6")).clear();
		wd.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$8")).clear();
		wd.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		wd.findElement(By.name("aiaEmbedded$10")).clear();
		wd.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		wd.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		wd.findElement(By.name("aiaEmbedded$12")).clear();
		wd.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$13")).clear();
		wd.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$14")).clear();
		wd.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$15")).clear();
		wd.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(wd.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("NSW");
		wd.findElement(By.name("aiaEmbedded$17")).clear();
		wd.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("aiaEmbedded")).clear();
		wd.findElement(By.name("aiaEmbedded")).sendKeys("AIAsdf");
		wd.findElement(By.name("aiaEmbedded$0")).clear();
		wd.findElement(By.name("aiaEmbedded$0")).sendKeys("52463234");
		new Select(wd.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		wd.findElement(By.name("aiaEmbedded$3")).clear();
		wd.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$5")).clear();
		wd.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$7")).clear();
		wd.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$2")).clear();
		wd.findElement(By.name("aiaEmbedded$2")).sendKeys("234781233");
		wd.findElement(By.name("aiaEmbedded$4")).clear();
		wd.findElement(By.name("aiaEmbedded$4")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$6")).clear();
		wd.findElement(By.name("aiaEmbedded$6")).sendKeys("23478123");
		wd.findElement(By.name("aiaEmbedded$8")).clear();
		wd.findElement(By.name("aiaEmbedded$8")).sendKeys("11111111");
		wd.findElement(By.name("aiaEmbedded$10")).clear();
		wd.findElement(By.name("aiaEmbedded$10")).sendKeys("a@aia.com");
		wd.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		wd.findElement(By.name("aiaEmbedded$12")).clear();
		wd.findElement(By.name("aiaEmbedded$12")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$13")).clear();
		wd.findElement(By.name("aiaEmbedded$13")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$14")).clear();
		wd.findElement(By.name("aiaEmbedded$14")).sendKeys("test");
		wd.findElement(By.name("aiaEmbedded$15")).clear();
		wd.findElement(By.name("aiaEmbedded$15")).sendKeys("test");
		new Select(wd.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("QLD");
		wd.findElement(By.name("aiaEmbedded$17")).clear();
		wd.findElement(By.name("aiaEmbedded$17")).sendKeys("1232");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("inputLossDate")).clear();
		wd.findElement(By.name("inputLossDate")).sendKeys("29/2/2012");
		wd.findElement(By.id("number")).clear();
		wd.findElement(By.id("number")).sendKeys("e13");
		wd.findElement(By.name("findCauseCode")).click();
		wd.findElement(By.id("description")).clear();
		wd.findElement(By.id("description")).sendKeys("test");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("confirmTask")).click();
		wd.findElement(By.name("complete")).click();
		wd.findElement(By.name("inputClaimNumber")).clear();

		String text = wd.findElement(By.className("info")).getText();
		System.out.println(text);
		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher matcher = p.matcher(text);
		String claimNumber = null;
		if (matcher.find()) {
			claimNumber = matcher.group();
			claimNumber = claimNumber.substring(1, claimNumber.length() - 1);
		}
		wd.findElement(By.name("inputClaimNumber")).sendKeys(claimNumber);
		wd.findElement(By.name("search")).click();

		// select the created claim
		wd.findElement(By.linkText(claimNumber)).click();
if(true){
	return ;
}
		wd.findElement(By.id("AssessmentTabLink")).click();
		wd.findElement(By.id("assessAction")).click();
		wd.findElement(By.name("inputAssessedBenefitAmount")).clear();
		wd.findElement(By.name("inputAssessedBenefitAmount")).sendKeys("4000.00");
		wd.findElement(By.name("inputDateOfLoss")).clear();
		wd.findElement(By.name("inputDateOfLoss")).sendKeys("29/02/2011");
		new Select(wd.findElement(By.name("selectDecision"))).selectByVisibleText("Approved");
		new Select(wd.findElement(By.name("selectComplexity"))).selectByVisibleText("Medium");
		wd.findElement(By.name("inputDateOfLoss")).clear();
		wd.findElement(By.name("inputDateOfLoss")).sendKeys("28/2/2011");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("confirmTask")).click();
		wd.findElement(By.name("complete")).click();
		wd.findElement(By.id("authorisePaymentAction")).click();
		wd.findElement(By.name("inputPaymentAmount")).clear();
		wd.findElement(By.name("inputPaymentAmount")).sendKeys("2500.00");
		wd.findElement(By.name("next")).click();
		new Select(wd.findElement(By.name("selectPayee")))
				.selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(wd.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		wd.findElement(By.name("next")).click();
		wd.findElement(By.name("confirmTask")).click();
		wd.findElement(By.name("complete")).click();
		wd.findElement(By.id("ClaimTabLink")).click();
		wd.findElement(By.name("complete")).click();
		wd.findElement(By.id("startReleasePayments")).click();
		// wd.findElement(By.name("$Checkbox$0")).click();
		// wd.findElement(By.name("$Checkbox$0$0")).click();
		// wd.findElement(By.name("$Checkbox$0$1")).click();
		// wd.findElement(By.name("$Checkbox$0$2")).click();
		// wd.findElement(By.name("$Checkbox$0$3")).click();
		// wd.findElement(By.name("$Checkbox$0$4")).click();
		// wd.findElement(By.name("$Checkbox$0$5")).click();
		// wd.findElement(By.name("$Checkbox$0$6")).click();
		// wd.findElement(By.name("$Checkbox$0$7")).click();
		// wd.findElement(By.name("$Checkbox$0$8")).click();
		// wd.findElement(By.name("$Checkbox$0$9")).click();
		// wd.findElement(By.name("$Checkbox$0$10")).click();
		// wd.findElement(By.name("$Checkbox")).click();
		// wd.findElement(By.name("$Checkbox$0$11")).click();
		// wd.findElement(By.name("$Checkbox$0$12")).click();
		// wd.findElement(By.name("$Checkbox$0$13")).click();
		// wd.findElement(By.name("$Checkbox$0$14")).click();
		// wd.findElement(By.name("$Checkbox$0$15")).click();
		// wd.findElement(By.name("$Checkbox$0$16")).click();
		// wd.findElement(By.name("next")).click();
		// wd.findElement(By.name("confirmTask")).click();
		// wd.findElement(By.name("complete")).click();
	}

	private void tt() {
		// home page
		wd.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		wd.findElement(By.name("inputMemberNumber")).sendKeys("545");
		SeleniumUtils.select(wd.findElement(By.name("selectPolicySource")), "Compass");

		wd.findElement(By.id("recordClaim")).click();

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
		return DriverType.html;
	}

	@Before
	public void setUp() {
		super.setUp();
		wd.get("http://localhost:9083/ClaimsAdminWeb/app");
		// wd.findElement(By.id("name")).sendKeys("auth1");
		// wd.findElement(By.id("pswd")).sendKeys("123");
		// wd.findElement(By.name("action")).click();

	}
}
