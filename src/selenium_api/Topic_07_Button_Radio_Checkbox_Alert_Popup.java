package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Button_Radio_Checkbox_Alert_Popup {
	// Online 05
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Button_clickByAPI() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click by API click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		String createAccountUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAccountUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Button_clickByJavascript() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click by Javascript
		clickElementByJavascript("//a[@title='Create an Account']");
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		String createAccountUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAccountUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_03_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		String dualZoneAirConditioning = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";

		clickElementByJavascript(dualZoneAirConditioning);
		sleepInSeconds(3);
		Assert.assertTrue(isElementSelected(dualZoneAirConditioning));

		uncheckTheCheckbox(dualZoneAirConditioning);
		sleepInSeconds(3);
		Assert.assertFalse(isElementSelected(dualZoneAirConditioning));

	}

	@Test
	public void TC_04_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		String twoPetrol147Kw = "//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input";

		clickElementByJavascript(twoPetrol147Kw);
		sleepInSeconds(3);
		Assert.assertTrue(isElementSelected(twoPetrol147Kw));

	}

	@Test
	public void TC_05_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();
		String alertJSMessage = alert.getText();
		Assert.assertTrue(alertJSMessage.equals("I am a JS Alert"));
		sleepInSeconds(3);

		alert.accept();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result']")).getText().equals("You clicked an alert successfully"));

		// JS Confirm
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().equals("I am a JS Confirm"));
		sleepInSeconds(3);
		
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result']")).getText().equals("You clicked: Cancel"));

		// JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		String valueMessage = "Practice Alert element";
		alert.sendKeys(valueMessage);
		sleepInSeconds(3);
		
		alert.accept();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result']")).getText().equals("You entered: " + valueMessage));

	}

	public void clickElementByJavascript(String locator) {

		if (!isElementSelected(locator)) {
			WebElement element = driver.findElement(By.xpath(locator));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
		}
	}

	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uncheckTheCheckbox(String locator) {
		if (isElementSelected(locator)) {
			clickElementByJavascript(locator);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
