package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BikeDetails;
import PageObjects.LoginPage;
import TestBase.BaseClass;
import utilities.utilities;
import TestCases.TC_001_BikeDetails;



public class TC_003_LoginPage extends BaseClass {
	
	
	BikeDetails bd;
	@Test(priority=5,groups= {"master","smoke"})
	public void Verify_WebPage() {

	    bd = new BikeDetails(driver);
		String Actual_Title = 	bd.get_Title();
		String Expected_Title = p.getProperty("Title");
		Assert.assertEquals(Actual_Title, Expected_Title);
		
        logger.info("Page sucessfully verified...");

	}
	

	
	@Test(priority=6,dependsOnMethods = "Verify_WebPage",groups= {"master","smoke"})
	public void verify_Login() throws InterruptedException, IOException {	
		LoginPage lp = new LoginPage( driver);

		
		lp.click_login();
		logger.info("***************TC__003_LoginPag started*****************");
		logger.info("clicked on login button...");
		takescreenshot("login.png");
		logger.info("screenshot is taken...");
		
		Thread.sleep(3000);
		lp.click_Google();
		logger.info("login with google option...");
		takescreenshot("google.png");
		logger.info("screenshot is taken...");


		Thread.sleep(3000);
		lp.handlewindow();
		logger.info("switch from parent window to child window...");


		lp.provide_email(p.getProperty("email"));
		logger.info("provide the invalid email...");
		takescreenshot("email.png");
		logger.info("screenshot is taken...");



		lp.click_nextbutton();
		logger.info("clicked on next button...");

		try {
			
		String Actual_Msg = lp.errormessage();
		String Expected_Msg = p.getProperty("Error_Message");
		Assert.assertEquals(Actual_Msg, Expected_Msg);
		logger.info("captured the error message...");
		takescreenshot("errormessage.png");
		logger.info("screenshot is taken...");

		}catch(Exception e) {
			Assert.fail();
			logger.error("Error msg not verifies...");
			takescreenshot("fail_errormessage.png");

		}
		
		logger.info("***************TC__003_LoginPage Finished*****************");

	}
}
