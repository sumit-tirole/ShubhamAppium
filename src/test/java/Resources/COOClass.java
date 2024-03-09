package Resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utility.Utility;

public class COOClass extends BaseClass {
	
	public Logger log = LogManager.getLogger(COOClass.class);
	Utility utility ;

	@BeforeMethod
	public void initializeUtility() {
		utility = new Utility(appiumdriver);
	}

	@Test(priority = 1)
	public void getLogin() {

		utility.Login("2000000000");
		
		log.info("COO Logged IN");
		
		appiumdriver.findElement(By.xpath("//android.widget.TextView[@text=\"Business Development Module\"]")).click();

	}

	
	@Test(priority = 2)
	public void clientConversionRequestAccept() throws InterruptedException {

		String requestName = "Conversion Request";
		appiumdriver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + requestName + "\").instance(0))")).click();
		Thread.sleep(1000);
		
		utility.scrollPage();
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/approveBtn\"]")).click();
		
		appiumdriver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter Here\"]")).sendKeys("Request to Clinet is approved from COO");
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/yesBtn\"]")).click();
		
		appiumdriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ssi.agroworlds:id/okayBtn\"]")).click();
		
		appiumdriver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ssi.agroworlds:id/backImgView\"]")).click();
		
		log.info("Request to Clinet is approved from COO");

	}
	
	
	@Test(priority = 3)
	public void getLogout() {

		utility.logout();
		
		log.info("COO Logged OUT");
	}


}
