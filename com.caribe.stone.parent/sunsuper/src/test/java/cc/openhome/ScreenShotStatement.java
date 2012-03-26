package cc.openhome;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenShotStatement extends Statement {
	private Statement invoker;
	private Object target;
	private String methodName;

	public ScreenShotStatement(Statement invoker, Object target, String methodName) {
		this.invoker = invoker;
		this.target = target;
		this.methodName = methodName;
	}

	@Override
	public void evaluate() throws Throwable {
		invoker.evaluate();
	}
}