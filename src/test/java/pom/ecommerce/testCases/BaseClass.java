package pom.ecommerce.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pom.ecommerce.utilities.ReadConfig;

public class BaseClass {
	public static Logger logger;
	
	ReadConfig rc=new ReadConfig(); 
	public String url=rc.getApplicationURL();
	public String username=rc.getUsername();
	public String password=rc.getPassword();
	public static WebDriver driver;
	
	
	@Parameters("browser")
	@BeforeMethod
	public void startBrowser(String br) {
		
		logger=Logger.getLogger("framework");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",rc.getChromepath());
			driver= new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver",rc.getFirefoxpath());
			driver= new FirefoxDriver();
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",rc.getInternetExplorerpath());
			driver= new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(url);
	
		
	}
	
	
	@AfterMethod
    public void stopBrowser() {
		driver.quit();
	}
	public void CaptureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new  File(System.getProperty("user.dir")+("/ScreenShots/")+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("ScreenShot taken");
	
	}

}
