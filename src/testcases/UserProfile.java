package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserProfile {
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert User = new SoftAssert();
   
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
	public void Profile() throws InterruptedException{
		
		Thread.sleep(5000);
     	driver.findElement(By.xpath("//button[@class='dropbtn']")).click();
    
     	Thread.sleep(5000);	
		driver.findElement(By.xpath("//a[@href='/auth/sign-in?view=popup&role=1']")).click();
		
		Thread.sleep(3000);	
		
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("devara.vikram@gmail.com");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
		//Click on Avatar
		Thread.sleep(10000);
		driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[2]/div[1]/img")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[2]/div[1]/img")).click();
		WebElement Avatar = driver.findElement(By.xpath("//div[@class='nav-middle-content']/nav[@role='navigation']/ul"));
		List<WebElement> Links = Avatar.findElements(By.tagName("a"));
		System.out.println("Total Links are---->"+Links.size());
		
		for(int i=0;i<Links.size();i++){
			System.out.println(Links.get(i).getText());
					
		}
			
	}
	
	@AfterTest
	public void BrowserClose(){
		driver.quit();
	}

}
