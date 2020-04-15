package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Custom_Dropdownlist_Jquery_Angular {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Dropdownlist_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// dropdown list: 'Select a speed'
		System.out.println("\nSelect a speed:");
		String fasterValue = "Faster";
		selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", fasterValue);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[text()='" + fasterValue + "']")).isDisplayed());

		// dropdown list: 'Select a number'
		System.out.println("\nSelect a number:");
		String numValue = "19";
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", numValue);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='" + numValue + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Dropdownlist_Angular() {
		driver.get("https://material.angular.io/components/select/examples");
		// dropdown list: 'Favorite food'
		System.out.println("\nFavorite food:");
		String tacosValue = "Tacos";
		selectCustomDropdownList("//mat-select[@id='mat-select-0']//div[@class='mat-select-arrow-wrapper ng-tns-c153-4']", "//span[@class='mat-option-text']", tacosValue);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()= '" + tacosValue + "']")).isDisplayed());

		// dropdown list: 'State'
		System.out.println("\nState:");
		String washingtonValue = "Washington";
		selectCustomDropdownList("//mat-select[@id='mat-select-5']//div[@class='mat-select-arrow-wrapper ng-tns-c153-18']", "//mat-option", washingtonValue);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()= '" + washingtonValue + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Dropdownlist_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		// dropdown list: 'A prettier way to display select boxes'
		System.out.println("\nA prettier way to display select boxes:");
		String secondValue = "Second Option";
		selectCustomDropdownList("//div[@class='btn-group']/li[@class='dropdown-toggle']", "//div[@class='btn-group']/ul//li", secondValue);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(), '" + secondValue + "')]")).isDisplayed());

	}

	public void selectCustomDropdownList(String dropdown, String listItems, String valueItem) {
		// Click on dropdown list
		driver.findElement(By.xpath(dropdown)).click();

		// Get all elements of dropdown list
		List<WebElement> allItems = driver.findElements(By.xpath(listItems));

		// Wait all elements are displayed
		wait.until(ExpectedConditions.visibilityOfAllElements(allItems));

		// Get text for all items
		for (WebElement item : allItems) {
			System.out.println(item.getText().trim());
			if (item.getText().trim().equals(valueItem)) {
				// Scroll to that item and click
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSeconds(3);

				item.click();
				sleepInSeconds(3);
				break;

			}
		}
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
