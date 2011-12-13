import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.thoughtworks.selenium.DefaultSelenium;

public class Demo {

	public static void main(String[] args) {
		WebDriver wd = new InternetExplorerDriver();
		wd.get("http://localhost:8090/com.caribe.stone.jsf2/");
		System.out.println(wd.findElements(By.tagName("a")).get(1).findElement(By.tagName("h3")).getText());
		System.out.println(wd.findElement(By.className("ch1")).getText());
//		wd.findElements(By.tagName("input")).get(1).sendKeys("thinkdeeply");
		
	}
}
