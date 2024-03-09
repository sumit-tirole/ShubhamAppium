package extentManager;

import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager
{
	 public static ExtentSparkReporter htmlReporter;   // repsosnible for look
	 public static ExtentReports extent; //  Resposnisble for entries
	 public static ExtentTest test; // Respossible for test status pass/fail
	 
	 public static void setup() throws UnknownHostException
	 {
		 
		  htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
		  htmlReporter.config().setDocumentTitle("Automation Report");
		  htmlReporter.config().setReportName("Functional Report");
		  htmlReporter.config().setTheme(Theme.STANDARD);

		  extent = new ExtentReports();
		  extent.attachReporter(htmlReporter);
		  String hostname = java.net.InetAddress.getLocalHost().getHostName();
		  extent.setSystemInfo("hostname", hostname);

		  // Instead of hardcoding, you might also get the OS information programmatically
		  String osName = System.getProperty("os.name");
		  extent.setSystemInfo("os", osName);

		  // Setting the tester name, which is fine as is
		  extent.setSystemInfo("testername", "Shubham");

		  // Additionally, consider adding more relevant system information
		  extent.setSystemInfo("Appium Version", "");
		  extent.setSystemInfo("Platform Name", "Android");
		  extent.setSystemInfo("Platform Version", "13");
		 }
	 
	 public static void endReport()
	 {
		  extent.flush();
	 }

}