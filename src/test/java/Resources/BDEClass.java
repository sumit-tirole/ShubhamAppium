package Resources;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import java.util.Random;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mongodb.util.Util;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.Utility;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
public class BDEClass extends BaseClass {

	public static String companyNameText;
	public String companyNameInTodayFollowText;
	public String todayFollowupCounter;
	public int initialCounter;
	public int counterAfterAddingLead;
	public Logger log = LogManager.getLogger(BDEClass.class);
	Utility utility ;

	@BeforeMethod
	public void initializeUtility() {
		utility = new Utility(appiumdriver);
	}

	@Test(priority = 1)
	public void getLogin() throws IOException, InterruptedException {

		utility.Login("9999999999");
		
		log.info("BDM Logged in");
		
		Thread.sleep(2000);

		//utility.setAirplaneMode(true);

		//utility.disableLocation();
	}

	@Test(priority = 2)
	public int TodayfollowupCounter() {

		WebElement todayFollowupCount = appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/tx_number\"] "));

		todayFollowupCounter = todayFollowupCount.getText();

		log.info("and today follow up" + todayFollowupCounter);

		return Integer.parseInt(todayFollowupCounter);
	}


	@Test(priority = 3)
	public void addLeadComapnyDeatail() throws InterruptedException {
		initialCounter = TodayfollowupCounter();

		log.info("Initai ciounter before adding lead in todays followup" + initialCounter);


		Thread.sleep(3000);
		utility.tap(400,1930);
		//	////	appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/addLeadTv\"]")).click();
		Random rand = new Random();
		int randInt = rand.nextInt(1000000000);
		String company = "Systango.pvt.ltd" + randInt;
		WebElement comapanyname = 	appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Company Name Here\"]"));
		comapanyname.sendKeys(company);
		companyNameText = comapanyname.getText();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select Country\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"United Arab Emirates\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select State\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Dubai\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select City\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Dubai\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Address Here\"]")).sendKeys("231, south kasba, dubai");

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Pin Code Here\"]")).sendKeys("413007");

		appiumdriver.findElement(By.id("com.ssi.agroworlds:id/primaryNextBtn")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Email ID Here\"]")).sendKeys("skulkarni12@yopmail.com");

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Landline / Phone Number Here\"]")).sendKeys("999999999");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/nextBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Name Here\"]")).sendKeys("shubham");

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Designation Here\"]")).sendKeys("QA Tester");

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter POC Business Number Here\"]")).sendKeys("777777777");

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select Event Type\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Birthday\"]")).click();

		appiumdriver.findElement(By.id("com.ssi.agroworlds:id/eventTypeDateTv")).click();

		appiumdriver.findElement(By.id("android:id/date_picker_header_year")).click();



		/*Dimension dimensions = appiumdriver.manage().window().getSize();
    Double screenHeightStart = dimensions.getHeight() * 0.5;
    int scrollStart = screenHeightStart.intValue();

    Double screenHeightEnd = dimensions.getHeight() * 0.2;
    int scrollEnd = screenHeightEnd.intValue();

		 */
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+2017
				+"\").instance(0))"))).click();

		appiumdriver.findElement(By.xpath("//android.view.View[@content-desc=\"24 March 2017\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		String LeadSource = "Please Select Lead source";

		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+LeadSource+"\").instance(0))"))).click();

		//appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select Lead source\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Browsing data\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select Interest Status\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Contact later\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/followUpDateTv\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/date_picker_header_year\"]")).click();


		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+2026
				+"\").instance(0))"))).click();



		for(int i=0;i<3;i++) {	

			appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Next month\"]")).click();

		}

		appiumdriver.findElement(By.xpath("//android.view.View[@content-desc=\"22 June 2026\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/nextBtn\"]")).click();


	}


	@Test(priority = 4)
	public void productDetails() throws InterruptedException {


		appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.ssi.agroworlds:id/addProductsFab\"]")).click();

		appiumdriver.findElement(By.id("com.ssi.agroworlds:id/selectOriginsDropdownTv")).click();

		WebElement SearchCountry =  appiumdriver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ssi.agroworlds:id/alertSearchEditText\"]"));

		SearchCountry.sendKeys("Russia");

		String Searchorigin = SearchCountry.getText();

		WebElement Country = appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/alertTextView\" and @text=\"RUSSIA\"]"));

		String origin = Country.getText();

		if(Searchorigin.equalsIgnoreCase(origin)) {

			Country.click();

		}


		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		//appiumdriver.navigate().refresh();        //Thread.sleep(5000);

		//
		//WebDriverWait wait = new WebDriverWait(appiumdriver, Duration.ofSeconds(20));

		// By productCategorySpinner = By.id("com.ssi.agroworlds:id/productCategorySpinner");

		// Wait for the element to be clickable and then click it
		// WebElement productCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(productCategorySpinner));
		//productCategoryElement.click();

		// Locate and switch to the frame

		// Example: Scroll to the element using UiAutomator
		// Example: If the Spinner is inside a LinearLayout with resource-id "parentLayoutId"
		//  By productCategorySpinner = By.xpath("//android.widget.Spinner[@resource-id=\"com.ssi.agroworlds:id/productCategorySpinner\"]");
		//  By parentLayout = By.xpath("//android.widget.ScrollView");
		// By childLayout = By.xpath("//android.widget.ScrollView/android.widget.LinearLayout");
		// WebElement parentElement = appiumdriver.findElement(parentLayout);
		//  WebElement childElement = appiumdriver.findElement(childLayout);

		//  parentElement.findElement(childLayout).findElement(productCategorySpinner).click();
		// appiumdriver.switchTo().activeElement().click();
		//appiumdriver.findElement(By.xpath("//android.widget.Spinner[@resource-id=\"com.ssi.agroworlds:id/productCategorySpinner\"]")).click();



		// SearchProduct.sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		utility.tap(480,1800);

		WebElement SearchProduct = appiumdriver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"android:id/search_src_text\"]"));

		SearchProduct.sendKeys("spices");


		String SearchCategory = SearchProduct.getText();

		WebElement Product = appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]"));

		String category = Product.getText();
		//
		if(SearchCategory.equalsIgnoreCase(category)) {

			Product.click();
		}

		Thread.sleep(1000);
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Please Select Products\"]")).click();
		System.out.println("prodcut clicked ");

		String itemName = "CUMIN";
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + itemName + "\").instance(0))")).click();
		Thread.sleep(1000);
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Please Select User\"]")).click();
		Thread.sleep(1000);
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Apple John\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/saveBtn\"]")).click();

		log.info("prodcut added");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/nextBtn\"]")).click();

		log.info("prodcut added");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();


		/* 
       appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Please Select Products\"]")).click();


       String DropdownProdcut = "BLACK PEPPER";

       appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+DropdownProdcut
       		+"\").instance(0))"))).click();

       appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Please Select User\"]")).click();


       WebElement SearchUser = appiumdriver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"android:id/search_src_text\"]"));

       SearchUser.sendKeys("apple");

       String SelectUser = SearchUser.getText();

       WebElement User = appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]"));

       String DropdownUser = User.getText();


       if(SelectUser.equalsIgnoreCase(DropdownUser)) {

    	   User.click();
       }


       appiumdriver.findElement(By.id("com.ssi.agroworlds:id/saveBtn")).click();

       appiumdriver.findElement(By.id("com.ssi.agroworlds:id/nextBtn")).click();

		 */
		counterAfterAddingLead = TodayfollowupCounter();

		System.out.println("Initai ciounter after adding lead in todays followup" + counterAfterAddingLead);


		if (counterAfterAddingLead > initialCounter) {
			System.out.println("Counter has increased after adding a lead.");
		} else {
			System.out.println("Counter did not increase after adding a lead.");
		}

	}




	@Test(priority = 5)
	public void addMeetingForProspect() throws InterruptedException {

		appiumdriver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/background_Img\"])[3]")).click();

		utility.scrollPage();

		String xpathElement = "//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/meeetingTitleTv\"]";

		utility.isElementDisable(xpathElement);

		Thread.sleep(1000);

		utility.isElementClickable(xpathElement);

		Thread.sleep(1000);

		log.info("Meeting button is diabled");

		utility.back();

		Thread.sleep(4000);

		utility.back();

	}




	@Test(priority = 6)
	public void prospect() throws InterruptedException {

		appiumdriver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/background_Img\"])[3]")).click();

		utility.scrollPage();

		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/editFollowupDate\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/followUpDateTv\"]")).click();

		utility.currentDate();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("followup date updated as todays followup");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/remarkSaveBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("convert to potentail");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/yesBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();

		utility.back();

		Thread.sleep(4000);

		utility.back();

		log.info("BDM send request convert to potentail");

	}



	@Test(priority = 7)
	public void todaysFollowup() throws InterruptedException {

		appiumdriver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/background_Img\"])[1]")).click();

		utility.scrollPage();

		Thread.sleep(2000);

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/followUpDateTv\"]")).click();

		for(int i=0; i<3 ;i++) {

			appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Next month\"]")).click();
		}

		appiumdriver.findElement(By.xpath("//android.view.View[@content-desc=\"30 June 2024\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("Added follow up date");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/remarkSaveBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();

		Thread.sleep(2000);
		
		utility.back();

		Thread.sleep(2000);

		log.info("BDE Taken todays follow up");


	}


	@Test(priority=8)		
	public void getLogout() {

		utility.logout();

		log.info("BDE Logged OUT");
	}




	public String getCompanyNameText() {
		return companyNameText;
	}

















}
