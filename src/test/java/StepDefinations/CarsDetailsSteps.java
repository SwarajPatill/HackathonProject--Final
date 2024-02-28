package StepDefinations;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.UsedCarsDetails;
import TestBase.CucumberBaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utilities.utilities;

public class CarsDetailsSteps {
	WebDriver driver;
	UsedCarsDetails cd = new UsedCarsDetails(CucumberBaseClass.getDriver());
	
	@Given("the user is on application")
	public void the_user_is_on_application() {
	    CucumberBaseClass.getLogger().info("******Cucumber Cars Details Test started*****");

	}

	@When("the user hover to UsedCars")
	public void the_user_hover_to_used_cars() {
		cd.Hover_UsedCars();
	    CucumberBaseClass.getLogger().info("hover on used cars...");

	}

	@When("the user click on location chennai")
	public void the_user_click_on_location_chennai() {
		cd.click_location();
	    CucumberBaseClass.getLogger().info("clicked on location...");

	}

	@When("get the popular models")
	public void get_the_popular_models() throws FileNotFoundException {
		
		List<WebElement> elems = cd.usc();
		String[] data = new String[elems.size()];
		for(int i =0; i<elems.size(); i++) {
			data[i] = elems.get(i).getText();

		}
		
		System.out.println("data got : " + Arrays.toString(data));
		utilities.writeInfoInColumn(data,0);
	    CucumberBaseClass.getLogger().info("got the popular models...");

	    CucumberBaseClass.getLogger().info("******Cucumber Cars Details Test Finished*****");

	}

}
