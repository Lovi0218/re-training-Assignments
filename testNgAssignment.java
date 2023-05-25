package recap;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class testNgAssignment {
    public String baseUrl = "https://www.lazada.com.ph/";
    public WebDriver driver;
    static com.aventstack.extentreports.ExtentTest test;
    static com.aventstack.extentreports.ExtentReports report;
    static ExtentHtmlReporter htmlReporter;
    
    @BeforeSuite
    public static ExtentTest beforeSuite() {
    	System.out.println("this is BeforeSuite");
    	htmlReporter = new ExtentHtmlReporter("reports.html");
        report = new ExtentReports();
        test = report.createTest("captone_test");
        return test;
    }

    @BeforeMethod
    public void launch() {
    	System.out.println("This is BeforeMethod");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void validateTitle() {
        System.out.println("inside test");
        System.out.println(driver.getTitle());
        String actualTitle = driver.getTitle();
        String expectedTitle = "lazada";

        Assert.assertFalse(actualTitle.contains(expectedTitle));
        test.log(Status.PASS, "title contains lazada");
    }

    @AfterMethod
    public void afterMethod() {
    	System.out.println("This is AfterMethod");
        driver.close();  
    }
    @AfterSuite
    public void afterSuite() {
    	System.out.println("This is AfterSuite");
    	if (report != null) {
            report.flush();
    }

}
}
