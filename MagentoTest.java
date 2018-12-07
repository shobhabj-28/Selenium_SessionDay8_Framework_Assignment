package com.ibm.magentotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ibm.magentopages.HomePage;
import com.ibm.magentopages.LoginPage;
import com.ibm.magentopages.MyAccountPage;
import com.ibm.utilities.PropertiesFileHandler;

import jdk.nashorn.internal.runtime.PrototypeObject;

public class MagentoTest {
	WebDriver driver;
	WebDriverWait wait;
	PropertiesFileHandler propFileHandler;
	HashMap<String, String> data;

	@BeforeSuite
	public void preSetForTest() throws IOException {
		String file = "./TestData/magentodata.properties";
		propFileHandler = new PropertiesFileHandler();
		data = propFileHandler.getPropertiesAsMap(file);
	}

	@BeforeMethod
	public void Initialization() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test(priority = 1, testName = "CheckValidCredentials")
	public void positiveCredential() throws IOException {

		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String expectedTitle = data.get("expectedtitle");
		driver.get(url);

		HomePage home = new HomePage(driver);
		home.clickOnMyAccountIcon();

		LoginPage login = new LoginPage(driver, wait);
		login.enterEmailAddress(userName);
		login.enterPassword(password);
		login.clickOnLogin();

		MyAccountPage myAccount = new MyAccountPage(driver, wait);
		String actualTitle = myAccount.getCurrentTitle();

		Assert.assertEquals(actualTitle, expectedTitle);
		myAccount.clickOnLogOut();
	}

	@Test(priority = 2, invocationCount = 2)
	public void negativeCredential() throws IOException {

		try {
			String url = data.get("url");
			String userName = data.get("invalidusername");
			String password = data.get("password");
			String expectedMessage = data.get("invaliderrormessage");
			driver.get(url);

			HomePage home = new HomePage(driver);
			home.clickOnMyAccountIcon();

			LoginPage login = new LoginPage(driver, wait);
			login.enterEmailAddress(userName);
			login.enterPassword(password);
			login.clickOnLogin();

			String pageSource = login.getPageSourceForInvalidErrorMessage();

			Assert.assertTrue(pageSource.contains(expectedMessage), "Assertion on Invalid Login Error Message");

			Reporter.log("Negataive Credentials Passed");
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			Reporter.log("Negataive Credentials Failed");
			//screenshot
			TakesScreenshot ts=(TakesScreenshot) driver;
			File file= ts.getScreenshotAs(OutputType.FILE);
			Date date =new Date();
			String currentDate=date.toString().replace(":", "-");
			FileUtils.copyFile(file, new File("./screenshots/Error_"+currentDate+".png"));
			
			Assert.fail();
		}
	}
	@Test(priority = 3, testName = "CheckingForgotEmail")
	public void forgotYourpassword() throws IOException {
		String url = data.get("url"); 
		driver.get(url);
		
		String enteremailaddress=data.get("enteremailaddress");
		HomePage home = new HomePage(driver);
		home.clickOnMyAccountIcon();

		LoginPage login = new LoginPage(driver, wait);
		login.ForgotYourPasswordEle();
		login.submitButtonEle();
		
		//To get the first Error message- after submit
		String pageSource1 = login.getPageSourceToRequiredFieldErrorMessage();
		Assert.assertTrue(pageSource1.contains("This is a required field."), "Assertion on This is a required field.");
		
		login.enterYourEmailEle(enteremailaddress);
		login.submitButtonEle();
		
		//To get the first Error message- after submit
		String pageSource2 = login.getPageSourceToEnterValidEmailErrorMessage();
		Assert.assertTrue(pageSource2.contains("Please enter a valid email address."), "Assertion on This is a required field.");
				
		
	}
	
}
