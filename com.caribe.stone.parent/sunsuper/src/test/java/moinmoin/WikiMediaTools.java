package moinmoin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WikiMediaTools {
	private static final String HTTP_THINKDEEPLYWIKI_SINAAPP_COM = "http://thinkdeeplywiki.sinaapp.com";
	static int i = 0;
	static WebDriver driver = null;

	public static void main(String[] args) throws IOException {
		Map<String, String> data = MoinMoinExport.getWiki();
		driver = initDriver();
		driver.get(HTTP_THINKDEEPLYWIKI_SINAAPP_COM);
		driver.findElement(By.linkText("登录／创建账户")).click();
		driver.findElement(By.id("wpName1")).clear();
		driver.findElement(By.id("wpName1")).sendKeys("thinkdeeply");
		driver.findElement(By.id("wpPassword1")).clear();
		driver.findElement(By.id("wpPassword1")).sendKeys("19850930");
		driver.findElement(By.id("wpLoginAttempt")).click();

		Set<String> keyset = data.keySet();
		for (String key : keyset) {
			driver.get(HTTP_THINKDEEPLYWIKI_SINAAPP_COM + key);
			String content = data.get(key);
			if(content!=null && content.length()>0){
				
			WebElement findElement = null;
			try {
				findElement = driver.findElement(By.xpath("id('ca-edit')//a"));
			} catch (Exception e1) {
			}
			try {
				findElement = driver.findElement(By.linkText("编辑此页"));
			} catch (Exception e) {
			}
			if (findElement != null) {
				findElement.click();
				driver.findElement(By.id("wpTextbox1")).clear();
				driver.findElement(By.id("wpTextbox1")).sendKeys(content);
				driver.findElement(By.id("wpSave")).click();
			}
			}
		}

		System.out.println("success");
	}

	private static WebDriver initDriver() {
		WebDriver driver = null;
		// driver = new HtmlUnitDriver();
		System.setProperty("webdriver.firefox.bin", "d:/Program Files/test/Mozilla Firefox9/firefox.exe");

		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("selenium");
		if (profile != null) {
			driver = new FirefoxDriver(profile);
		} else {
			driver = new FirefoxDriver();
		}
		return driver;
	}
}
