package cc.openhome;

import java.io.File;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverClickListener implements WebDriverEventListener {
	private LinkedList<File> fileList = new LinkedList<File>();

	public LinkedList<File> getFileList() {
		return fileList;
	}
	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void beforeClickOn(WebElement e, WebDriver wd) {
		if (e.getTagName().equals("input")) {
			getScreenShot(wd);
		}
	}

	public void getScreenShot(WebDriver wd) {
		File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		fileList.add(f);
	}
	
	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
	}
}