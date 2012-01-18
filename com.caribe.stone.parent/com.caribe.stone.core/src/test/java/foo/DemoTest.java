/*package foo;

import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoTest extends BaseFunctionalTestCase {
	private static Logger logger = LoggerFactory.getLogger(DemoTest.class);
	
	@Test
	public void demo() throws Exception {
		 driver.get("http://10.69.4.187:9086/NotificationWeb/");
		 
		 driver.findElement(By.xpath("//input[@value='Search Notification']")).click();
//		 driver.wait(1000);
		 driver.findElement(By.xpath("/html/body/div/div[2]/form/div[4]/table[2]/tbody/tr/td/a")).click();
		 
		 driver.findElement(By.linkText("Reminders")).click();
		 
		 driver.findElement(By.xpath("/html/body/div/div[2]/form/div[4]/table/tbody/tr/td[6]/a")).click();
		 
		 driver.findElement(By.xpath("/html/body/div/div[2]/form/div[3]/table/tbody/tr[3]/td/textarea")).sendKeys("test"+new Date());
		 
		 driver.findElement(By.xpath("/html/body/div/div[2]/form/div[4]/table/tbody/tr/td[3]/input")).click();
		 logger.info(driver.getTitle());
	}
}
*/