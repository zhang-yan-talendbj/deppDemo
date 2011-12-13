import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example  {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver ;
        driver = new InternetExplorerDriver();
//        driver=new HtmlUnitDriver();

//        driver.get(url);
//        WebElement staffId = driver.findElement(By.name("staff_id"));
//        System.out.println(staffId);
        // And now use this to visit Google
        driver.get("http://localhost:8090/com.caribe.stone.jsf2/");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        driver.findElement(By.tagName("a")).click();

        WebElement findElement = driver.findElement(By.xpath("//input[@type='text']"));
		findElement.sendKeys("bruce");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		System.out.println(driver.findElement(By.tagName("h3")).getText());
        
        // Enter something to search for

        // Now submit the form. WebDriver will find the form for us from the element

        // Check the title of the page
//        System.out.println("Page title is: " + driver.getTitle());
//        
//        // Google's search is rendered dynamically with JavaScript.
//        // Wait for the page to load, timeout after 10 seconds
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().startsWith("cheese!");
//            }
//        });
//
//        // Should see: "cheese! - Google Search"
//        System.out.println("Page title is: " + driver.getTitle());
//        
        //Close the browser
//        driver.quit();
    }
}