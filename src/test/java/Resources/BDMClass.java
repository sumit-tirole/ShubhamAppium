package Resources;

import java.util.List;

import javax.lang.model.element.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utility.Utility;

public class BDMClass extends BaseClass{
	public String MeetingTitleOnCreatingMeeting;
	public String MeetingTitleAftercreatingMeeting;
	public String dateAndTime;
	public Logger log = LogManager.getLogger(BDMClass.class);
	BDEClass testInstance = new BDEClass();
	public String companyNames = testInstance.getCompanyNameText();
	Utility utility ;

	@BeforeMethod
	public void initializeUtility() {
		utility = new Utility(appiumdriver);
	}

	@Test(priority = 1)
	public void getLogin() throws InterruptedException {

		utility.Login("3000000000");
		
		log.info("BDM Logged in");
		
		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void potentialConversionRequestAccept() throws InterruptedException {

		String requestName = "Conversion Request";
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + requestName + "\").instance(0))")).click();
		Thread.sleep(1000);

		utility.scrollPage();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/approveBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/followUpDateTv\"]")).click();

		utility.currentDate();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("converted to potentail from BDM");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/yesBtn\"]")).click();

		Thread.sleep(2000);

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();

		utility.back();

		Thread.sleep(2000);
		
		utility.back();

	}

	
	@Test(priority = 3)
	public void  addMeetingForPotential() throws InterruptedException {
		
		utility.scrollUp();
		
		Thread.sleep(1000);
		
		appiumdriver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/background_Img\"])[6]")).click();

		Thread.sleep(2000);

		log.info("potentail has clciked");
		
		utility.scrollPage();
		
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/meeetingTitleTv\"]")).click();
		
		Thread.sleep(4000);
		
//		int startX = 870;
//		int startY = 2082;
//		int endX = 1024;
//		int endY = 2202;
//
//		// Calculate the center of the element
//		int centerX = (startX + endX) / 2;
//		int centerY = (startY + endY) / 2;

		
		//utility.tap(950, endY);
		
		//appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.ssi.agroworlds:id/mainFab\"]")).click();
		
		appiumdriver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout")).click();
		
		Thread.sleep(3000);
		
		String createMettingXpath = "//android.widget.ImageButton[@resource-id=\"com.ssi.agroworlds:id/createMeetingFab\"]";
		
		utility.isElementDisable(createMettingXpath);
		
		utility.isElementClickable(createMettingXpath);
		
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\" and @text=\"Please Select Meeting Mode\"]")).click();
		
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\" and @text=\"Video call\"]")).click();

		WebElement meetingTitle = appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Title Here\"]"));
		
		meetingTitle.sendKeys("Meeting hase created for" + companyNames);
		
		MeetingTitleOnCreatingMeeting = meetingTitle.getText();
		
		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/calenderImgView\"]")).click();
		
		for(int i=0;i<3;i++) {	

			appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Next month\"]")).click();

		}

		appiumdriver.findElement(By.xpath("//android.view.View[@content-desc=\"22 June 2026\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/meetingTimeTv\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Switch to text input mode for the time input.\"]")).click();
		
		Thread.sleep(2000);
		
		utility.currentTime();
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Address/Link Here\"]")).sendKeys("Meeting placed in Indore");

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Agenda Here\"]")).sendKeys("To convert potential lead into client");
		
		appiumdriver.findElement(By.xpath("//android.widget.Spinner[@resource-id=\"com.ssi.agroworlds:id/reportingManagerSpinner\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Aayushi Procurement IRM\"]")).click();

		utility.scrollDown();
		
		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("Meeting should done");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/scheduleMeetingBtn\"]")).click();

		Thread.sleep(2000);
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();
		
		Thread.sleep(2000);
		
		WebElement meetingtitle = appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\"]"));
		
		MeetingTitleAftercreatingMeeting = meetingtitle.getText();

		Assert.assertEquals(MeetingTitleOnCreatingMeeting, MeetingTitleOnCreatingMeeting, "Meeting Title validation Done");
		
		WebElement meetingFlag = appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/meetingEscalationFlagImgView\"]"));
		
		Thread.sleep(1000);
		
		
		if(meetingFlag.isDisplayed()) {
			
			log.info("Meeting has escalated before scheduled time" );
		}

		utility.back();
		
		Thread.sleep(2000);
			
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@text=\"Activity Logs\"]")).click();
		
		Thread.sleep(1000);
		
		dateAndTime = utility.currentDateAndTime();
		
		String meetingXpath = "//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/logTitleTv\" and @text=\"A new meeting with "+companyNames+" has been scheduled for "+MeetingTitleOnCreatingMeeting+" on "+dateAndTime+"\"]";
				
		WebElement meetingCreatedElement = appiumdriver.findElement(By.xpath(meetingXpath));
		
		String meetingCreatedElements = meetingCreatedElement.getText();
		
		List<WebElement> ActivityLogPage =  appiumdriver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ssi.agroworlds:id/recyclView\"]"));

		
        for(WebElement meetingInActivityLog : ActivityLogPage) {
        	
        	String meetingInActivityLogs = meetingInActivityLog.getText();
        	
        	if (meetingInActivityLogs.contains(meetingCreatedElements)) {
        		
        		log.info("Meeting creations are coorectly displayed in activity logs ");
        	}
        	
        	else {
        		
        		log.info("Meeting creations not display in activity logs");

        	}
        }
		
		utility.back();
		
		Thread.sleep(3000);
		
		utility.back();
		
		Thread.sleep(3000);
		
		utility.back();

		
	}
	
	@Test(priority = 4)
	public void convertToClient() throws InterruptedException {

		String leadState = "Potentials";
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + leadState + "\").instance(0))")).click();
		Thread.sleep(2000);

		log.info("potentail has clciked");
		
		utility.scrollPage();
		
		log.info("BDM gone though scrollpage");

		Thread.sleep(2000);
			
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/titleTv\"]")).click();

		Thread.sleep(1000);

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ssi.agroworlds:id/nightModeTv\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Anshita Trader\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("please convert lead from potential to client");

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/yesBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();

		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/backImgView\"]")).click();

		Thread.sleep(2000);
		
		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/backImgView\"]")).click();
		
		log.info("BDM Convrted lead from potential to client");
		 		
	}

	@Test(priority = 5)
	public void getLogout() {

		utility.logout();
		
		log.info("BDM Logged out");
	}



}
