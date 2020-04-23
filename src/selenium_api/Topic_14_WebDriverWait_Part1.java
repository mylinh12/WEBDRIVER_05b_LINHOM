package selenium_api;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_WebDriverWait_Part1 {
	WebDriver driver;
	WebDriverWait explicitlyWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitlyWait = new WebDriverWait(driver, 17);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Visible_Pass_01() {
		System.out.println("TC_01_Visible_Pass_01:");
		System.out.println("Start step get: " + getDateTimeNow());
		driver.get("https://www.facebook.com/");

		System.out.println("Start step wait: " + getDateTimeNow());
		// Wait for find 'Email textbox' displayed = visible
		// co trong DOM , va co xuat hien tren UI
		explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		System.out.println("End step wait: " + getDateTimeNow());

		// Check system really displays element on UI
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	// @Test
	public void TC_01_Visible_Fail_02() {
		driver.get("https://www.facebook.com/");

		// Wait for finding 'Confirm Email textbox' displayed = visible, but:
		// Co trong DOM nhung ko xuat hien tren UI, so, this step will fail & throw exception
		explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		// Check system really displays element on UI => FAIL, becasuse it can not find the element
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}

	@Test
	public void TC_02_Invisible_Pass_01() {
		System.out.println("TC_02_Invisible_Pass_01:");
		System.out.println("Start step get: " + getDateTimeNow());
		driver.get("https://www.facebook.com/");

		System.out.println("Start step wait: " + getDateTimeNow());
		// ko xuat hien tren UI, nhung co xuat hien trong DOM
		explicitlyWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		System.out.println("End step wait: " + getDateTimeNow());
		
	}

	@Test
	public void TC_02_Invisible_Pass_02() {
		System.out.println("TC_02_Invisible_Pass_02:");
		System.out.println("Start step get: " + getDateTimeNow());
		driver.get("https://www.facebook.com/");

		System.out.println("Start step wait: " + getDateTimeNow());
		// ko xuat hien tren UI, ko xuat hien trong DOM
		explicitlyWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email_address']")));
		System.out.println("End step wait: " + getDateTimeNow());
		
	}

	@Test
	public void TC_02_Invisible_Fail_03() {
		System.out.println("TC_02_Invisible_Fail_03:");
		System.out.println("Start step get: " + getDateTimeNow());
		driver.get("https://www.facebook.com/");
		
		System.out.println("Start step wait: " + getDateTimeNow());
		// co xuat hien tren UI, ma minh muon system wait cai element no bien mat => wait se FAIL , vi element do luon luon dang xuat hien tren UI
		explicitlyWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
		System.out.println("End step wait: " + getDateTimeNow());
		
	}
	
	public String getDateTimeNow() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("End afterClass wait: " + getDateTimeNow());
		driver.quit();
	}

}
