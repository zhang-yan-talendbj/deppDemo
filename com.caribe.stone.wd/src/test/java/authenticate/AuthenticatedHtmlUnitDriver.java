package authenticate;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;

public class AuthenticatedHtmlUnitDriver extends HtmlUnitDriver {

	public static void setCredentials(String username, String password) {
		USERNAME = username;
		PASSWORD = password;
	}
	
	private static String USERNAME;
	private static String PASSWORD;

	public AuthenticatedHtmlUnitDriver() {
	}

	@Override
	protected WebClient newWebClient(BrowserVersion browserVersion) {		
		System.out.println("Logging in with: [username:" + USERNAME + "]");
		WebClient client = super.newWebClient(browserVersion);
		DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
		provider.addCredentials(USERNAME, PASSWORD);
		client.setCredentialsProvider(provider);
		return client;
	}
			
}