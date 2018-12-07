package com.ibm.magentopages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	@FindBy(id="email")
	WebElement emailEle;
	
	@FindBy(how=How.ID,using="pass")
	WebElement passEle;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginEle;
	
	By errorMsgLoc =By.className("error-msg");
	
	//Click on Forget Your Password HyperLink
	
	@FindBy(xpath="//a[@href='https://account.magento.com/customer/account/forgotpassword/']")
	WebElement ForgotYourPasswordEle;
	
	// Re -enter email id to get the password
   @FindBy(xpath="//*[@name='email']")
   WebElement enterYourEmailEle;
   
   //Submit
   @FindBy(xpath="//button[@type='submit']")
   WebElement submitButtonEle;
   
   //Error message
   By requiredFieldErrorMessage =By.className("validation-advice");
   
   //Error message to give valid mail id
   By enterValidEmailAddress=By.className("validation-advice");
   
	WebDriverWait wait;
	WebDriver driver;
	
	public LoginPage(WebDriver driver,WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}

	public void enterEmailAddress(String userName)
	{
		emailEle.sendKeys(userName);
	}
	
	public void enterPassword(String password)
	{
		passEle.sendKeys(password);
	}
	
	public void clickOnLogin()
	{
		loginEle.click();
	}
	
	public String getPageSourceForInvalidErrorMessage()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(errorMsgLoc));
		
		return driver.getPageSource();
	}
	
	//Click on Forgotyour password hyperLink
	
	public void  ForgotYourPasswordEle()
	
	{ 
		ForgotYourPasswordEle.click();
	}
	
	//Enter your email id
	public void enterYourEmailEle(String enteremailaddress)
	{
		enterYourEmailEle.sendKeys(enteremailaddress);	
	}
	
	// Click on Submit
	public void submitButtonEle()
	{
		submitButtonEle.click();
	}
	
	
	//To get Error message "this is a Required Field.
	public String getPageSourceToRequiredFieldErrorMessage()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(requiredFieldErrorMessage));
		
		return driver.getPageSource();
	}
	
	//To get Error message after Submit button
		public String getPageSourceToEnterValidEmailErrorMessage()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(enterValidEmailAddress));
		
		return driver.getPageSource();
	}
}
