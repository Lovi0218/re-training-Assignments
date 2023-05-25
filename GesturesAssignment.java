package recap;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.SessionNotCreatedException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.CommonFunctions;
import utils.ConfigFileReader;
import utils.IOSJsonFileReader;

public class GesturesAssignment {
	
	static IOSDriver driver = CommonFunctions.iosDriver;
	ConfigFileReader cfr = new ConfigFileReader();
	static ExtentTest test;
	static ExtentReports report;

@Test(priority = 2)
public static void scrollPage() throws IOException {
		
		test = CommonFunctions.generateExtentReportforIOS();
		
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.9);
		int endY = (int) (size.height * 0.2);
		int startX = size.width / 2;
		
		TouchAction action = new TouchAction(driver);
		action.longPress(PointOption.point(startX, startY))
		      .moveTo(PointOption.point(startX, endY))
		      .release()
		      .perform();
		
//		test.log(LogStatus.PASS, "scroll page success", "success");
		CommonFunctions.ScreenShotsIOS("Scroll page success", "PASS");

}
@Test(priority =1)
public static void run() {
	IOSJsonFileReader jsonFileReaderIPhone = new IOSJsonFileReader("/Users/collabera/eclipse-workspace/capstoneProj/src/test/java/files/IOSPhoneCapabilities.json");
	
	XCUITestOptions cap = new XCUITestOptions();
	cap.setDeviceName(jsonFileReaderIPhone.getDeviceName());
	cap.setApp(jsonFileReaderIPhone.getAppPath());
	cap.setPlatformVersion(jsonFileReaderIPhone.getPlatformVersion());
	cap.setAutomationName(jsonFileReaderIPhone.getAutomationName()); 
	cap.setUdid(jsonFileReaderIPhone.getUUID());
	try {
		
	driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	}catch (SessionNotCreatedException e) {
		
        System.out.println("Session could not be created. Please check the capabilities and device settings.");
    } catch (Exception e) {
    	
        e.printStackTrace();
    }
}
}