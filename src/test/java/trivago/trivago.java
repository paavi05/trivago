package trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.JavascriptExecutor;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class trivago {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeTest
	public void setUp() {
		
        String path = Paths.get("").toAbsolutePath().toString();
        
		System.setProperty("webdriver.chrome.driver", ""+path+"//drivers//chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		js = (JavascriptExecutor) driver;

		vars = new HashMap<String, Object>();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@org.testng.annotations.Test
	public void booking() throws InterruptedException {
		driver.get("https://www.trivago.in/");
		driver.findElement(By.id("horus-querytext")).sendKeys("Goa");
		Thread.sleep(10000);
		driver.findElement(By.cssSelector(".btn--primary")).click();
		Thread.sleep(30000);
		List<WebElement> list = driver
				.findElements(By.xpath("/html/body/div[3]/main/div/div/div[4]/div/div/div[3]/section/ol/li"));

		System.out.println("-------------------------Printing Hotel Names-----------------");

		for (int i = 1; i <= list.size() - 1; i++) {
			String text = driver
					.findElement(By.xpath("/html/body/div[3]/main/div/div/div[4]/div/div/div[3]/section/ol/li[" + i
							+ "]/article/div[1]/div[2]/div/div[1]/h3/span"))
					.getText();
			System.out.println("Hotel Name:" + text);
		}
		Thread.sleep(10000);
		driver.findElement(By.id("mf-select-sortby")).click();
		{
			WebElement dropdown = driver.findElement(By.id("mf-select-sortby"));
			dropdown.findElement(By.xpath("//option[. = 'Rating only']")).click();
		}

		Thread.sleep(10000);

		String window = driver.getWindowHandle();

		driver.findElement(By.xpath(
				"/html/body/div[3]/main/div/div/div[4]/div/div/div[3]/section/ol/li[1]/article/div[1]/div[2]/section[2]/div/div[2]/button"))
				.click();

		Thread.sleep(10000);

		Set<String> windows = driver.getWindowHandles();

		for (String win : windows) {
			if (!(win.equals(window)))
				driver.switchTo().window(win);
		}

		Thread.sleep(30000);

		driver.findElement(By.cssSelector(".book_now")).click();
		driver.findElement(By.cssSelector(".proceed-payment:nth-child(2)")).click();

		Thread.sleep(10000);

		String val1 = driver
				.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/form/div[1]/div[1]"))
				.getText();
		
		  System.out.println("Valiation start");


		  // assertion
		  Assert.assertEquals(val1, "Please enter a valid mobile number");
		 
		  Assert.assertEquals(driver .findElement(By.xpath(
		  "/html/body/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/form/div[2]/div"
		  )).getText(), "Please enter your full name (e.g. John Snow)");
		  
		  Assert.assertEquals(driver .findElement(By.xpath(
		  "/html/body/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/form/div[3]/div"
		  )).getText(), "Please enter a valid email address");
		 
			System.out.println("Valiation end");
	  

	}
}
