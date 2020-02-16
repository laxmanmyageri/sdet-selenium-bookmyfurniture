package utils;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import common.CommonConstant;

public class Reporting {
	Properties prop;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest logger;

	public void setExtentReport() {

		String reportName = "Automation_Test-Report.html";

		htmlReporter = new ExtentHtmlReporter(CommonConstant.reportPath + reportName);
		htmlReporter.config().setDocumentTitle("Mindtree SDET Mind");
		htmlReporter.config().setReportName("Automation Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("Tester Name", "Laxman");
	}

	public void endExtentReport() {
		extent.flush();
	}
}
