package PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class UsedCarsDetails extends BasePage {

	public UsedCarsDetails(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
		
	
	Actions action;
	@FindBy(xpath="//a[text()=\"Used Cars\"]")
	private WebElement UsedCars;
	
	@FindBy(xpath="(//span[text()=\"Chennai\"])[1]")
	private WebElement location;
	
	@FindAll(@FindBy(xpath="//li[starts-with(@id,'mmvLi')]"))
	private List<WebElement> popularcars;
	
	@FindBy(xpath="//div[text()=\"Popular Models\"]")
	private WebElement Popular_Models;
	
	
	public void Hover_UsedCars() {
		action = new Actions(driver);
		action.moveToElement(UsedCars).build().perform();
	}
	
	
	public void click_location() {
		location.click();
	}
	
	
	public void Scroll_page() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView;",Popular_Models);
	}
	
	public List<WebElement> usc() {
		return popularcars;
	}
	
	
	public void page_refresh() {
		driver.navigate().back();
	}
	
	
}
