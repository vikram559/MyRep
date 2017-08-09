package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class HomePage {
	//DesiredCapabilities capabilities = DesiredCapabilities.firefox();       
	WebDriver driver;
	       SoftAssert HomeAssert = new SoftAssert();
		  
	       @BeforeTest
	   	public void BrowserOpen(){
	   		//System.setProperty("webdriver.chrome.driver", "D:\\Vikram\\chromedriver_win32\\chromedriver.exe");
	    	   System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
	    	   //capabilities.setCapability("marionette", false);
	    	   driver = new FirefoxDriver();
	   		
	   		//driver = new ChromeDriver();
	   		driver.manage().window().maximize();
	   		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   		System.out.println("Browser Opened successfully");
	   		
	   		driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
	   		System.out.println("HeyBnb HomePage");
	   		String ShareText =driver.findElement(By.linkText("share your space")).getText();
	   		System.out.println(ShareText);
	   		String signIn = driver.findElement(By.xpath("//button[@class='dropbtn']")).getText();
	   		System.out.println("signIn");
	   		HomeAssert.assertEquals(signIn, "Sign In");
	   	
	   	}
	   	
	    	@AfterTest
	    	public void BrowserClose(){
	   		driver.quit();
	   		System.out.println("Browser Closed Successfully");
	   	}
	   	
	       
	       @Test(priority=1)
		   public void MainPage(){
		   
		        String MainText = driver.findElement(By.xpath("html/body/div[4]/div/section[1]/div/div[1]/h1")).getText();
				System.out.println(MainText);
				
				String SubTitle = driver.findElement(By.xpath("//div[@class='section-main-inner']/div/h2")).getText();
				System.out.println(SubTitle);
				
				String PlaceHolder = driver.findElement(By.xpath("//div[@class='main-search-autocomplete']/input[@placeholder='Where do you wanna go']")).getAttribute("placeholder");
				System.out.println(PlaceHolder);
				
				HomeAssert.assertEquals(MainText, "HOME AWAY FROM HOME");
				HomeAssert.assertEquals(SubTitle, "Rent with a local host and explore the world");
				HomeAssert.assertEquals(PlaceHolder, "Where do you wanna go");
				
				driver.findElement(By.xpath("//input[@name='check_in']")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String CheckIn = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/thead/tr/th[1]/span")).getText();
				System.out.println("CheckIn Calander is..."+CheckIn);
				
				HomeAssert.assertEquals(CheckIn, "Su");
				
				driver.findElement(By.xpath("//input[@name='check_out']")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String CheckOut = driver.findElement(By.xpath("//th[@class='ui-datepicker-week-end']/span[@title='Sunday']")).getText();
				System.out.println("CheckOut Calander is..."+CheckOut);
				
				HomeAssert.assertEquals(CheckOut, "Su");
				
				driver.findElement(By.xpath("//div[@class='wrap-select main-search-quantity']")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String Guests = driver.findElement(By.xpath("//ul[@class='ui-multiselect-checkboxes ui-helper-reset']/li[2]/label/span")).getText();
				System.out.println("Gusets are ..."+Guests);
				
				HomeAssert.assertEquals(Guests, "1 Guest");
				
				
	       }		
			@Test(priority=2)
			public void StayAndExplore(){
				//Stay and Explore
			
				System.out.println("checking Stay and Explore");
				driver.findElement(By.linkText("view more")).click();
				String destination =driver.findElement(By.xpath("//section[@class='blog-main']/ul/li/span")).getText();
				System.out.println(destination);
				HomeAssert.assertEquals(destination, "California");
			}
			@Test(priority=3)  //Host and Earn
			public void HostAndEarn() throws InterruptedException{	
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				System.out.println("checking Host and Earn");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[@id='mCSB_2_container']/a")).click();
				Thread.sleep(7000);
				String HostText =driver.findElement(By.xpath("//div[@class='text-block']/h1")).getText();
				System.out.println(HostText);
				HomeAssert.assertEquals(HostText, "How do I List My Property on HeyBnb");
				
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				Thread.sleep(5000);
			}	
			@Test(priority=5)  //Footer links
			public void FooterLinks(){	
				WebElement Footer = driver.findElement(By.xpath("//div[@class='footer-inner']"));
				List<WebElement> Links = Footer.findElements(By.tagName("a"));
				System.out.println("Total links in footer---->"+Links.size());
				
				for(int i=0;i<Links.size();i++){
					System.out.println(Links.get(i).getText()+"-------->"+Links.get(i).isDisplayed());
					Links.get(i).click();
					System.out.println(driver.getTitle());
					driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
					Footer = driver.findElement(By.xpath("//div[@class='footer-inner']"));
					Links = Footer.findElements(By.tagName("a"));
								}
			}	

			@Test(priority=4)  //Top Destinations
			public void TopDestinations() throws InterruptedException{	
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				//Italy Search
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Italy']"))); 
				
				String City1 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Italy']")).getText();
				System.out.println(City1);
				HomeAssert.assertEquals(City1, "Italy");
				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Italy']")).click();
				String ItalySearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
				System.out.println(ItalySearch);
				HomeAssert.assertEquals(ItalySearch, "Popular Listings");
				
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				// Goa Search
				//WebDriverWait wait2 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Goa']"))); 
				
				String City2 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Goa']")).getText();
				System.out.println(City2);
				HomeAssert.assertEquals(City2, "Goa");
				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Goa']")).click();
				String GoaSearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
				System.out.println(GoaSearch);
				HomeAssert.assertEquals(ItalySearch, "Popular Listings");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				
				// Srilanka Search
				//WebDriverWait wait3 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Sri+Lanka']"))); 
				
				String City3 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Sri+Lanka']")).getText();
				System.out.println(City3);
				HomeAssert.assertEquals(City3, "SriLanka");
				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/search?location=Sri+Lanka']")).click();
//				String SrilankaSearch =driver.findElement(By.xpath("//*[@id='search_span']/h1/b")).getText();
//				System.out.println(SrilankaSearch);
				//HomeAssert.assertEquals(SrilankaSearch, "1 - Vacation Rentals in Sri Lanka");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				// Bangalore Search
				//WebDriverWait wait4 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Bangalore']"))); 
				
				String City4 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Bangalore']")).getText();
				System.out.println(City4);
				HomeAssert.assertEquals(City4, "Bangalore");
//				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Bangalore']")).click();
//				String BangaloreSearch =driver.findElement(By.xpath("//*[@id='search_span']/h1/b")).getText();
//				System.out.println(BangaloreSearch);
				//HomeAssert.assertEquals(BangaloreSearch, "Showing");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				// Paris Search
				//WebDriverWait wait5 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Paris']"))); 
				
				String City5 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Paris']")).getText();
				System.out.println(City5);
				HomeAssert.assertEquals(City5, "Paris");
				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Paris']")).click();
				String ParisSearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
				System.out.println(ParisSearch);
				HomeAssert.assertEquals(ParisSearch, "Popular Listings");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				// Singapore Search
				//WebDriverWait wait6 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Singapore']"))); 
				
				String City6 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Singapore']")).getText();
				System.out.println(City6);
				HomeAssert.assertEquals(City6, "Singapore");
//				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Singapore']")).click();
//				String SingaporeSearch =driver.findElement(By.xpath("//*[@id='search_span']/h1/b")).getText();
//				System.out.println(SingaporeSearch);
				//HomeAssert.assertEquals(SingaporeSearch, "Showing");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				

				// Spain Search
			//	WebDriverWait wait7 = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Spain']"))); 
				
				String City7 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Spain']")).getText();
				System.out.println(City7);
				HomeAssert.assertEquals(City7, "Spain");
				driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Spain']")).click();
				String SpainSearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
				System.out.println(SpainSearch);
				HomeAssert.assertEquals(SpainSearch, "Popular Listings");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				// Thailand Search
				//	WebDriverWait wait9 = new WebDriverWait(driver, 40);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Thailand']"))); 
					
					String City9 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Thailand']")).getText();
					System.out.println(City9);
					HomeAssert.assertEquals(City9, "Thailand");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Thailand']"))).click();
					String ThailandSearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
					System.out.println(ThailandSearch);
					HomeAssert.assertEquals(ThailandSearch, "Popular Listings");
				
				
				
				// Florida Search
			//	WebDriverWait wait8 = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Florida']"))); 
				
				String City8 =driver.findElement(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Florida']")).getText();
				System.out.println(City8);
				HomeAssert.assertEquals(City8, "Florida");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blog-main-item-gallery-slide active' and @href='http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com/Florida']"))).click();
				String FloridaSearch =driver.findElement(By.xpath("//div[@class='filter-result']/h1")).getText();
				System.out.println(FloridaSearch);
				HomeAssert.assertEquals(FloridaSearch, "Popular Listings");
				driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
				
				
				
				
				HomeAssert.assertAll();
					
					
				}
				@Test(priority=5)
				public void Blogs(){
					driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.findElement(By.xpath("//a[@href='/blog/dont-miss-the-architectural-grandeur-of-barcelona']")).click();
					String Blog1 = driver.findElement(By.xpath("//div[@id='blog_post_title']/h1")).getText();
					System.out.println("Title of the 1st blog is-------"+Blog1);
					HomeAssert.assertEquals(Blog1, "Don’t miss the architectural grandeur of Barcelona");
					driver.findElement(By.xpath("//img[@src='/dist/images/logo.svg']")).click();
					driver.findElement(By.xpath("//a[@href='/blog/how-does-travelling-help-you-empower-yourself']")).click();
					String Blog2 = driver.findElement(By.xpath("//div[@id='blog_post_title']/h1")).getText();
					System.out.println("Title of the 2nd Blog is-----"+Blog2);
					HomeAssert.assertEquals(Blog2, "How does travelling help you empower yourself?");
					
				}

}


	
