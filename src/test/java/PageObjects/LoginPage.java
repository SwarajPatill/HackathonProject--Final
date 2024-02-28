package PageObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import TestBase.BaseClass;

public class LoginPage extends BasePage {
	//WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//div[@id=\"forum_login_wrap_lg\"]")
	 private WebElement button;
	
	@FindBy(xpath="//span[text()='Google']")
	 private WebElement google;
	
	@FindBy(xpath="//input[@id=\"identifierId\"]")
	 private WebElement email;
	
	@FindBy(xpath="//span[text()=\"Next\"]")
	 private WebElement nextbutton;
	
	@FindBy(xpath="//div[contains(text(),'Enter a valid email or phone number')]")
	 private WebElement message;
	
	
	
	
	public void click_login() {
		button.click();
	}
	
	public void click_Google() {
		google.click();
	}
	
	
	public void handlewindow() {
		Set<String>windows=driver.getWindowHandles();
		Iterator<String>win_it = windows.iterator();
		String p = win_it.next();
		String c = win_it.next();
		driver.switchTo().window(c);
	}
	
	public void provide_email(String email_) {
		email.sendKeys(email_);
	}
	
	public void click_nextbutton() {
		nextbutton.click();
	}
	
	public String errormessage() {
		String ErrorMessage = message.getText();
		System.out.println("Error message :-"+""+ ErrorMessage);
		return ErrorMessage;
	}
}
