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
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(ImageRunner.class)
public class CPIWarningTest extends AIAAAbstractTest {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private String baseUrl = "http://localhost:9085";
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

		checkCPIState("Yes" + now.plusYears(2).minusDays(1).toString(cedPattern));// ced
																					// 14/Mar
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
		login();
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
//		new Select(driver.findElement(By.name("aiaEmbedded$7"))).selectByVisibleText("Phone");
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
//		new Select(driver.findElement(By.name("aiaEmbedded$8"))).selectByVisibleText("Phone");
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

		claimNumber = getClaimNumber();
	}

	private void login() {
		driver.get(baseUrl + "/ClaimsAdminWeb/login.jsp");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("");
		driver.findElement(By.id("pswd")).clear();
		driver.findElement(By.id("pswd")).sendKeys("");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("AITB003");
		driver.findElement(By.id("pswd")).clear();
		driver.findElement(By.id("pswd")).sendKeys("Ymay321y");
		driver.findElement(By.name("action")).click();
	}

	@Test()
	public void rehab() throws Exception {
		recordClaim();

		viewClaim();

//		enableRehab();
//		viewClaim();
//		recordRehab();
//
//		rehabTab();
//		getTable();
////		claimNumber="20102";
////		recordExpense("1");
		System.out.println(claimNumber);
	}

	private void recordExpense(String amount) {
		viewClaim();
		driver.findElement(By.id("ExpenseTabLink")).click();
		driver.findElement(By.id("recordExpense")).click();
		driver.findElement(By.name("inputExpenseAmount")).clear();
		driver.findElement(By.name("inputExpenseAmount")).sendKeys(amount);
		driver.findElement(By.id("inputInvoiceNumber")).clear();
		driver.findElement(By.id("inputInvoiceNumber")).sendKeys(String.valueOf(System.currentTimeMillis()));
		new Select(driver.findElement(By.name("selectPayee"))).selectByVisibleText("WEST STATE SUPER SCHEME - Owner");
		new Select(driver.findElement(By.name("selectAbn"))).selectByVisibleText("Create New - External");
		new Select(driver.findElement(By.name("selectExpenseType"))).selectByVisibleText("Rehabilitation");
		new Select(driver.findElement(By.name("selectRehab"))).selectByVisibleText("Rehab No.1");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		
		driver.findElement(By.name("complete")).click();
	}

	private void getTable() {
		List<WebElement> table = driver.findElements(By.xpath("id('rehabList')/tbody/tr[@class]"));
		// int index = getColumnIndex(table, "Rehab Number");
		// index++;
		// get rehab
		for (WebElement rehab : table) {
			System.out.println(rehab);
			if (rehab.findElement(By.xpath("td[2]")).getText().equals("1")) {
				WebElement findElement = rehab.findElement(By.linkText("Services and Program"));
				System.out.println(findElement.getText());
				findElement.click();
				break;
			}
		}
		// Add serviceApproved
		driver.findElement(By.id("addServiceApproved")).click();
		driver.findElement(By.name("inputFromDate")).clear();
		driver.findElement(By.name("inputFromDate")).sendKeys("12/3/2012");
		driver.findElement(By.name("inputToDate")).clear();
		driver.findElement(By.name("inputToDate")).sendKeys("20101");
		driver.findElement(By.name("inputToDate")).clear();
		driver.findElement(By.name("inputToDate")).sendKeys("12/3/2012");
		driver.findElement(By.name("inputFromDate")).clear();
		driver.findElement(By.name("inputFromDate")).sendKeys("1/1/2012");
		driver.findElement(By.name("inputToDate")).clear();
		driver.findElement(By.name("inputToDate")).sendKeys("1/1/2013");
		driver.findElement(By.name("inputCost")).clear();
		driver.findElement(By.name("inputCost")).sendKeys("2000");
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();
		driver.findElement(By.id("addServiceApproved")).click();
		driver.findElement(By.name("inputFromDate")).clear();
		driver.findElement(By.name("inputFromDate")).sendKeys("1/1/2012");
		driver.findElement(By.name("inputToDate")).clear();
		driver.findElement(By.name("inputToDate")).sendKeys("1/1/2013");
		driver.findElement(By.name("inputCost")).clear();
		driver.findElement(By.name("inputCost")).sendKeys("1000");
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		// System.out.println(findElements);
	}

	private int getColumnIndex(WebElement table, String columnName) {
		int index = 0;
		List<WebElement> findElements = table.findElements(By.tagName("th"));
		for (WebElement th : findElements) {
			if (th.getText().equals(columnName)) {
				break;
			}
			index++;
		}
		return index;
	}

	private void recordRehab() {
		rehabTab();
		driver.findElement(By.id("addRehab")).click();
		driver.findElement(By.name("inputDateReferred")).clear();
		driver.findElement(By.name("inputDateReferred")).sendKeys("21/7/2011");
		new Select(driver.findElement(By.name("selectReferralType"))).selectByVisibleText("NEW");
		new Select(driver.findElement(By.name("selectReferringTeam"))).selectByVisibleText("RETAIL 1");
		new Select(driver.findElement(By.name("selectAssessor"))).selectByVisibleText("Superman");
		new Select(driver.findElement(By.name("selectEmploymentStatus"))).selectByVisibleText("Employed");
		driver.findElement(By.id("number")).clear();
		driver.findElement(By.id("number")).sendKeys("e13");
		driver.findElement(By.name("findCauseCode")).click();
		new Select(driver.findElement(By.name("selectLanguage"))).selectByVisibleText("ENGLISH");
		new Select(driver.findElement(By.name("selectBenefitType"))).selectByVisibleText("TPD");
		new Select(driver.findElement(By.name("selectBenefitPeriod"))).selectByVisibleText("2");
		new Select(driver.findElement(By.name("selectFund"))).selectByVisibleText("RETAIL");
		new Select(driver.findElement(By.name("selectConsultantName"))).selectByVisibleText("JO LEE");
		driver.findElement(By.name("inputInterCompleteDate")).clear();
		driver.findElement(By.name("inputInterCompleteDate")).sendKeys("21/7/2011");
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Claim (.*) Complete");
	}

	// @After
	// public void tearDown() throws Exception {
	// driver.close();
	// }

	private void rehabTab() {
		viewClaim();
		driver.findElement(By.id("RehabTabLink")).click();
	}

	private void enableRehab() {
		driver.findElement(By.id("startEditRehabilitationTask")).click();
		new Select(driver.findElement(By.name("selectRehabilitation"))).selectByVisibleText("Yes");
		driver.findElement(By.name("next")).click();
		driver.findElement(By.name("confirmTask")).click();
		driver.findElement(By.name("complete")).click();

		assertOperation("Edit Rehabilitation Instructed (.*) Complete");
	}

	@Override
	protected DriverType getType() {
		return DriverType.html;
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
