package PageObjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utilities.utilities;

public class BikeDetails extends BasePage {

	public BikeDetails(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	List<String> bikeNamesTxt = new ArrayList<String>();
	List<String> bikePricesTxt = new ArrayList<String>();
	List<String> bikeReleaseDatesTxt = new ArrayList<String>();
	
	Map<String,String[]> bikeUnder4Lac = new LinkedHashMap<String,String[]>();
	
	@FindBy(xpath="//a[normalize-space()='New Bikes']")
	private WebElement drpdwnNewBikes;
	
	@FindBy(xpath="//span[@onclick=\"goToUrl('/upcoming-bikes')\"]")
	private WebElement UpcomingBikes;
	
	@FindBy(id="makeId")
	private WebElement Manufacturers;
	
	@FindBy(xpath="//span[normalize-space()='...Read More']")
	private WebElement spanLoadMore;
	
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']")
	private WebElement btnViewMoreBikes;
	
	@FindBy(xpath = "//img[@data-track-label=\"zw-header-logo\"]")
	private WebElement Verify_logo;
	
	@FindAll(@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']"))
	List<WebElement> listBikeNames;
	
	@FindAll(@FindBy(xpath="//div[contains(@title,'Ex-Showroom')]"))
	List<WebElement> listBikePrices;
	
	@FindAll(@FindBy(xpath="//div[contains(text(),'Launch Date')]"))
	List<WebElement> listBikeReleaseDates;
	
	
	
	
	public String get_Title() {
		String Actual_Title = driver.getTitle();
		System.out.println(Actual_Title);
		return Actual_Title;
	}

   
	public void clickNewBikes() {
		
		Actions act = new Actions(driver);
		act.moveToElement(drpdwnNewBikes);
		act.perform();
		UpcomingBikes.click();
	}
	
	
	public void selectingManufacturer() {
		
		Select select =  new Select(Manufacturers);
		select.selectByVisibleText("Honda");
			
	}
	
	

	
	
	public void clickViewMoreBikes() throws InterruptedException{
	
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",btnViewMoreBikes);
		
		
	}
		
	
	public List<WebElement> extractHondaBikeNames() {
		
		return listBikeNames;
		
	}
	
	
	public List<WebElement> extractHondaBikePrices() {
			
			return listBikePrices;
			
	}
	
	
	public List<WebElement> extractHondaBikeReleaseDates() {
		
		return listBikeReleaseDates;
		
	}

	public  Map<String, String[]> getDetails() {
        
		
		
		List<WebElement> bikeNames = extractHondaBikeNames();
		
		List<WebElement> bikePrices = extractHondaBikePrices();
		
		List<WebElement> bikeReleaseDaates = extractHondaBikeReleaseDates();
		
		for(int i=0 ; i<bikeNames.size();i++) {
			
			String bikeName = bikeNames.get(i).getText();
			bikeNamesTxt.add(bikeName);
			
			
			String bikePrice = bikePrices.get(i).getText();
			bikePricesTxt.add(bikePrice);
			
			String bikeReleaseDate = bikeReleaseDaates.get(i).getText();
			bikeReleaseDatesTxt.add(bikeReleaseDate);
			
			//Split the string where we find space
			String[] bikePrice_ =  bikePrice.split(" ");
			
			
			double bikePriceD = 0;  // use for more precision than float
			int bikePriceI = 0;
			
			//check if in bikeprice array at first index contains . or not if prsent then go
			if(bikePrice_[1].contains(".")) {
				bikePriceD = Double.parseDouble(bikePrice_[1]);      //convert string to double
			}
			else {
				String a = bikePrice_[1].replace(",", "");
				bikePriceI = Integer.parseInt(a);
			}
			
			//store in map 
			if(bikePriceD<4.0) {
				bikeUnder4Lac.put(bikeName, new String[] {bikePrice,bikeReleaseDate});
			}
			
					
		}
		
		//System.out.println(bikeNamesTxt);
		
		for(Map.Entry<String, String[]> e :bikeUnder4Lac.entrySet()) {
			System.out.println(e.getKey()+":"+e.getValue()[0]+" "+e.getValue()[1]);
		}

	
		return bikeUnder4Lac;
		
		}
		
	

}

