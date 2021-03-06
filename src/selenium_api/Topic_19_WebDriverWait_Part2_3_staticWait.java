package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_WebDriverWait_Part2_3_staticWait {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_No_Wait() {
		// FAIL
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}
	
	@Test
	public void TC_02_Static_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		sleepInSeconds(5);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
