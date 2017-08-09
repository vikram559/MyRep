package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ForgotPwd {
	
	@Test
	public void FgtPwd() throws InterruptedException{
		//System.setProperty("webdriver.chrome.driver", "D:\\Vikram\\chromedriver_win32\\chromedriver.exe");
				System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
//				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//				capabilities.setCapability("marionette", false);
				WebDriver driver = new FirefoxDriver();
				
				//WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				System.out.println("HeyBnb HomePage");
			
				//Thread.sleep(5000);
				
				WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
				
				SignIn.click();
				Thread.sleep(5000);
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")));
				
				driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
				Thread.sleep(7000);
				driver.findElement(By.linkText("Forgot password?")).click();
				Thread.sleep(5000);	
				driver.findElement(By.xpath("//input[@id='passwordresetrequestform-email']")).sendKeys("dev.test1@heypillow.com");
				driver.findElement(By.xpath("//div[@class='buttons']/div/input[@type='submit']")).click();
				System.out.println("pwd reset link sent successfully");
				
	}

}
