package com.aiaa.claim;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Study {

	private static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.bin", "d:/Program Files/test/Mozilla Firefox9/firefox.exe");
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

		
			
		for (int i = 0; i < 100; i++) {
			execute();
		}
		// driver.findElement(By.cssSelector("#8264 > h3")).click();
		// driver.findElement(By.cssSelector("#17528 > img")).click();
		// driver.findElement(By.cssSelector("h3")).click();
		// driver.findElement(By.cssSelector("#19275 > img")).click();
	}

	private static void execute() {
		String pageSource = driver.getPageSource();
//		System.out.println(pageSource);
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return driver.findElement(By.className("main-loading-wrapper")).getAttribute("style").equals("display: none;");
			}
		});
		
		System.out.println(driver.findElement(By.className("main-loading-wrapper")).getAttribute("style"));
		String id = driver.findElement(By.id("current_word_id")).getAttribute("value");
		System.out.println(id);
		String attribute = driver.findElement(By.className("pass-word")).getAttribute("class");
		String word = driver.findElement(By.className("highlight-single-word")).getText();
		
//		driver.findElement(By.linkText("继续做题")).click();
//		String attribute = driver.findElement(By.id("current_word_id")).getAttribute("value");

		
		Document doc = Jsoup.parse(pageSource, "utf-8");

		Element head = doc.head();
		Elements elementsByTag = head.getElementsByTag("script");
		Elements select = doc.select("span[class^=highlight]");
//		String word = select.get(0).text();
		
		String data = elementsByTag.last().data();
		data = data.replace("//&lt;![CDATA[", "");
		data = data.replace("//<![CDATA[", "");
		data = data.replace("//]]>", "");
		data = data.replace("//]]&gt;", "");
		data = data.replace("var random_word_topics =", "");
		// System.out.println(data.substring(3, data.length() - 3));
		JSONArray obj = JSONArray.fromObject(data.substring(3, data.length() - 3));
		for (int i = 0; i < obj.size(); i++) {
			Map x = (Map) obj.get(i);
			String x2 = (String) x.get("word");
			if (x2.equals(word)) {
				System.out.println(x);
				System.out.println(x.get("id"));
				WebElement answer = driver.findElement(By.id(String.valueOf(x.get("id"))));
				answer.click();
				return;
			}
		}
	}

}
