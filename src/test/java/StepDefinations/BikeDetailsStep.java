package StepDefinations;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import PageObjects.BikeDetails;
import PageObjects.UsedCarsDetails;
import TestBase.CucumberBaseClass;
import io.cucumber.java.en.When;
import utilities.utilities;

public class BikeDetailsStep {

	WebDriver driver;
	 BikeDetails bd = new BikeDetails (CucumberBaseClass.getDriver());
	
	@When("the user clicked on NewBikes")
	public void the_user_clicked_on_new_bikes() {
	    CucumberBaseClass.getLogger().info("******Cucumber Bike Details Test started*****");

		bd.clickNewBikes();
	    CucumberBaseClass.getLogger().info("Clicked on New Bikes");
	}

	@When("the user select the Honda from dropdown")
	public void the_user_select_the_honda_from_dropdown() {
		bd.selectingManufacturer();
	    CucumberBaseClass.getLogger().info("selecting the manufacturer from dropdown...");

	}

	@When("the user clicked on the view more Bikes")
	public void the_user_clicked_on_the_view_more_bikes() throws InterruptedException {
		bd.clickViewMoreBikes();
	    CucumberBaseClass.getLogger().info("clicked on viewMoreBikes button...");

	}
	
	@When("get all Bike details for selected manufacturer")
	public void get_all_bike_details_for_selected_manufacturer() throws IOException {
		Map<String, String[]>details = bd.getDetails();
		System.out.println(details);
		utilities.writeToExcel(details);
	    CucumberBaseClass.getLogger().info("got the details...");
	    CucumberBaseClass.getLogger().info("******Cucumber Bike Details Test Finished*****");

	}

}
