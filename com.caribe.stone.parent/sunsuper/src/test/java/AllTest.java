import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class AllTest {

	private static WebDriver driver;
	private String url = "http://10.69.4.187:9086/NotificationWeb/";

	@Test
	public void testName() throws Exception {
		driver.get(url);
		assertEquals("AIA Pre-Claim", driver.getTitle());

		WebElement recordBtn = driver.findElement(By.xpath("//input[@value='Record Notification']"));
		recordBtn.click();

		WebElement claimantDetailsDiv = driver.findElement(By.id("bodyTitle"));
		assertEquals("Claimant Details", claimantDetailsDiv.getText());

		List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='text']"));
		inputs.get(0).sendKeys("Zhang");
		inputs.get(1).sendKeys("Yan");
		inputs.get(2).sendKeys("30/09/1985");
		inputs.get(3).sendKeys("Address1");
		inputs.get(8).sendKeys("(8610)65161616");
		inputs.get(12).sendKeys("Bruce-Y.Zhang@aia.com");
		inputs.get(13).sendKeys("MP8032");
		inputs.get(14).sendKeys("100100");
		inputs.get(15).sendKeys("Bruce");

		List<WebElement> selects = driver.findElements(By.tagName("select"));
		selectOption(selects.get(0), "Mr");
		selectOption(selects.get(1), "SA");
		selectOption(selects.get(2), "TPD");
		selectOption(selects.get(3), "SuperMan");

		driver.findElement(By.xpath("//input[@value='Next']")).click();

		WebElement dateFirstNotified = driver.findElement(By.xpath("//input[@type='text'][1]"));
		dateFirstNotified.sendKeys("01/10/2011");

		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
		driver.findElement(By.xpath("//input[@value='Ok']")).click();

		String successMsg = driver.findElement(By.id("alertConfirm")).getText();
		System.out.println(successMsg);

		driver.findElement(By.xpath("//input[@value='Search Notification']")).click();

		List<WebElement> notification = driver.findElements(By.xpath("//div[@id='bodyListTable']/table[2]/tbody/tr/td"));
		assertEquals(7, notification.size());
		assertEquals(successMsg.substring(16,22), notification.get(0).getText());
		assertEquals("Notification", notification.get(1).getText());
	}
	
//	@Test
//	public void testWait() throws Exception {
//		driver.get("http://somedomain/url_that_delays_loading");
//		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
//		  .until(new ExpectedCondition<WebElement>(){
//			@Override
//			public WebElement apply(WebDriver d) {
//				return d.findElement(By.id("myDynamicElement"));
//			}});
//	}
	
	public void selectOption(WebElement select, String selectedOption) {
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals(selectedOption)) {
				option.click();
			}
		}
	}

	@AfterClass
	public static void destoryDriver() {
		 driver.quit();
	}

	@BeforeClass
	public static void initDriver() {
//		InternetExplorerDriver will start IE, HtmlUnitDriver is very fast!!!
		driver = new InternetExplorerDriver();
//		driver = new HtmlUnitDriver();
//		driver = new FirefoxDriver();
	}
}
