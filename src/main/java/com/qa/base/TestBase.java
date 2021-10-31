package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.qa.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import com.qa.util.DataUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static WebDriver driver;
	private static Logger log = Logger.getLogger(TestBase.class);
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest extentTest;


	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Extent.html", true);
		extent.addSystemInfo("Host Name", "LennoxPros");
		extent.addSystemInfo("User Name", "lenproautomation8");
		extent.addSystemInfo("Environment", "QA");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@BeforeMethod
	public void setup() {
		initialization();
	}

	@DataProvider()
	public Object[][] getData(Method m) {
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "/src/main/java/com/qa/testdata/Data.xlsx");
		switch (m.getName()) {
			case "validateCompressorDetails":
				return DataUtil.getData("ValidateCompressorDetailsTest", xls);
		}
		return null;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

			String screenshotPath = DataUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html extent
		// report
		driver.quit();
	}

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/chrome-driver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/gecko-driver/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(DataUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DataUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		log.info("Entering application url");
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
	}

	public static void waitUntilElementDisplayed(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilElementPresent(By by){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
}
