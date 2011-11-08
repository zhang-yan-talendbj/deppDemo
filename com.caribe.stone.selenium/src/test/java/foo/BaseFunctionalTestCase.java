package foo;

import java.util.Properties;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseFunctionalTestCase {
	private static Logger logger = LoggerFactory.getLogger(BaseFunctionalTestCase.class);

	protected final static String BASE_URL = "";

	//Test Groups define
	protected final static String DAILY = "DAILY";
	protected final static String NIGHTLY = "NIGHTLY";


	protected static DataSource dataSource;

	protected static WebDriver driver;

	@BeforeClass
	public static void startAll() throws Exception {
		startJetty();

		fetchDataSource();
		loadDefaultData();

		createWebDriver();

	}

	@AfterClass
	public static void stopAll() throws Exception {
		stopWebDriver();
		cleanDefaultData();
		stopJetty();
	}

	/**
	 * 启动Jetty服务器
	 */
	protected static void startJetty() throws Exception {
		System.out.println("start was...");
	}

	/**
	 * 关闭Jetty服务器.
	 */
	protected static void stopJetty() throws Exception {
	}

	/**
	 * 取出Jetty Server内的DataSource.
	 */
	protected static void fetchDataSource() {
		logger.info("get data source");
//		dataSource = SpringContextHolder.getBean("dataSource");
	}

	/**
	 * 载入默认数据.
	 */
	protected static void loadDefaultData() throws Exception {
		logger.info("load default data.");
//		DbUnitUtils.loadData(dataSource, "/data/default-data.xml");
	}

	/**
	 * 删除默认数据.
	 */
	protected static void cleanDefaultData() throws Exception {
		logger.info("clean default data.");
//		DbUnitUtils.removeData(dataSource, "/data/default-data.xml");
	}

	/**
	 * 创建WebDriver.
	 */
	protected static void createWebDriver() throws Exception {
		Properties props = PropertiesUtils.loadProperties("classpath:/application.test.properties");
		driver = SeleniumUtils.buildDriver(props.getProperty("selenium.driver"));
		logger.info("create WebDriver.");
	}

	/**
	 * 关闭WebDriver
	 */
	protected static void stopWebDriver() {
		logger.info("stop WebDriver");
		driver.close();
	}

	/**
	 * 登录管理员角色.
	 */
	protected static void loginAsAdmin() {
		driver.get(BASE_URL + "/j_spring_security_logout");

		driver.findElement(By.name("j_username")).sendKeys("admin");
		driver.findElement(By.name("j_password")).sendKeys("admin");
//		driver.findElement(By.xpath(Gui.BUTTON_LOGIN)).click();
	}
}