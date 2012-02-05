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
		Throwable throwable = null;
		try {
			invoker.evaluate();
		} catch (Throwable e) {
			throwable = e;
			Class<?> clazz = target.getClass();
			Field wd = clazz.getDeclaredField("wd");
			wd.setAccessible(true);
			WebDriver driver = (WebDriver) wd.get(target);
			if (driver instanceof FirefoxDriver || driver instanceof InternetExplorerDriver) {
				WebDriverClickListener eventListener = new WebDriverClickListener();
				WebDriver newDriver = new EventFiringWebDriver(driver).register(eventListener);
				wd.set(target, newDriver);
				try {
					invoker.evaluate();
				} catch (Throwable e1) {
					throwable = e1;
				}
				System.out.println("rerun test.");
				eventListener.getScreenShot(newDriver);// get last screen shot
				LinkedList<File> list = eventListener.getFileList();
				int i = 0;
				for (File f : list) {
					i++;
					String fileName = target.getClass().getName() + methodName + i + ".png";
					FileUtils.copyFile(f, new File(fileName));
				}
			}
		}
		if (throwable != null) {
			throw throwable;
		}
	}
}