package com.ibm.magentopages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {
	By logOutLoc = By.xpath("//a[text()='Log Out']");
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(linkText="Log Out")
	WebElement logOutEle;
	public MyAccountPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
	}
	
	public String getCurrentTitle() {
		wait.until(ExpectedConditions.presenceOfElementLocated(logOutLoc));
		String actualTitle = driver.getTitle();
		return actualTitle;
	}
	
	public void clickOnLogOut()
	{
		logOutEle.click();
	}

}
