package com.aiaa.claim;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RunWith(ImageRunner.class)
public class CPIWarningTest extends AIAAAbstractTest {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private String baseUrl = "http://cibwkdpcon007:9092";
	private String claimNumber;

	@Test
	public void recordAnOngoingClaim() throws Exception {
		prepareClaim();

		DateTime now = new DateTime(2012, 3, 15, 0, 0, 0, 0);

		DateTimeFormatter pattern = DateTimeFormat.forPattern("d/M/yyyy");
		DateTimeFormatter cedPattern = DateTimeFormat.forPattern("/d-MMM");

		Payment initialPayment = recordPayment(now.toString(pattern), now.plusYears(1).toString(pattern),
				true);// warning

		authorisePayment(initialPayment);
		releasePayments();
		checkCPIState("Yes" + now.toString(cedPattern));
		// ced 15/Mar

		Payment payment1 = recordPayment(now.plusYears(2).minusDays(1).toString(pattern), now
				.plusYears(2).toString(pattern), true);// crossed
														// 14/3/2014->15/3/2014
		Payment payment2 = recordPayment(now.plusYears(2).plusDays(1).toString(pattern), now.plusYears(2)
				.plusDays(2).toString(pattern), false);// not
														// 16/3/2014->17/3/2014

		// ced now-1day
		authorisePayment(payment1);
		authorisePayment(payment2);

		releasePayments();
		checkCPIState("Yes" + now.toString(cedPattern));// ced 15/Mar

		reversePayment(initialPayment);

		checkCPIState("Yes" + now.plusYears(2).minusDays(1).toString(cedPattern));// ced 14/Mar
	}

	@Test
	public void initialPaymentOnlyOneDay() throws Exception {
		prepareClaim();

		DateTime now = new DateTime(2012, 3, 15, 0, 0, 0, 0);

		DateTimeFormatter pattern = DateTimeFormat.forPattern("d/M/yyyy");
		DateTimeFormatter cedPattern = DateTimeFormat.forPattern("/dd-MMM");

		Payment initialPayment = recordPayment(now.toString(pattern), now.toString(pattern), false);// warning

		authorisePayment(initialPayment);
		releasePayments();
		checkCPIState("Yes" + now.toString(cedPattern));
		// ced 15/Mar

		reversePayment(initialPayment);

		checkCPIState("Yes");// ced Yes
	}

	private void prepareClaim() {
		recordClaim();
		claimNumber = getClaimNumber();

		checkCPIState("Yes");

		setPayeeDetails();

		reserveClaim();

		assessClaim();
	}

	private void reversePayment(Payment payment) {

		viewClaim();
		reversePaymentDetail(payment);
	}

	private void reversePaymentDetail(Payment payment) {

		driver.findElement(By.id("PaymentsTabLink")).click();
		List<WebElement> trs = driver.findElements(By.xpath("id('payments')/tbody/tr[@class]"));
		for (WebElement tr : trs) {
			WebElement fromDate = tr.findElement(By.className("fromColumnValue"));
			WebElement toDate = tr.findElement(By.className("toColumnValue"));
			if (fromDate.getText().equals(payment.getFrom()) && toDate.getText().equals(payment.getTo())) {
				tr.findElement(By.linkText("view")).click();
				driver.findElement(By.id("reversePayment")).click();
				driver.findElement(By.name("confirmTask")).click();
				driver.findElement(By.name("complete")).click();
				assertOperation("Reverse Payment (.*) Complete");

				driver.findElement(By.name("complete")).click();
				assertOperation("View Payment (.*) Complete");
				break;
			}
		}

		// driver.findElement(By.name("complete")).click();
	}

	private void releasePayments() {
		goHome();
		driver.findElement(By.id("startReleasePayments")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Release Payments Complete .* \\(See System Logs\\)");
	}

	private void authorisePayment(Payment payment) {
		viewClaim();
		authorisePaymentDetails(payment);
	}

	private void authorisePaymentDetails(Payment payment) {
		driver.findElement(By.id("PaymentsTabLink")).click();
		List<WebElement> trs = driver.findElements(By.xpath("id('payments')/tbody/tr[@class]"));
		for (WebElement tr : trs) {
			WebElement fromDate = tr.findElement(By.className("fromColumnValue"));
			WebElement toDate = tr.findElement(By.className("toColumnValue"));
			if (fromDate.getText().equals(payment.getFrom()) && toDate.getText().equals(payment.getTo())) {
				WebElement authorised = tr.findElement(By.className("authorisedColumnValue"));
				if (!authorised.getText().equals("Not Required")) {
					tr.findElement(By.linkText("view")).click();
					driver.findElement(By.id("authorisePaymentAction")).click();
					driver.findElement(By.name("next")).click();
					driver.findElement(By.name("confirmTask")).click();
					driver.findElement(By.name("complete")).click();
					assertOperation("Authorise Payment (.*) Complete");

					driver.findElement(By.name("complete")).click();
					assertOperation("View Payment (.*) Complete");
				}
				break;
			}
		}

		driver.findElement(By.name("complete")).click();
	}

	private void assessClaim() {
		viewClaim();
		assessClaimDetails();
	}

	private void reserveClaim() {
		viewClaim();
		reserveClaimDetails();
	}

	private void setPayeeDetails() {
		viewClaim();
		payeeDetails();
	}

	private void checkCPIState(String cpiState) {
		viewClaim();
		String cpi = driver.findElement(By.id("cpiColumn")).getText();
		assertEquals(cpiState, cpi);
	}

	private Payment recordPayment(String from, String to, boolean isCpiWarning) {
		viewClaim();
		return recordPaymentDetails(from, to, isCpiWarning);
	}

	private void viewClaim() {
		if (driver.getTitle().startsWith("AIA Claims : View Claim : " + claimNumber)) {
			return;
		} else {
			goHome();
			driver.findElement(By.name("inputClaimNumber")).clear();
			driver.findElement(By.name("inputClaimNumber")).sendKeys(claimNumber);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.linkText(claimNumber)).click();
		}
	}

	private void goHome() {
		if (!driver.getTitle().equals("AIA Claims : Home")) {
			driver.get(baseUrl + "/ClaimsAdminWeb/app");
		}
	}

	private Payment recordPaymentDetails(String from, String to, boolean isCpiWarning) {
		WebElement tabLink = driver.findElement(By.className("tab-selected"));
		if (!tabLink.getText().equals("Assessment")) {
			driver.findElement(By.id("AssessmentTabLink")).click();
		}
		driver.findElement(By.id("authorisePaymentAction")).click();
		driver.findElement(By.name("inputPaymentAmount")).clear();
		driver.findElement(By.name("inputPaymentAmount")).sendKeys("1.00");
		driver.findElement(By.name("inputFromDate")).clear();
		driver.findElement(By.name("inputFromDate")).sendKeys(from);
		driver.findElement(By.name("inputToDate")).sendKeys(to);
		driver.findElement(By.name("next")).click();

		if (isCpiWarning) {
			String cpiWarning = driver.findElement(By.xpath("//h3[@style]")).getText();
			assertTrue(cpiWarning.startsWith("NOTE: Payment crosses Claims Escalation Date"));
			driver.findElement(By.name("next")).click();
		}
		new Select(driver.findElement(By.name("selectPayee")))
				.selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(driver.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Record Payment (.*) Complete");
		return new Payment(from, to);
	}

	private void assessClaimDetails() {
		driver.findElement(By.id("AssessmentTabLink")).click();
		driver.findElement(By.id("assessAction")).click();
		driver.findElement(By.name("inputDateOfLoss")).clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		driver.findElement(By.name("inputDateOfLoss")).sendKeys(sdf.format(new Date()));
		driver.findElement(By.name("inputExpectedDuration")).clear();
		driver.findElement(By.name("inputExpectedDuration")).sendKeys("10");
		// TODO why
		// new
		// Select(driver.findElement(By.name("selectComplexity"))).selectByVisibleText("Medium");
		new Select(driver.findElement(By.name("selectDecision"))).selectByVisibleText("Approved");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Assess Claim (.*) Complete");
	}

	private void reserveClaimDetails() {
		driver.findElement(By.id("ReserveTabLink")).click();
		driver.findElement(By.id("setReserveAction")).click();
		driver.findElement(By.name("inputReserveAmount")).clear();
		driver.findElement(By.name("inputReserveAmount")).sendKeys("3000");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Set Reserve Complete");
	}

	private void payeeDetails() {
		driver.findElement(By.id("Payee DetailsTabLink")).click();
		driver.findElement(By.id("recordPayeeDetails")).click();
		new Select(driver.findElement(By.name("selectPayee")))
				.selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(driver.findElement(By.name("selectPaymentMethod"))).selectByVisibleText("Manual");
		driver.findElement(By.name("changePaymentDetails")).click();
		driver.findElement(By.name("inputPayeeName")).clear();
		driver.findElement(By.name("inputPayeeName")).sendKeys("test");
		driver.findElement(By.name("inputDescription")).clear();
		driver.findElement(By.name("inputDescription")).sendKeys("test");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("RecordPayeeDetails (.*) Complete");
	}

	private void assertOperation(String pattern) {
		String text = driver.findElement(By.className("info")).getText();
		LOG.info(text);
		assertTrue(text != null && text.length() > 0);
		assertTrue(Pattern.compile(pattern).matcher(text).matches());
	}

	private void recordClaim() {
		driver.get(baseUrl + "/ClaimsAdminWeb/app");
		driver.findElement(By.name("inputClaimNumber")).clear();
		driver.findElement(By.name("inputClaimNumber")).sendKeys("");
		driver.findElement(By.name("inputPolicyNumber")).clear();
		driver.findElement(By.name("inputPolicyNumber")).sendKeys("0000MP9936");
		driver.findElement(By.name("inputMemberNumber")).clear();
		driver.findElement(By.name("inputMemberNumber")).sendKeys("545");
		driver.findElement(By.id("recordClaim")).click();
		new Select(driver.findElement(By.name("selectBenefitName")))
				.selectByVisibleText("SCI - WP 90D 2 YRS (DEFAULT) : GROUP SUPER SALARY CONTINUANCE - FT");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return driver.findElement(By.name("aiaEmbedded$1")).getAttribute("value").length() > 0;
			}
		});
		new Select(driver.findElement(By.name("aiaEmbedded$4")))
				.selectByVisibleText("American Home (Non-life Rein)");
		new Select(driver.findElement(By.name("aiaEmbedded$6"))).selectByVisibleText("Agreed Value");
		new Select(driver.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Yes");

		new Select(driver.findElement(By.name("aiaEmbedded$13"))).selectByVisibleText("AAL");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("0.005");
		new Select(driver.findElement(By.name("aiaEmbedded$8"))).selectByVisibleText("Yes");
		new Select(driver.findElement(By.name("aiaEmbedded$10"))).selectByVisibleText("1 Year");
		driver.findElement(By.name("aiaEmbedded$11")).clear();
		driver.findElement(By.name("aiaEmbedded$11")).sendKeys("10");
		new Select(driver.findElement(By.name("aiaEmbedded$12"))).selectByVisibleText("Day(s)");
		driver.findElement(By.name("inputExclusions")).clear();
		driver.findElement(By.name("inputExclusions")).sendKeys("test");

		new Select(driver.findElement(By.name("aiaEmbedded$9"))).selectByVisibleText("Monthly");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("aiaEmbedded"))).selectByVisibleText("Mr");
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
		new Select(driver.findElement(By.name("aiaEmbedded$22"))).selectByVisibleText("QLD");
		new Select(driver.findElement(By.name("aiaEmbedded$28"))).selectByVisibleText("QLD");
		driver.findElement(By.name("checkInsuredPosIsResCheckbox")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$17"))).selectByVisibleText("Employed");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$0"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$2")).clear();
		driver.findElement(By.name("aiaEmbedded$2")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$4")).clear();
		driver.findElement(By.name("aiaEmbedded$4")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$6")).clear();
		driver.findElement(By.name("aiaEmbedded$6")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$8"))).selectByVisibleText("Phone");
		new Select(driver.findElement(By.name("aiaEmbedded$15"))).selectByVisibleText("QLD");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("QLD");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$22"))).selectByVisibleText("QLD");
		driver.findElement(By.name("next")).click();
		new Select(driver.findElement(By.name("aiaEmbedded$1"))).selectByVisibleText("Mr");
		driver.findElement(By.name("aiaEmbedded$3")).clear();
		driver.findElement(By.name("aiaEmbedded$3")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$5")).clear();
		driver.findElement(By.name("aiaEmbedded$5")).sendKeys("test");
		driver.findElement(By.name("aiaEmbedded$7")).clear();
		driver.findElement(By.name("aiaEmbedded$7")).sendKeys("test");
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("QLD");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
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
		new Select(driver.findElement(By.name("aiaEmbedded$16"))).selectByVisibleText("QLD");
		driver.findElement(By.name("checkclaimRolePosIsResCheckbox")).click();
		driver.findElement(By.name("next")).click();
		driver.findElement(By.id("number")).clear();
		driver.findElement(By.id("number")).sendKeys("e13");
		driver.findElement(By.name("findCauseCode")).click();
		driver.findElement(By.name("inputLossDate")).clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		driver.findElement(By.name("inputLossDate")).sendKeys(sdf.format(new Date()));
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("test");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
	}

	// @After
	// public void tearDown() throws Exception {
	// driver.close();
	// }

	@Override
	protected DriverType getTyep() {
		return DriverType.firefox;
	}

	private String getClaimNumber() {
		assertOperation("Record Claim (.*) Complete");
		String text = driver.findElement(By.className("info")).getText();
		Matcher matcher = Pattern.compile("\\(.*\\)").matcher(text);
		String claimNumber = null;
		if (matcher.find()) {
			claimNumber = matcher.group();
			claimNumber = claimNumber.substring(1, claimNumber.length() - 1);
		}
		return claimNumber;
	}
}
