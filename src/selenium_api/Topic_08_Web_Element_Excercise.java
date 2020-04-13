package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_Excercise {
	WebDriver driver;
	By emailTextboxBy = By.id("mail");
	By educationTextareaBy = By.id("edu");
	By ageUnder18rRadioBy = By.id("under_18");
	By passwordTextboxBy = By.id("password");
	By developmentCheckboxBy = By.id("development");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
//		System.setProperty("webdriver.ie.driver", "\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
//		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Check_Display() {

		// Email textbox
		if (isElementDisplayed(emailTextboxBy)) {
			sendKeyToElement(emailTextboxBy, "Automation Testing");
		}

		// Education textarea
		if (isElementDisplayed(educationTextareaBy)) {
			sendKeyToElement(educationTextareaBy, "Automation Testing");
		}

		// Age under 18 radio button
		if (isElementDisplayed(ageUnder18rRadioBy)) {
			clickToElement(ageUnder18rRadioBy);
		}
	}

	@Test
	public void TC_02_Check_Enable() {

		// expected: enable
		Assert.assertTrue(isElementEnabled(ageUnder18rRadioBy));

		// expected: disable
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
	}

	@Test
	public void TC_03_Check_Selected() {
		
		// Click to select
		clickToElement(developmentCheckboxBy);
		
		// Development checkbox is selected
		Assert.assertTrue(isElementSelected(developmentCheckboxBy));
		
		// Click to de-select
		clickToElement(developmentCheckboxBy);
		
		// Development checkbox is de-selected
		Assert.assertFalse(isElementSelected(developmentCheckboxBy));
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element --- " + by + " --- is displayed");
			return true;
		} else {
			System.out.println("Element --- " + by + " --- is un-displayed");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element --- " + by + " --- is enable");
			return true;
		} else {
			System.out.println("Element --- " + by + " --- is disable");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element --- " + by + " --- is selected");
			return true;
		} else {
			System.out.println("Element --- " + by + " --- is de-selected");
			return false;
		}
	}

	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
