package selenium_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_WebDriverWait_Part4_1_mixing_implicitWait_explicitWait {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Element_Found() {
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Explicit Wait
		waitExplicit = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");
		System.out.println(" --------- STEP 01 - Start TC_01_Element_Found: " + new Date() + " --------- ");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath(".//input[@id='email']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("switch to catchc element!");
		}
		System.out.println(" --------- STEP 01 - END TC_01_Element_Found: " + new Date() + " --------- ");

		System.out.println(" --------- STEP 02 - Start TC_01_Element_Found: " + new Date() + " --------- ");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastname']")));
		} catch (Exception ex) {
			System.out.println("switch to catchc element!");
		}

		System.out.println(" --------- STEP 02 - END TC_01_Element_Found: " + new Date() + " --------- ");
	}

	@Test
	public void TC_02_Element_Not_Found_Timeout_Equal() {
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		// Explicit Wait
		waitExplicit = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");
		System.out.println(" --------- STEP 01 - Start TC_02_Element_Not_Found_Timeout_Equal: " + new Date() + " --------- ");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("//input[@id='Wrong_Locator']"))));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(" --------- STEP 01 - END TC_02_Element_Not_Found_Timeout_Equal: " + new Date() + " --------- ");

		System.out.println(" --------- STEP 02 - Start TC_02_Element_Not_Found_Timeout_Equal: " + new Date() + " --------- ");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Wrong_Locator']")));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println(" --------- STEP 02 - END TC_02_Element_Not_Found_Timeout_Equal: " + new Date() + " --------- ");
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
