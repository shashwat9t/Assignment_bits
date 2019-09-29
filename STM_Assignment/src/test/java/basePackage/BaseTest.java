package basePackage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		Report.startExtent();
	}

	@BeforeMethod(enabled = true)
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		Report.getTest().log(LogStatus.INFO, "Opening chrome browser");
		driver = new ChromeDriver();
		driver.get("http://www.flipkart.com");
		Report.getTest().log(LogStatus.INFO, "Navigated to: <b>http://www.flipkart.com</b> ");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@AfterMethod(enabled = true)
	public void afterClass(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS)
			Report.getTest().log(LogStatus.PASS, "Test passed.");
		if (result.getStatus() == ITestResult.FAILURE)
			Report.getTest().log(LogStatus.FAIL, "Test failed.");

		driver.quit();
	}

	@AfterSuite()
	public static void afterSuite() {
		Report.closeReport();
	}
}
