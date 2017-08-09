package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShareYorSpace {
	//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, 15);
	SoftAssert Check = new SoftAssert();
    WebDriverWait wait; 
	@BeforeTest
	public void HomePage(){
		//System.setProperty("webdriver.chrome.driver", "D:\\Vikram\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		//capabilities.setCapability("marionette", false);
		driver = new FirefoxDriver();
		
		//driver = new ChromeDriver();
		//Browser opening
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Home Page
		driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
		System.out.println("HeyBnb HomePage");
	}
	@Test(priority=1)
	public void HostSignIn() throws InterruptedException{
	//SignIn
		WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
		
		SignIn.click();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")));
		driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
		
		Thread.sleep(5000);	
		
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("devara.vikram@gmail.com");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).click();
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
		Thread.sleep(7000);
	}
	@Test(priority=2)
	public void PropertyListing() throws InterruptedException{
		//Share your space
		driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[1]/div/a[1]")).click();
		Thread.sleep(5000);
		//Property Type
		driver.findElement(By.xpath("//div[@class='label']/b")).click();
		driver.findElement(By.xpath("//*[@id='propertyform-room_type']/li/div/b")).click();
		driver.findElement(By.xpath("//span[text()='Country']")).click();
        driver.findElement(By.xpath("html/body/div[7]/div/ul/li[104]/label")).click();
        driver.findElement(By.xpath("//*[@id='property-create-form']/div[5]/div[2]/div/input")).click();
        Thread.sleep(7000);
        
        //Complete your listing pop-up
        driver.findElement(By.xpath("//*[@id='popup-listing']/div/div/div[2]/a")).click();
        wait = new WebDriverWait(driver, 15);
        //Location Details
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='propertyform-street_name']"))).sendKeys("Cunningham road");
       // driver.findElement(By.xpath("//*[@id='propertyform-street_name']")).sendKeys("Cunningham road");
        driver.findElement(By.xpath("//*[@id='propertyform-street_number']")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id='propertyform-city']")).sendKeys("Bangalore");
        driver.findElement(By.xpath("//*[@id='propertyform-state']")).sendKeys("Karnataka");
        driver.findElement(By.xpath("//*[@id='propertyform-zip']")).sendKeys("560052");
       
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='btn-black']"))).submit();
       // driver.findElement(By.xpath("//*[@id='property-location-form']/aside/div/div[3]/div/div/input")).submit();
        Thread.sleep(5000);
        
        //Location map
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']"))).submit();
        Thread.sleep(4000);
       
        //Property details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='propertyform-title']"))).sendKeys("AutoTest Property");
        String Title = driver.findElement(By.xpath("//*[@id='propertyform-title']")).getText();
        System.out.println(Title);
        driver.findElement(By.xpath("//*[@id='propertyform-summary']")).sendKeys("Include attractive facts about your property and its neighbourhood, nice summary will fetch you more bookings. You can always update your listing price at later point of time. You can provide discounted rate in case of weekly and monthly bookings.");
        driver.findElement(By.xpath("//*[@id='propertyform-per_day']")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id='propertyform-per_week']")).sendKeys("7");
        driver.findElement(By.xpath("//*[@id='propertyform-per_month']")).sendKeys("30");
        driver.findElement(By.xpath("//*[@id='propertyform-deposit_refund']/label[1]/input")).click();
        driver.findElement(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //driver.findElement(By.xpath("//div[@class='inner-layout']/div/ul/li/div//div/div/b/i")).click();
        //Image Upload
        driver.findElement(By.xpath("//input[@id='imageuploadform-image']")).sendKeys("D:\\Images\\Home.jpg");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //Needs or Amenities
       WebElement features =  driver.findElement(By.xpath("//label/input[@id='propertyform-kitchen']"));
           JavascriptExecutor js = (JavascriptExecutor)driver;
           js.executeScript("arguments[0].click();", features);
       WebElement conveniences = driver.findElement(By.xpath("//label/input[@id='propertyform-gym']"));
           JavascriptExecutor js1 = (JavascriptExecutor)driver;
           js1.executeScript("arguments[0].click();", conveniences);
       WebElement Extras = driver.findElement(By.xpath("//label/input[@id='propertyform-family_or_kid_friendly']"));
	       JavascriptExecutor js2 = (JavascriptExecutor)driver;
	       js2.executeScript("arguments[0].click();", Extras);
	   WebElement Safety = driver.findElement(By.xpath("//label/input[@id='propertyform-smoke_detector']"));
		   JavascriptExecutor js3 = (JavascriptExecutor)driver;
	       js3.executeScript("arguments[0].click();", Safety);
        driver.findElement(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //Information Page
        String Info = driver.findElement(By.xpath("//div[@class='form-listing-info-fieldset']/h2")).getText();
		System.out.println(Info);
		
        
		Check.assertEquals(Info, "How Much Space is Available?");
		driver.findElement(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //Property Terms
        String Terms = driver.findElement(By.xpath("//div[@class='form-terms']/h1")).getText();
		System.out.println(Terms);
		driver.findElement(By.xpath("//div[@class='page-info']/div[3]/div/div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //Calendar Page
        driver.findElement(By.xpath("//div[@class='btn-black']")).submit();
        Thread.sleep(4000);
        
        //You are almost done!
        driver.findElement(By.xpath("//input[@type='checkbox']")).submit();
        driver.findElement(By.xpath("//input[@id='finishButton']")).submit();
        Thread.sleep(10000);
	}
	@Test(priority=3)
	public void ListingStatus() throws InterruptedException{
        //MyListings
        driver.findElement(By.xpath("//img[@alt='avatar']")).click();
        wait = new WebDriverWait(driver, 15);
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/listing/manage']"))).click();
        Thread.sleep(4000);
        String Status = driver.findElement(By.xpath("//span[@class='ui-multiselect-value']")).getText();
        System.out.println("Status is..."+Status);
       
        Check.assertEquals(Status, "Not Listed");
        Check.assertAll();
    }
	@AfterTest
	public void BrowserClose(){
		driver.quit();
	}
}
