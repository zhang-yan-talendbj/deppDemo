import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Demo {

	public static void main(String[] args) {
		WebDriver wd;
		wd = new InternetExplorerDriver();
		// wd=new FirefoxDriver();
		// wd=new HtmlUnitDriver();
		wd.get("http://www-3a5e5c4f59a:9080/WSsamples/en/index.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(wd.findElement(By.xpath("//head")));
	}
}
