package utility;

import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.HasNetworkConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.NoSuchElementException;
import Resources.BDEClass;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import logger.Log;


public class Utility {
	BDEClass testInstance = new BDEClass();
	public String companyName = testInstance.getCompanyNameText();


	public AndroidDriver appiumdriver;

	public Utility(AndroidDriver appiumdriver) {

		this.appiumdriver=appiumdriver;
	}
	
		
	public void Login(String MobileNumber) {

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ssi.agroworlds:id/phoneEt\"]")).sendKeys(MobileNumber);
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/loginBtn\"]")).click();
		
		appiumdriver.findElement(By.id("com.ssi.agroworlds:id/otp_view")).sendKeys("1234");
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/verifyBtn\"]")).click();
	}
	
	
	public void runAppInBackground() {
		
		appiumdriver.runAppInBackground(Duration.ofSeconds(5));
	}
	
	
	public void lockUnlockDevice() throws InterruptedException {
		
		((AndroidDriver) appiumdriver).lockDevice();
		boolean isLocked = ((AndroidDriver) appiumdriver).isDeviceLocked();
		// If locked, then unlock
		
		Thread.sleep(5000);
		
		if(isLocked) {
		    ((AndroidDriver) appiumdriver).unlockDevice();
		}
	}
	
	
	
	public boolean manageDataConnection() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("adb shell dumpsys connectivity | grep \"Data connection\"");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean isDataEnabled = false;
        
        while ((line = reader.readLine()) != null) {
            if (line.contains("Data connection: ACTIVATED") || line.contains("state: CONNECTED")) {
                isDataEnabled = true;
                break;
            }
        }
        
        if (!isDataEnabled) {
            Runtime.getRuntime().exec("adb shell svc data enable");
            Thread.sleep(5000);  // Wait for the data connection to establish
            return true;  // Data was off and has been enabled
        }
        return false;  // Data was already on
	}
	
	
	public void enableWifiIfDataIsOff() throws InterruptedException {
	    HasNetworkConnection connection = this.appiumdriver;
	    ConnectionState state = connection.getConnection();

	    // Check if mobile data is off (not checking Wi-Fi state here)
	    if (!state.isDataEnabled()) {
	        // If data is off, enable Wi-Fi
	    	this.appiumdriver.setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
	        
	        Thread.sleep(10000);
	    }
	}
	
	
	
	public void setAirplaneMode(boolean enabled) throws IOException {
	    // Enable airplane mode (1 for on, 0 for off)
	    int state = enabled ? 1 : 0;
	    
	    // Setting airplane mode state
	    Runtime.getRuntime().exec("adb shell settings put global airplane_mode_on " + state);
	    
	    // Broadcasting an intent to update the airplane mode state
	    Runtime.getRuntime().exec("adb shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state " + (enabled ? "true" : "false"));
	}

	
	
	
	public void setMockLocation(AndroidDriver appiumdriver, double latitude, double longitude) {
		appiumdriver.setLocation(new Location(latitude, longitude, 0));
	}
	
	public void enableLocation() throws IOException {
	    // Turn on location
	    Runtime.getRuntime().exec("adb shell settings put secure location_mode 3");
	}

	public void disableLocation() throws IOException {
	    // Turn off location
	    Runtime.getRuntime().exec("adb shell settings put secure location_mode 0");
	}

	
	
	
	public void allowNotification() {
		
		String allowButtonId = "com.android.packageinstaller:id/permission_allow_button";
		// Or use XPath, e.g., String allowButtonXPath = "//android.widget.Button[@text='ALLOW']";

		// Check if the Allow button is present and click it
		List<WebElement> allowButtons = appiumdriver.findElements(By.xpath(allowButtonId));
		if (!allowButtons.isEmpty()) {
		    allowButtons.get(0).click();
		}
	}
	
	
	
	public void tap(int x,int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		((AppiumDriver) appiumdriver).perform(Arrays.asList(tap));
	}

	
	
	public void currentDate() {

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		String formattedDate = currentDate.format(formatter);

		String xpath = "//android.view.View[@content-desc=\"" + formattedDate + "\"]";

		WebElement dateElement = appiumdriver.findElement(By.xpath(xpath));
		dateElement.click();
	}
	
	
	public void currentTime() throws InterruptedException {
	    // Get current minutes
	    LocalTime currentTime = LocalTime.now();
	    int currentHour = currentTime.getHour();
	    int currentMinutes = currentTime.getMinute();
 
	    WebElement minuteInputField = appiumdriver.findElement(By.xpath("//android.widget.EditText[@resource-id='android:id/input_minute']"));
	
	    WebElement hourInputField = appiumdriver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"android:id/input_hour\"]"));

	    hourInputField.clear();
		Thread.sleep(1000);

	    hourInputField.sendKeys(String.valueOf(currentHour));
	    
	    minuteInputField.clear();
		Thread.sleep(1000);

	    minuteInputField.sendKeys(String.valueOf(currentMinutes));
	}

	
	 public String currentDateAndTime(){
		 
	        // Get the current date and time
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        
	        // Define the pattern to format the date and time
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' hh:mm a");
	        
	        // Format the current date and time according to the specified pattern
	        String formattedDateTime = currentDateTime.format(formatter);
	        
	        // Output the formatted date and time
	        System.out.println(formattedDateTime);
			return formattedDateTime;
	    }
	
	 
	public void isElementDisable(String elementXpath) {
		 
		WebElement element = appiumdriver.findElement(By.xpath(elementXpath));
		
		if(element.isDisplayed()) {

		Log.info("Element is enabled");
		
		}
		
		else {
		
			Log.info("Element is not enabled ");	
		}
		 	 
	 }
	
	
	
	public void back() {
		
       appiumdriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
       
    }
	
	
	
	public void startAppInForegrond() {
		
		appiumdriver.activateApp( "com.ssi.agroworlds");

	}
	
	
	
	 public void isElementClickable(String elementXpath) {
		 
		 WebElement Clickableelement = appiumdriver.findElement(By.xpath(elementXpath));
		 
		 Clickableelement.click();
		 
		 if(Clickableelement.isSelected()) {
			 
		     Log.info("Element is cliakble");
		
		 }
		 
		 else {
			 
			 Log.info("Element is not cliakble");
		 }
		
     }
		
 
		 
	 
	
	
	
	public void scrollPage() throws InterruptedException {
		System.out.println("and comapny name is" + companyName);
		int maxScrolls = 10; // Maximum number of scrolls
		boolean elementClicked = false;
		for (int i = 0; i < maxScrolls && !elementClicked; i++) {
			// Refetch elements after each scroll to avoid stale elements
			List<WebElement> elements = appiumdriver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.ssi.agroworlds:id/leadNameTv']"));
			for (WebElement element : elements) {
				Thread.sleep(1000);
				String elementText = element.getText();
				System.out.println(elementText);

				if (elementText.equalsIgnoreCase(companyName)) {
					try {
						element.click();
						elementClicked = true;
						break; // Breaks the inner loop
					} catch (StaleElementReferenceException e) {
						// Element has become stale, refetch the element or list of elements
						System.out.println("Encountered stale element, retrying...");
						// Optionally, refetch the specific element or continue to the next iteration to refetch all elements
					}
				}
			}

			if (!elementClicked) {
				appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward()"
						));
				try {
					Thread.sleep(1000); // Wait for 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
	
	public void scrollUp() {
		
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollBackward()"));
	}
	
	public void scrollDown() {
		
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward()"));
	}
	
	
	public void logout(){

		scrollUp();
		
		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/menuOption\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/logout\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/yesBtn\"]")).click();


	}
	
	

}
