package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HostLogin {
	SoftAssert HostAssert = new SoftAssert();
	@Test
	public void UserLogin() throws InterruptedException{
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
				WebDriver driver = new FirefoxDriver();
				
				//WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				System.out.println("HeyBnb HomePage");
			
				WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
				
				SignIn.click();
				Thread.sleep(5000);	
				
				
				driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
				
				Thread.sleep(3000);	
				
				driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("devara.vikram@gmail.com");
				driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
				driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
				System.out.println("Host Logged-in successfully");
				
				Thread.sleep(10000);	
				driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[2]/div[1]/img")).click();
				Thread.sleep(3000);	
					
				String Logout = driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[1]/div/div/nav/ul/li[8]/a/b")).getText();
				System.out.println(Logout);
				System.out.println("Host logged out successfully");
				
				
				HostAssert.assertEquals(Logout, "LOG OUT");
				HostAssert.assertAll();
			}
	}


