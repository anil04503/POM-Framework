package com.inetbanking.testCases;


import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.NALExcelXLSReader;


public class TC_LoginTest_001 extends BaseClass
{
	NALExcelXLSReader reader = new NALExcelXLSReader(System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx");
	 SoftAssert softAssert = new SoftAssert();


	@Test
	public void loginTest() throws InterruptedException 
	{
		try
		{
		int col = reader.getColumnCount("Credentials");
		System.out.println(col);
		int row = reader.getRowCount("Credentials");
		System.out.println(row);
		for(int i=2;i<=row;i++)
		{
			System.out.println("for loop");
			LoginPage lp=new LoginPage(driver);
			HomePage hp=new HomePage(driver);
			lp.setUserName(reader.getCellData("Credentials", "username", i));
			logger.info("Entered username");
			lp.setPassword(reader.getCellData("Credentials", "password", i));
			logger.info("Entered password");
			Thread.sleep(2000);
			lp.clickSubmit();
			logger.info("clicked submit");
			Thread.sleep(3000);
			
			if(alertPresent()==true)
			{
				System.out.println("if loop");
				getAlert();
				logger.info(alertText);
				alertAccept();
				Thread.sleep(3000);
				logger.info("Login failed");
				softAssert.assertTrue(false);
			}
			else
			{
				System.out.println("else loop");
				hp.clickLogout();
				Thread.sleep(3000);
				getAlert();
				logger.info(alertText);
				alertAccept();
				Thread.sleep(3000);
				logger.info("Login passed");
				softAssert.assertTrue(true);
				
				
			}
			
			
		}
		softAssert.assertAll();
		
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	
			

		
	}
