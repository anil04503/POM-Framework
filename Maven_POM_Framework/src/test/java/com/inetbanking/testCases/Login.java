package com.inetbanking.testCases;


import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;


public class Login extends BaseClass
{

	@Test
	public void loginTest() throws IOException 
	{
			
		logger.info("URL is opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lp.clickSubmit();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			System.out.println("if loop");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			System.out.println("else loop");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
	}
}
