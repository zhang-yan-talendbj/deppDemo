import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.thoughtworks.selenium.DefaultSelenium;

public class Demo {

	public static void main(String[] args) {
		WebDriver wd = new InternetExplorerDriver();
		wd.get("http://www.google.com.hk/");
		System.out.println(wd.findElement(By.xpath("//input[@name='btnI']")).getTagName());
	}
}
