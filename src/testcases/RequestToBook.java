package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RequestToBook {
	//DesiredCapabilities capabilities = DesiredCapabilities.firefox();

	
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, 15);
    SoftAssert HomeAssert = new SoftAssert();
    WebDriverWait wait; 
    
	@BeforeTest
	public void BrowserOpen() throws InterruptedException{
		
			//System.setProperty("webdriver.chrome.driver", "D:\\Vikram\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.0-win64\\geckodriver.exe");
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
	public void GuestLoginSendRequest() throws InterruptedException{
	    	//SignIn
			WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
			
			SignIn.click();
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")));
			
			driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
			
			Thread.sleep(5000);	
			
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("dev.test1@heypillow.com");
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).click();
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
			Thread.sleep(7000);
			//Search for Bangalore
			driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/bangalore");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@href='/properties/59/view' and @title='Exclusive propetry Bengaluru']")).click();
			Thread.sleep(5000);
			
			//Property Details Page
			driver.findElement(By.xpath("//input[@name='PropertyForm[check_in]']")).sendKeys("2017-08-09");
			driver.findElement(By.xpath("//input[@name='PropertyForm[check_out]']")).sendKeys("2017-08-10");
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[@class='obj-right']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='btn-black']")).click();
//			WebElement RTOBOOK = driver.findElement(By.xpath("//div[@class='btn-black']"));
//			   JavascriptExecutor js = (JavascriptExecutor)driver;
//	           js.executeScript("arguments[0].click();", RTOBOOK);
			//Book Page
			driver.findElement(By.xpath("//input[@id='propertyform-guest_name']")).sendKeys("Vikram");
			driver.findElement(By.xpath("//input[@id='propertyform-male']")).sendKeys("1");
			//driver.findElement(By.xpath("//input[@id='not_traveller' and @type='checkbox']")).click();
			driver.findElement(By.xpath("//input[@id='book_now']")).click();
			Thread.sleep(6000);
			
			//Booking Success Page
			String Status = driver.findElement(By.xpath("//div[@class='account-activated-inner']/h1")).getText();
			System.out.println("Booking Status is ...."+Status);
	
	
			SoftAssert Check = new SoftAssert();
		    Check.assertEquals(Status, "Booking request sent successfully");
		    Thread.sleep(5000);
	}
	@Test(priority=2)
	public void GuestLogout(){
		    //LogOut As Guest
		    driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
		    driver.findElement(By.xpath("//div[@class='nav-user-avatar']")).click();
	        //Thread.sleep(5000);
		    wait = new WebDriverWait(driver, 15);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='top-links-link']/b"))).click();
	       //Thread.sleep(4000);
	       // driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
	}
	@Test(priority=3)
	public void HostLoginToAccept() throws InterruptedException{
			//Host Login for accepting the request
	        WebElement HostIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
			HostIn.click();
			
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/auth/sign-in?view=popup&role=1']"))).click();
			
			//driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
						
			Thread.sleep(5000);	
			
			driver.findElement(By.xpath("//input[@id='loginform-email']")).sendKeys("devara.vikram@gmail.com");
			driver.findElement(By.xpath("//input[@id='loginform-password']")).sendKeys("test@1234");
			driver.findElement(By.xpath("//input[@id='loginform-password']")).click();
			driver.findElement(By.xpath("//div[@class='btn-black']/input[@type='submit']")).click();
			Thread.sleep(7000);
			
			
			driver.findElement(By.xpath("//div[@class='nav-user-avatar']")).click();
			//WebDriverWait wait1 = new WebDriverWait(driver, 15);
			
			Thread.sleep(10000);
			
			//My Listings page
			driver.findElement(By.xpath("//a[@href='/listing/manage']")).click();
	        Thread.sleep(4000);
			driver.findElement(By.xpath("//a[@href='/listings/reservations']")).click();
	        Thread.sleep(4000);
			driver.findElement(By.xpath("//div[@id='listing-reservations']/table/tbody/tr/td[2]/h2")).click();
	        Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@class='accept-reservation' and @type='submit']")).click();
	        Thread.sleep(7000);
	        //wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='col-status']/b/b")));
	}
	@Test(priority=4)
	public void HostLogOut() throws InterruptedException{
	      //LogOut
	        driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
		    driver.findElement(By.xpath("//div[@class='nav-user-avatar']")).click();
	        //Thread.sleep(5000);
		    wait = new WebDriverWait(driver, 15);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='top-links-link']/b"))).click();
	        Thread.sleep(4000);
	        driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
	}
	@Test(priority=5)
	public void GuestLoginToPayment() throws InterruptedException{
	      // Login as Guest
		driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
		WebElement GuestIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
		
		GuestIn.click();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")));
		
		driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
		
		//Thread.sleep(5000);	
		
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("dev.test1@heypillow.com");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).click();
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
		Thread.sleep(10000);
        
		
		//My Trips for Payment
		driver.findElement(By.xpath("//div[@class='nav-user-avatar']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@ href='/trips/upcoming']")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Pay Now >>")).click();
        Thread.sleep(5000);
        //Payment Page
        //driver.findElement(By.xpath("//select[@id='payment_methods']/option[@value='airtel']")).click();
       //driver.findElement(By.xpath("//input[@id='propertyform-coupon_code']")).sendKeys("First10");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='propertyform-billing_name']"))).sendKeys("Vikram");
        driver.findElement(By.xpath("//input[@id='propertyform-billing_address']")).sendKeys("Devara");
        driver.findElement(By.xpath("//input[@id='propertyform-billing_city']")).sendKeys("Hyderabad");
        driver.findElement(By.xpath("//input[@id='propertyform-billing_state']")).sendKeys("Telangana");
        driver.findElement(By.xpath("//input[@id='propertyform-billing_zip']")).sendKeys("500060");
       
       
        driver.findElement(By.xpath("//span[text()='Country']")).click();
        driver.findElement(By.xpath("html/body/div[7]/div/ul/li[104]/label")).click();
       //river.findElement(By.xpath("//span[text()='Country']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
       // driver.findElement(By.xpath("//input[@id='propertyform-billing_tel']")).sendKeys("8105786677");
       // driver.findElement(By.xpath("//input[@id='propertyform-billing_email']")).sendKeys("dev.test1@heypillow.com");
        driver.findElement(By.xpath("//input[@id='agree' and @name='agree']")).click();
        driver.findElement(By.xpath("//div[@class='btn-black']")).click();
        Thread.sleep(5000);
       // String PaymentPage=driver.findElement(By.xpath("//div[@class='span12 content-bg body-left-panel']/div/span/table/tbody/tr/td[2]")).getText();
       // System.out.println(PaymentPage);
	        
	}
	@AfterTest
	public void BrowserClose(){
		driver.quit();
	}
	
	
	
}
