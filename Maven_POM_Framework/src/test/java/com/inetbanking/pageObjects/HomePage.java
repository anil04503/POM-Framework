package com.inetbanking.pageObjects;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver ldriver;
	
	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
		
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnkLogout;
	
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
	public boolean logoutPresent()
	{
		try
		{
		lnkLogout.isDisplayed();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	
}
