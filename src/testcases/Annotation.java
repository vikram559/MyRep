package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


         
    public class Annotation {
     WebDriver driver;
     SoftAssert Assert3 = new SoftAssert();
     
 @BeforeTest
 public void BrowserOpen(){
  //System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	 System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
  driver = new FirefoxDriver();
  
  //WebDriver driver = new ChromeDriver();
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  
  driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
  System.out.println("HeyBnb HomePage");
 }
 
 @AfterTest
 public void BrowserClose(){
  driver.quit();
  System.out.println("Browser Closed Successfully");
 }
 
 @BeforeMethod
 public void UserLogin() throws InterruptedException{
  
  WebElement SignIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
  
  SignIn.click();
  Thread.sleep(5000); 
  WebDriverWait wait = new WebDriverWait(driver, 15);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")));
  
  driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div/div/div/div/a[1]")).click();
  
  Thread.sleep(5000); 
  
  driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[1]/div[1]/input")).sendKeys("devara.vikram@gmail.com");
  driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[2]/div[1]/input")).sendKeys("test@1234");
  driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div/div/form/div[3]/div/input")).click();
  System.out.println("Guest Logged-in successfully");
  Thread.sleep(10000); 
 }
 
 @AfterMethod
 public void UserLogout() throws InterruptedException{
  driver.findElement(By.xpath("html/body/div[4]/header/div/div/div/div[2]/div[2]/div[1]/img")).click();
  Thread.sleep(3000); 
   
  String Logout = driver.findElement(By.xpath("//*[@id='mCSB_1_container']/div/div/nav/ul/li[8]/a/b")).getText();
  System.out.println(Logout);
  driver.findElement(By.xpath("//*[@id='mCSB_1_container']/div/div/nav/ul/li[8]/a/b")).click();
  Thread.sleep(3000);
  System.out.println("Guest Logged out successfully");
  
  Assert3.assertEquals(Logout, "LOG OUT");
 }
 
 @Test(priority=1)
 public void Search1() throws InterruptedException{
  //driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/bangalore");
  Thread.sleep(5000);
  driver.findElement(By.xpath("//*[@id='global-search']")).sendKeys("Chandigarh");
  driver.findElement(By.xpath("//*[@id='search']")).click();
  Thread.sleep(5000);
  System.out.println("search successful");
//  String searchCity = driver.findElement(By.xpath("//*[@id='search_span']")).getText();
//  System.out.println(searchCity);
//  Assert.assertEquals(searchCity, "2 - Vacation Rentals in Chandigarh");
 }
 
 @Test(priority=2)
 public void r2b() throws InterruptedException{
  driver.findElement(By.xpath("//input[@name='PropertyForm[check_in]']")).sendKeys("2017-08-09");
  driver.findElement(By.xpath("//input[@name='PropertyForm[check_out]']")).sendKeys("2017-08-10");
  Thread.sleep(4000);
  driver.findElement(By.xpath("//div[@class='obj-right']")).click();
  Thread.sleep(3000);
  driver.findElement(By.xpath("//div[@class='btn-black']")).click();

  //Book Page
  driver.findElement(By.xpath("//input[@id='propertyform-guest_name']")).sendKeys("Vikram");
  driver.findElement(By.xpath("//input[@id='propertyform-male']")).sendKeys("1");
  //driver.findElement(By.xpath("//input[@id='not_traveller' and @type='checkbox']")).click();
  driver.findElement(By.xpath("//input[@id='book_now']")).click();
  Thread.sleep(6000);
  
  //Booking Success Page
  String Status = driver.findElement(By.xpath("//div[@class='account-activated-inner']/h1")).getText();
  System.out.println("Booking Status is ...."+Status);
  
  
  Assert3.assertEquals(Status, "Booking request sent successfully");
 }
 @Test
 public void Search2() throws InterruptedException{
  //driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/bangalore");
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id='global-search']")).sendKeys("Bangalore");
    driver.findElement(By.xpath("//*[@id='search']")).click();
    Thread.sleep(5000);
    Assert3.assertAll();
  
 }
}