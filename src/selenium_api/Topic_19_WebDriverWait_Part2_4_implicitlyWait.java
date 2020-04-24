package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_WebDriverWait_Part2_4_implicitlyWait {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_ImplicitlyWait_Pass() {

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// System can toi 5s de loading icon bien mat

		// Den giay 5.5s thi da thay element 'Hello World' text xuat hien

		// Implicitly Wait chi apply cho nhung ham findElement/ findElements
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_02_ImplicitlyWait_Failed() {

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// System can toi 5s de loading icon bien mat

		// Den giay 5.5s thi da thay element 'Hello World' text xuat hien

		// Implicitly Wait chi apply cho nhung ham findElement/ findElements
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_() {

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
