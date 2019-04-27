package trivago;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class sample {
	
	WebDriver driver = null;
	
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", "C://Users//pvija//driver//chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void bookingCancelHotelTest() {
		System.out.println("---------------Hotel Booking Trivago-----------------");
		
		driver.get("https://www.trivago.in/");
	}
	
	
	@AfterTest
	public void afterTest() {
		
		driver.close();
	}

}
