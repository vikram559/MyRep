package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GuestCancellation_BeforeHost_Accepts {
	    WebDriver driver;
	    SoftAssert Guest1 = new SoftAssert();
	    WebDriverWait wait;
	@BeforeTest
	public void HomePage() throws InterruptedException{
		
			//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		     System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
			
			//WebDriver driver = new ChromeDriver();
			//Browser opening
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//Home Page
			driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
			System.out.println("HeyBnb HomePage");
	}
    	@Test(priority=1)
    	public void GuestLogin(){
	    	//SignIn
			WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
			SignIn.click();
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]"))).click();
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input"))).sendKeys("dev.test1@heypillow.com");
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
			driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-user-avatar']/img")));
			System.out.println("User Logged in Successfully");
			
	}
	   @Test(priority=2)
	   public void SearchForProperty(){
		 
			//Search for Bangalore
			driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/bangalore");
			wait = new WebDriverWait(driver, 15);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/properties/59/view' and @title='Exclusive propetry Bengaluru']"))).click();
					
	       }
		@Test(priority=3)
		public void SelectDates() throws InterruptedException{		
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='PropertyForm[check_in]']"))).sendKeys("2017-08-09");
			
			//Property Details Page
			
			driver.findElement(By.xpath("//input[@name='PropertyForm[check_out]']")).sendKeys("2017-08-10");
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[@class='obj-right']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='btn-black']")).click();
		
			//Book Page
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='propertyform-guest_name']"))).sendKeys("Vikram");
			//driver.findElement(By.xpath("//input[@id='propertyform-guest_name']")).sendKeys("Vikram");
			driver.findElement(By.xpath("//input[@id='propertyform-male']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@id='book_now']")).click();
			
		 }
	  @Test(priority=4)
	  public void BookingStatus() throws InterruptedException{
			//Booking Success Page
			String Status = driver.findElement(By.xpath("//div[@class='account-activated-inner']/h1")).getText();
			System.out.println("Booking Status is ...."+Status);
			
			SoftAssert Check = new SoftAssert();
		    Check.assertEquals(Status, "Booking request sent successfully");
		   // Thread.sleep(5000);
	}
	  @Test(priority=5)
	  public void GuestCancel() throws InterruptedException{
		    //LogOut As Guest
		    wait = new WebDriverWait(driver, 15);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-user-avatar']/img"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/trips/upcoming']"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='trips-table']/table/tbody/tr[@class='request']/td[6]/p[2]/a"))).click();
//		   String Status= driver.findElement(By.xpath("//div[@class='trips-table']/table/tbody/tr[@class='request']/td/p")).getText();
//		   System.out.println(Status); 
//		   Guest1.assertEquals(Status, "Cancelled");
		    Thread.sleep(7000);
		   String ResStatus = driver.findElement(By.xpath("//div[@class='trips-table']/table/tbody/tr/td/p")).getText();
		   System.out.println(ResStatus);
		   Guest1.assertEquals(ResStatus, "Cancelled");
		   Thread.sleep(7000);
		   
	}   
	  @Test(priority=6)
	  public void GuestLogOut() throws InterruptedException{
		    //LogOut As Guest
		    wait = new WebDriverWait(driver, 15);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-user-avatar']/img"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='top-links-link']/b"))).click();
		    
		}   
	 @AfterTest
	 public void BrowserClose(){
		   driver.quit();
		   System.out.println("Browser Closed Successfully"); 
		 
	 }
	 
}
