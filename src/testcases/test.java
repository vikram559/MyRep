package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


	public class test {
		 //SoftAssert GuestAssert = new SoftAssert();
		// WebDriver driver;
		 //WebDriverWait wait;
		@Test
		public static void HostLogin() throws InterruptedException{
//			System.setProperty("webdriver.chrome.driver", "D:\\Vikram\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.0-win64\\geckodriver.exe");
				WebDriver driver = new FirefoxDriver();
				
				//WebDriver driver = new ChromeDriver();
				//Browser opening
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//Home Page
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				System.out.println("HeyBnb HomePage");
				WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
				SignIn.click();
				WebDriverWait wait = new WebDriverWait(driver, 25);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/auth/sign-in?view=popup&role=1']"))).click();
				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginform-email']"))).sendKeys("dev.test1@heypillow.com");
				driver.findElement(By.xpath("//input[@id='loginform-password']")).sendKeys("test@1234");
				driver.findElement(By.xpath("//div[@class='btn-black']/input[@type='submit']")).click();
				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nav-user-avatar']/img"))).click();
				System.out.println("User Logged in Successfully");
				//Thread.sleep(5000);
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/default/small_avatar.png']"))).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/trips/upcoming']"))).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@href,'/trips/cancel-request-new?reservationId')]"))).click();
				Thread.sleep(7000);
				String Status =driver.findElement(By.xpath("//*[@id='w0']/tbody/tr[1]/td[1]/p")).getText();
				System.out.println(Status);
				SoftAssert GuestAssert = new SoftAssert();
				GuestAssert.assertEquals(Status, "Cancelled");
				
		}
}
