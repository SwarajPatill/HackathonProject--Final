package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

//IT only contain the constuctor 
//we are assign the driver
// we are taking the driver instance
//super is invoking the parent class constructor
//it contain only constructor only initiate the browser