package com.ejada.coursera_appium_android;



import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;





/**
 * Unit test for simple App.
 */
public class LoginTest_1 
{	
	static AppiumDriver<MobileElement> driver; 
	String userName="seifmostafa_Cjbeis";
	String accessKey="r4Pd8cDnLC4vPxT6xadx";
    /**
     * Rigorous Test :-)
     * @throws MalformedURLException 
     */
	
	@BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("DeviceName", "Xiaomi Redmi Note 9")
                        .put("Platform.Version", "10.0")
                        .put("Cloud", "BrowserStack")
                        .build(),System.getProperty("user.dir")
                        + "/allure-results/");
    }
	
	@Step("Xiaomi Redmi Note 9 version 10.0")
	@BeforeTest(description="SetUp appium driver with browserstack cloud")
	public void SetUp() throws Exception{
		System.out.println( "SetUp driver" );
		
		// desiredCapabilities
		
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("deviceName", "Xiaomi Redmi Note 9");
	    caps.setCapability("platformVersion", "10.0");
	    caps.setCapability("automationName", "Appium");
	    caps.setCapability("app", "bs://a41f1acd764c6b2c1b52d9998d64aece8cbcf8e0");
	    caps.setCapability("platformName", "Android");
	    
	   
	    driver=new AppiumDriver<MobileElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);

	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	}
	@Severity(SeverityLevel.CRITICAL)
    @Test(description="Login with invalid input")
    public void Login_test()
    {
    	
    	System.out.println( "Run Tests" );
 		
 		driver.findElementById("org.coursera.android:id/login").click();
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	// header text is displayed
    	String login_message =driver.findElementById("org.coursera.android:id/header").getText();
    	
    	// assert header text is visible and equals to expected message
    	Assert.assertEquals(login_message,"Log into your account" , "Login message is displayed");
    
    	MobileElement email_field=driver.findElementById("org.coursera.android:id/email");
    	MobileElement pass_field=driver.findElementById("org.coursera.android:id/password");
    	
    	email_field.sendKeys("am603311@gmail.com");
    	pass_field.sendKeys("123456");
    	
    	driver.findElementById("org.coursera.android:id/create_account").click();
    	
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	MobileElement failed_login=driver.findElementById("android:id/alertTitle");
    	
    	boolean failed_cond=failed_login.isDisplayed();
    	Assert.assertEquals(failed_cond, true, "failed_login");
    	
    	String failed_message=failed_login.getText();
    	
    	Assert.assertEquals(failed_message, "Sign In Failed", "failed sign in");  	
    	
    }
	
    @AfterTest(description="close the session")
    public void TearDown() {
    	System.out.println( "TearDown driver" );
    	driver.quit();
    	
    }
}
