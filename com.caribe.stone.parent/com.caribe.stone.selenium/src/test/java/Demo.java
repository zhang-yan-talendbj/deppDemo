import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.thoughtworks.selenium.DefaultSelenium;

public class Demo {

	public static void main(String[] args) {
		WebDriver wd = new InternetExplorerDriver();
		wd.get("http://cibwkdp000105:9086/NotificationWeb/search.faces");
		List<WebElement> findElements = wd.findElements(By.xpath("//input"));
		for (WebElement e : findElements) {
			System.out.println(e.getAttribute("value"));
		}
		wd.quit();
	}
}
