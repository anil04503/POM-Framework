package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	//public static WebDriver driver;
	public static WebDriver driver;
	
	public static Alert alert=null;
	public static WebDriverWait wait;
	public static String alertText;

	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	public static Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{			
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public boolean alertPresent()
	{
		try
		{
		alert = driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	public void waitForAlert()
	{
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void getAlert()
	{
		
		alert=driver.switchTo().alert();
		alertText=alert.getText();
		
	}
	
	public void alertAccept()
	{
		alert=driver.switchTo().alert();
		alert.accept();
		
	}
	
	
	
}
