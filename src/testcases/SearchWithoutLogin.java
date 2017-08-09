package testcases;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchWithoutLogin {
	WebDriver driver;
    SoftAssert SearchAssert = new SoftAssert();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    
        
       @BeforeTest   
       public void DBConnection() throws SQLException{
       //Connection conn = null;
		
		String url = "jdbc:mysql://52.35.179.203:3306/";
		String driver = "com.mysql.jdbc.Driver";
		String dbName = "heybnbtestingdb";
		String username = "root";
		String password = "HeyBnb@321";
		
		try{
			
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,username,password);
			System.out.println(conn.isClosed());
			
			//Statement st = conn.createStatement();
		
		}catch(Exception e){
			e.printStackTrace();
			
		
		}

	}
     	@Test(priority=4)
	      public void BrowserClose(){
	    	driver.quit();
	    	System.out.println("Browser Closed Successfully");
	    	
	}
     	@AfterTest
     	public void DbClose(){
     		try{
     			if(rs!=null)
     				rs.close();
     			if(pstmt!=null)
     				pstmt.close();
     			
     			if((conn!=null) && (!conn.isClosed())){
     				conn.close();
     			}
     			}catch(SQLException e){
     				e.printStackTrace();
     				
     			}
     		
     	}
    
        @Test(priority=1)
    	public void BrowserOpen(){
   		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
        	System.setProperty("webdriver.gecko.driver", "D:\\Vikram\\geckodriver-v0.16.1-win64\\geckodriver.exe");
   		driver = new FirefoxDriver();
   		
   		//WebDriver driver = new ChromeDriver();
   		driver.manage().window().maximize();
   		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
   		System.out.println("Browser Opened successfully");
   		
   		
   		driver.get("http://ec2-52-35-179-203.us-west-2.compute.amazonaws.com");
   		System.out.println("HeyBnb HomePage");
   		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File("D:\\Screenshots\\HomePage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   		
   	}
   	
    
    	@Test(priority=2)
    	public void BasicSearchFromHome() throws SQLException{
    		driver.findElement(By.xpath("//input[@id='global-search']")).sendKeys("india");
    		driver.findElement(By.xpath("//input[@id='search' and @name='search' and @type='submit']")).click();
    		String indiaSearch =driver.findElement(By.xpath("//div[@class='filter-left']/div/h1/div/b[2]")).getText();
    		
    		
    		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM property WHERE country_code = ? AND listed = ?");
			pstmt.setString(1, "IN");
			pstmt.setInt(2, 1);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1)+"-----"+rs.getString(2)+"-------"+rs.getString(3));
				
			}
            
    		System.out.println("For India Search resultrs are...."+indiaSearch);
    		
    		
    		//Search from search results page
    		System.out.println("Search from search results page");
    		driver.findElement(By.xpath("//input[@id='global-search' and @name='location']")).clear();
    		driver.findElement(By.xpath("//input[@id='global-search' and @name='location']")).sendKeys("india");
    		driver.findElement(By.xpath("//input[@id='global-search' and @name='location']")).sendKeys(Keys.ENTER);
    		String Search =driver.findElement(By.xpath("//div[@class='summary']")).getText();
    		System.out.println("For India Search resultrs are...."+Search);
    		
    		
    		
    		
    	}
    	    	    	
    	@Test(priority=3) //This is for sorting results
    	public void Sort() throws InterruptedException{
    		Select SortBy = new Select(driver.findElement(By.name("short_by")));
    		SortBy.selectByValue("1");
    		Thread.sleep(500);
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		// Now you can do whatever you need to do with it, for example copy somewhere
    		try {
    			FileUtils.copyFile(scrFile, new File("D:\\Screenshots\\Low-High.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("Sorted By:Price - Low to High");
    		SortBy.selectByValue("2");
    		Thread.sleep(500);
    		
    		File sort1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		// Now you can do whatever you need to do with it, for example copy somewhere
    		try {
    			FileUtils.copyFile(sort1, new File("D:\\Screenshots\\High-Low.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("Sorted By:Price - High to Low");
    		SortBy.selectByValue("3");
    		Thread.sleep(500);
    		File sort2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		// Now you can do whatever you need to do with it, for example copy somewhere
    		try {
    			FileUtils.copyFile(sort2, new File("D:\\Screenshots\\Faster-Response.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("Sorted By:Faster Response");
    		SortBy.selectByValue("4");
    		Thread.sleep(500);
    		File sort3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		// Now you can do whatever you need to do with it, for example copy somewhere
    		try {
    			FileUtils.copyFile(sort3, new File("D:\\Screenshots\\Rating.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("Sorted By:Rating");

    	    				
    	}
//    	@Test
//    	public void ApplyFilter(){
//    	
//    		SearchAssert.assertAll();  
//    	}
    	 
    	
}
