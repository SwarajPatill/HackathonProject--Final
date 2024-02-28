package TestBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
		static public WebDriver driver;
		public static Logger logger;
		public static  Properties p;
		//make every method public 
		
		@BeforeClass(groups= {"sanity","regression","master","smoke"})
		@Parameters({"browser","os"})
		public void setup(String browser,String os) throws IOException {
			
			
			//loading properties file
			FileReader file = new FileReader(".//src/test/resources/config.properties");
			p = new Properties();
			p.load(file);
			
			//Loadinglog4j file for that we have a LogManager class in which getLogger method we have to use
			
			logger = LogManager.getLogger(this.getClass());
			
			//Disableing the notifications
			ChromeOptions c_option = new ChromeOptions();
			c_option.addArguments("disable-notifications");
			
			EdgeOptions e_option = new EdgeOptions();
			e_option.addArguments("disable-notifications");
			
			
			//new code - 
			
			if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
				DesiredCapabilities capabalities = new DesiredCapabilities();
				if(os.equalsIgnoreCase("windows")) {
					capabalities.setPlatform(Platform.WIN11);
				}
				else if (os.equalsIgnoreCase("mac")) {
					capabalities.setPlatform(Platform.MAC);
				}
				else {
					System.out.println("no matching os .....");
					return ;
				}
				//browser
				if(browser.equalsIgnoreCase("chrome")) {
					capabalities.setBrowserName("chrome");
				}
				else if(browser.equalsIgnoreCase("edge")) {
					capabalities.setBrowserName("MicrosoftEdge");
				}
				else {
					System.out.println("no matching browser .....");
					return ;
				}
	 
				 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") , capabalities);
			}
			else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
				// launching browser based on condition - locally
				if(browser.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver(c_option);
					logger.info("Chrome browser opened successfully");
				}
				else if(browser.equalsIgnoreCase("edge")){
					driver = new EdgeDriver(e_option);
					logger.info("Edge browser opened successfully");
				}
				else {
					System.out.println("no matching browser......");
					logger.info("no matching browser......");
					return ;
				}
	 
			}
			
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(p.getProperty("appURL"));
			logger.info("Base Page loaded sucessfully.......");
			driver.manage().window().maximize();
		}
		
		
		//Method to take screenshot
		
			
			public void takescreenshot(String fileName) {
				
				TakesScreenshot ts = (TakesScreenshot)driver;
				File src = ts.getScreenshotAs(OutputType.FILE);      // capture 
				File trg = new File(".\\screenshots\\" + fileName);  // store
				
				try {
					FileUtils.copyFile(src, trg);
				}
				catch(IOException e) {
					System.out.println("could not take screenshot");
				}
			}
		 //method to take screenshot of pass and  fail test cases
			public String captureScreen(String tname)throws IOException{
				
				String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
				File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
				String targetFilePath=System.getProperty("user.dir")+"\\ER_screenshots\\" + tname +"-" + timeStamp + ".png";
				File targetFile = new File(targetFilePath);
				
				sourceFile.renameTo(targetFile);
				return targetFilePath;
			}
		
		
		
			
		@AfterClass(groups= {"sanity","regression","master","smoke"})
		public void tearDown() {
			driver.quit();
			logger.info("browser closed successfully....");
		}
	}


