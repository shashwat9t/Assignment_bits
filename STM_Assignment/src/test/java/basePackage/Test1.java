package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class Test1 extends BaseTest {

	@Test(dataProvider = "getData")
	public void test1(String userName, String password) throws IOException {
		driver.findElements(By.xpath("//input[@type='text']")).get(1).sendKeys(userName);
		Report.getTest().log(LogStatus.INFO, "Entered username as : " + userName);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		Report.getTest().log(LogStatus.INFO, "Entered password as : " + password);

		driver.findElements(By.xpath("//button[@type='submit']")).get(1).click();
		Report.getTest().log(LogStatus.INFO, "Clicked on Login button");

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ZAtlA-']/span")).getText(),
				"Please enter valid Email ID/Mobile number");
		Report.getTest().log(LogStatus.INFO,
				"Asserted the error message to be : <b>Please enter valid Email ID/Mobile number</b>");
		Report.getTest().log(LogStatus.PASS, Report.getTest().addScreenCapture(Test1.capture(driver)));
		;

	}

	@DataProvider(name = "getData")
	public Object[][] getDataFromDataprovider() throws IOException {
		String userDir = System.getProperty("user.dir");
		System.out.println(userDir);
		File file = new File(userDir + "\\STM_BVA_AS.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet hs = workbook.getSheetAt(0);
		int last = hs.getLastRowNum();
		int start = 5;
		Object[][] dp = new Object[last - start][2];
		int i = 0;
		for (; start < last; start++) {
			String user = hs.getRow(start).getCell(8).getStringCellValue();
			String password = hs.getRow(start).getCell(9).getStringCellValue();
			dp[i][0] = user;
			dp[i][1] = password;
			i++;
		}
		return dp;
	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Long timeStamp = System.currentTimeMillis();
		File Dest = new File("src/screenShots/" + timeStamp + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileHandler.copy(scrFile, Dest);
		return timeStamp+".png";
	}

}
