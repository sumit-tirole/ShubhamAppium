package Resources;

import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import extentManager.ExtentManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import screenRecording.MobileScreenRecordingUtil;
import utility.Utility;

public class BaseClass {

	public static AndroidDriver appiumdriver;
	public URL url;
	Utility utility;

	

	private static String executeShellCommand(String command) throws IOException {
        StringBuilder output = new StringBuilder();
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }

    private static void printAppSizes(String packageName) throws IOException {
        String command = "adb shell dumpsys package " + packageName + " | grep -E 'DataSize|CacheSize|CodeSize|ExternalDataSize'";
        String output = executeShellCommand(command);
        System.out.println(output);
    }
	
	
	@BeforeSuite
	public void setUp() throws IOException, InterruptedException {
		
	
		
		   // Enable WiFi using ADB command
//	    String enableWiFiCommand = "adb shell svc wifi enable";
//	    try {
//	        Runtime.getRuntime().exec(enableWiFiCommand);
//	        System.out.println("WiFi enabled successfully.");
//	        Thread.sleep(20000); // Wait a bit for WiFi to be fully enabled
//	    } catch (IOException e) {
//	        System.err.println("Failed to enable WiFi: " + e.getMessage());
//	    }
//		
	    String appPackage = "com.ssi.agroworlds"; 

        // Print sizes before clearing app data
        System.out.println("Before clearing app data:");
        printAppSizes(appPackage);

        // Clear app data
        Runtime.getRuntime().exec("adb shell pm clear " + appPackage);
       
        Thread.sleep(3000);

        // Print sizes after clearing app data
        System.out.println("After clearing app data:");
        printAppSizes(appPackage);
		
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("deviceName", "vivo Y36");
		caps.setCapability("udid", "10BDB70PG4000XX");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "13");
		caps.setCapability("automationName","UiAutomator2");
		caps.setCapability("autoGrantPermissions", true);
		caps.setCapability("appPackage", "com.ssi.agroworlds");
		caps.setCapability("appActivity", "com.ssi.agroworlds.view.ui.login.ActivityLogin");


		url = new URL(" http://192.168.3.5:4723/"); // // http://192.168.3.212:4723/ http://192.168.3.71:4723/

	    appiumdriver = new AndroidDriver(url,caps);
		
		utility = new Utility(appiumdriver);
		 
		utility.manageDataConnection();
		
	
		
		//utility.enableWifiIfDataIsOff();

		appiumdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		MobileScreenRecordingUtil.startRecordingScreen(appiumdriver);	
		 
	} 

	
	@BeforeTest
	public void ExtentReport() throws UnknownHostException 
	{
		ExtentManager.setup();
	}


	@AfterTest
	public void endReport()
	{
		ExtentManager.endReport();
	}
	
	//Screenshot method is used when script is fails.
	public static String screenShot(AndroidDriver appiumdriver,String filename)
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) appiumdriver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		File finalDestination= new File(destination);
		try 
		{
			FileUtils.copyFile(source, finalDestination);
		} 
		catch (Exception e)
		{
			e.getMessage();
		}
		return destination;
	}
	
	@AfterSuite
    public void globalTeardown() {
        // Stop recording and save the file with a timestamp
        String filePath = "./test-output/video_" + System.currentTimeMillis() + ".mp4";
        MobileScreenRecordingUtil.stopRecordingScreen(appiumdriver, filePath);

        
    }

}
