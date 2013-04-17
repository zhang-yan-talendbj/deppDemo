package com.aiaa.claim;

import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Study {

	private static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.bin", "d:/Program Files/test/Mozilla Firefox9/firefox.exe");
		// driver = new HtmlUnitDriver();
		driver = new FirefoxDriver();
		driver.get("http://www.baicizhan.com/login");
		// driver.quit();baseUrl = "http://www.baicizhan.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("bruce-y.zhang@aia.com");
		driver.findElement(By.name("raw_pwd")).clear();
		driver.findElement(By.name("raw_pwd")).sendKeys("19850930");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.linkText("开始背单词吧")).click();

			execute();
		// driver.findElement(By.cssSelector("#8264 > h3")).click();
		// driver.findElement(By.cssSelector("#17528 > img")).click();
		// driver.findElement(By.cssSelector("h3")).click();
		// driver.findElement(By.cssSelector("#19275 > img")).click();
	}

	private static void execute() {
		String pageSource = driver.getPageSource();

		Document doc = Jsoup.parse(pageSource, "utf-8");

		Element head = doc.head();
		Elements elementsByTag = head.getElementsByTag("script");

		String data = elementsByTag.last().data();
		data = data.replace("//&lt;![CDATA[", "");
		data = data.replace("//<![CDATA[", "");
		data = data.replace("//]]>", "");
		data = data.replace("//]]&gt;", "");
		data = data.replace("var random_word_topics =", "");
		// System.out.println(data.substring(3, data.length() - 3));
	}

}
