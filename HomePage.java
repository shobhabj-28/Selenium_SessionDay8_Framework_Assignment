package com.ibm.magentopages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ibm.magentotest.MagentoTest;


public class HomePage extends MagentoTest {
	
	
	@FindBy(xpath="//span[text()='Account']/ancestor::a")
	WebElement myAccountEle;
	
	
	@FindAll(@FindBy(tagName="a"))
	List<WebElement> linksEle;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnMyAccountIcon()
	{
		myAccountEle.click();
	}
	
	public int noOfLinks()
	{
		return linksEle.size();
	}
}
