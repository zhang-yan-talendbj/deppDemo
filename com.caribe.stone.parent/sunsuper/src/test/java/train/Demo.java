package train;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Demo {

	public static void main(String[] args) {
		String string = "http://www.12306.cn/mormhweb/kyfw/";
		WebDriver wd = new InternetExplorerDriver();
		wd.get(string);
		System.out.println(wd.getPageSource());
	}
}
