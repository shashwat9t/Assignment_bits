package basePackage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Report {

	static ExtentTest test;

	static ExtentReports report;

	public static void startExtent() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\src\\screenShots\\ExtentReportResults.html");

		test = report.startTest("ExtentDemo");
		test.assignAuthor("Shashwat Chadha--- 2018HT12016", "Shashank Rasotgi --- 2018HT12248",
				"Shashishekar B S --- 2018HT12290");
		test.setDescription("Robust Worstcase boundary value analysis test cases.");

	}

	public static ExtentTest getTest() {
		return test;
	}

	public static void closeReport() {

		report.endTest(test);

		report.flush();

	}

}
