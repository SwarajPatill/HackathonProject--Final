package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BikeDetails;
import TestBase.BaseClass;
import utilities.utilities;


public class TC_001_BikeDetails extends BaseClass {
	
	public BikeDetails bd ;
		
	@Test(priority=0,groups= {"master","smoke"})
		public void Verify_WebPage() {

			bd = new BikeDetails(driver);
			String Actual_Title = 	bd.get_Title();
			String Expected_Title = p.getProperty("Title");
			Assert.assertEquals(Actual_Title, Expected_Title);
			
	    logger.info("Page sucessfully verified...");

		}
	
	
	@Test(priority=1,dependsOnMethods = "Verify_WebPage",groups= {"master","sanity"})
		public void Verify_BikeDetails() throws InterruptedException, IOException {
		
		logger.info("***************TC__001_BikeDetails started*****************");

		utilities excelutility = new utilities();
		
		
		
		bd.clickNewBikes();
		logger.info("clicked on new bikes...");

		
		bd.selectingManufacturer();
		logger.info("selecting the manufacturer from dropdown...");

        
		Thread.sleep(3000);		
		
		bd.clickViewMoreBikes();
		logger.info("clicked on viewMoreBikes button...");

		
		Map<String, String[]>details = bd.getDetails();
		
		utilities.writeToExcel(details);
		
		logger.info("write the data into excel...");

		logger.info("***************TC__001_BikeDetails finished*****************");


}
}
