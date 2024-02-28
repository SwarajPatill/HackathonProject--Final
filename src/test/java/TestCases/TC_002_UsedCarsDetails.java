package TestCases;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BikeDetails;
import PageObjects.UsedCarsDetails;
import TestBase.BaseClass;
import utilities.utilities;
import TestCases.TC_001_BikeDetails;

public class TC_002_UsedCarsDetails extends BaseClass {
			
	
	BikeDetails bd;
	@Test(priority=2,groups= {"master","smoke"})
		public void Verify_WebPage() {

		    bd = new BikeDetails(driver);
			String Actual_Title = 	bd.get_Title();
			String Expected_Title = p.getProperty("Title");
			Assert.assertEquals(Actual_Title, Expected_Title);
			
	    logger.info("Page sucessfully verified...");
		}
	
	
	
	@Test(priority=3,dependsOnMethods = "Verify_WebPage",groups= {"master","sanity"})
	public void Verify_UsedCarsDetails() throws FileNotFoundException, InterruptedException {
		UsedCarsDetails cd = new UsedCarsDetails(driver);
		
		cd.Hover_UsedCars();
		logger.info("***************TC__002_UsedCarsDetails started*****************");
		logger.info("Hover to used cars...");
		takescreenshot("usedcars.png");
		logger.info("screenshot is taken...");


		cd.click_location();
		logger.info("click on desired location...");
		cd.Scroll_page();
		
		List<WebElement> elems = cd.usc();
		String[] data = new String[elems.size()];
		for(int i =0; i<elems.size(); i++) {
			data[i] = elems.get(i).getText();

		}
		logger.info("sucessfully converted to string from webelement...");

		
		System.out.println("data got : " + Arrays.toString(data));
		utilities.writeInfoInColumn(data,0);
		logger.info("get the details in excel...");
		
		cd.page_refresh();

		logger.info("***************TC__002_UsedCarsDetails finished*****************");


	}
}
