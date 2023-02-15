package pom.ecommerce.testCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pom.ecommerce.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		
	    logger.info("url is opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		 logger.info("Enter username");
		
		lp.setPassword(password);
		logger.info("Enter password");
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else {
			CaptureScreen(driver,"LoginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");

			
		}		
			
	}
	
}


















