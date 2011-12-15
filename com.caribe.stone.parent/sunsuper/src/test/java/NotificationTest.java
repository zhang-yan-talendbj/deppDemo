import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NotificationTest {

	private static WebDriver wd;
	private String url = "http://10.69.4.187:9086/NotificationWeb/";

	@Test
	public void recordNotification() throws Exception {

		wd.get(url);
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("AIA Pre-Claim", wd.getTitle());
		WebElement recordBtn = wd.findElement(By.xpath("//input[@value='Record Notification']"));
		recordBtn.click();

		WebElement claimantDetailsDiv = wd.findElement(By.id("bodyTitle"));
		assertEquals("Claimant Details", claimantDetailsDiv.getText());

		List<WebElement> inputs = wd.findElements(By.xpath("//input[@type='text']"));
		inputs.get(0).sendKeys("Zhang");
		inputs.get(1).sendKeys("Yan");
		inputs.get(2).sendKeys("30/09/1985");
		inputs.get(3).sendKeys("Address1");
		inputs.get(8).sendKeys("(8610)65161616");
		inputs.get(12).sendKeys("Bruce-Y.Zhang@aia.com");
		inputs.get(13).sendKeys("MP8032");
		inputs.get(14).sendKeys("100100");
		inputs.get(15).sendKeys("Bruce");

		List<WebElement> selects = wd.findElements(By.tagName("select"));
		selectOption(selects.get(0), "Mr");
		selectOption(selects.get(1), "SA");
		selectOption(selects.get(2), "TPD");
		selectOption(selects.get(3), "SuperMan");

		wd.findElement(By.xpath("//input[@value='Next']")).click();

		WebElement dateFirstNotified = wd.findElement(By.xpath("//input[@type='text'][1]"));
		dateFirstNotified.sendKeys("01/10/2011");

		wd.findElement(By.xpath("//input[@value='Calculate']")).click();
		wd.findElement(By.xpath("//input[@value='Ok']")).click();

		String successMsg = wd.findElement(By.id("alertConfirm")).getText();
		System.out.println(successMsg);

		wd.findElement(By.xpath("//input[@value='Search Notification']")).click();

		List<WebElement> notification = wd.findElements(By.xpath("//div[@id='bodyListTable']/table[2]/tbody/tr/td"));
		assertEquals(7, notification.size());
		assertEquals(successMsg.substring(16, 22), notification.get(0).getText());
		assertEquals("Notification1", notification.get(1).getText());
	}

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
		wd.quit();
	}

	@BeforeClass
	public static void initDriver() {
//		InternetExplorerDriver will start IE, HtmlUnitDriver is very fast!!!
//		wd= new InternetExplorerDriver();
//		wd = new HtmlUnitDriver();
		wd = new FirefoxDriver();
	}
}
