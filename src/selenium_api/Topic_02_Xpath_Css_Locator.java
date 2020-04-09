package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CheckUrlAndTitle() {
		/*Xpath: */
		//01
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("mylinh@mailinator.comm");
		
		//02
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email validation-failed']")).sendKeys("mylinh@mailinator.com");
		
		//03
		driver.findElement(By.xpath(".//input[@title='Email Address']")).sendKeys("mylinh@mailinator.com");
		
		//04
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("mylinh@mailinator.com");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
