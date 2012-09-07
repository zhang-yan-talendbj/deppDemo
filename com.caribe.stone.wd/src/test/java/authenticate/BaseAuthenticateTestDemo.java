package authenticate;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseAuthenticateTestDemo {

	public static void main(String[] args) {
		WebDriver wd;
		AuthenticatedHtmlUnitDriver.setCredentials("tomcat", "tomcat");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		wd = new HtmlUnitDriver(true);
		Cookie cookie = new Cookie("Authorization", "Basic tomcat");
		// var header = "Basic " + $.base64.encode(auth.username + ":" +
		// auth.password);
		// document.cookie = "Authorization: " + header;
		wd.get("http://localhost:8090/");
		JavascriptExecutor js = (JavascriptExecutor) wd;
		System.out.println(js.executeScript("return document.title"));

	}
}
