package baseUtil;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.Login;
import reports.ExtentReportManager;
import reports.TestManager;
import utils.Configuration;

import static utils.IConstant.*;
import common.CommonAction;

public class BaseClass {

	public WebDriver driver;
	public HomePage homePage;
	Configuration configuration;
	ExtentReports extentReports;
	ExtentTest extentTest;
	public Login login;
	

	@BeforeSuite
	public void initialReporting() {
		extentReports = ExtentReportManager.initialReports();
	}

	// newly added
	@BeforeMethod
	public void initialTest(Method method) {
		extentTest = TestManager.createTest(extentReports, method.getName());
		extentTest.assignCategory(method.getDeclaringClass().getName());
	}

	@BeforeMethod
	public void setup() {
		configuration = new Configuration();
		initDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(configuration.getProperties(URL));
		//long pageLoadWait = Long.parseLong(configuration.getProperties(PAGELOAD_WAIT));
		long pageLoadWait = Long.parseLong(configuration.getProperties(PAGELOAD_WAIT));
		long implicitlyWait = Long.parseLong(configuration.getProperties(IMPLICITLY_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(implicitlyWait));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(pageLoadWait));
		
		initClass();
	}

	public void initDriver() {
		String browserName = configuration.getProperties(BROWSER);
		switch (browserName) {
		case CHROME:
			System.setProperty("webDriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case EDGE:
		//case EDGE:
			System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}

	}

	public void initClass() {
		homePage = new HomePage(driver);
		// configuration = new Configuration();
		login = new Login(driver);
	}

	@AfterMethod
	public void tearUp() {
		 driver.quit();
	}

	@AfterMethod
	public void afterEachTest(Method method, ITestResult result) {
		for (String group : result.getMethod().getGroups()) {
			extentTest.assignCategory(group);
		}

		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test PASSED");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(CommonAction.getSreenShot(method.getName(), driver));
			extentTest.log(Status.FAIL, "Test FAILED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test SKIPPED");
		}
	}

	@AfterSuite
	public void publishReport() {
		extentReports.flush();
	}

	// create config.properties file in src/main/resoures
	// create utils package
	// Inside utils, create enum Constant, Interface IConstant, Configuration class
	// Bring changes in Base class
	// static import necessary for ---> import static utils.IConstant.*

}