package StepDefinations;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObjects.LoginPage;
import TestBase.CucumberBaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps {

	WebDriver driver;
	Properties p;
	LoginPage lp = new LoginPage(CucumberBaseClass.getDriver());
	
	@Given("the user on application")
	public void the_user_on_application() throws IOException {
	    CucumberBaseClass.getLogger().info("******Cucumber Login Test started*****");

		p = CucumberBaseClass.getProperties();
	}

	@When("the user clicked on log in button")
	public void the_user_clicked_on_log_in_button() {

		lp.click_login();
	    CucumberBaseClass.getLogger().info("Clicked on login button...");

		

	}

	@When("the user clicked on google button for login")
	public void the_user_clicked_on_google_button_for_login() {
        try {
			Thread.sleep(3000);
			lp.click_Google();
		    CucumberBaseClass.getLogger().info("Clicked on google button...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@When("the user switch the window")
	public void the_user_switch_the_window() {
		try {
			Thread.sleep(3000);
			lp.handlewindow();
		    CucumberBaseClass.getLogger().info("Switch the window...");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@When("the user entered invalid user email")
	public void the_user_entered_invalid_user_email() {
		//String mail = (RandomStringUtils.random(10,true,true)+"@gmail.com");
		lp.provide_email("abc@abc");
	    CucumberBaseClass.getLogger().info("provided the email...");

	}

	@When("the user clicked on next button")
	public void the_user_clicked_on_next_button() {
	    lp.click_nextbutton();
	    CucumberBaseClass.getLogger().info("Clicked on next button...");

	}

	@Then("the user should see a error message")
	public void the_user_should_see_a_error_message() {
	    try {
			
			String Actual_Msg = lp.errormessage();
			String Expected_Msg = p.getProperty("Error_Message");
			Assert.assertEquals(Actual_Msg, Expected_Msg);
		    CucumberBaseClass.getLogger().info("got the error message...");

			}catch(Exception e) {
				Assert.fail();

			}
	    CucumberBaseClass.getLogger().info("*****Cucumber Login test finished*****");


	}

}
