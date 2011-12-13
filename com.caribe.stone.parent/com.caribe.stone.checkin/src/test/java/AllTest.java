import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class AllTest {

	public static void main(String[] args) {
		String url = "http://localhost:8090/com.caribe.stone.jsf2/";
		WebDriver driver = new InternetExplorerDriver();
		driver.get(url);
		System.out.println(driver.findElement(By.tagName("a")));
	}
}
