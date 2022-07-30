package com.ejada.coursera_appium_android;



import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;







/**
 * Unit test for simple App.
 */
public class LoginTest_2 
{	
	static AppiumDriver<MobileElement> driver; 
	String userName="seifmostafa_Cjbeis";
	String accessKey="r4Pd8cDnLC4vPxT6xadx";
    /**
     * Rigorous Test :-)
     * @throws MalformedURLException 
     */
	
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
	
    @Test(description="Login with valid input/data ")
    @Severity(SeverityLevel.CRITICAL)
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
    	
    	// pass valid input to the driver
    	email_field.sendKeys("am603311@gmail.com");
    	pass_field.sendKeys("ahmed.78#");
    	
    	driver.findElementById("org.coursera.android:id/create_account").click();
    	
    	
    }
    
    @AfterTest(description="close the session")
    public void TearDown() {
    	System.out.println( "TearDown driver" );
    	driver.quit();
    	
    }
}
