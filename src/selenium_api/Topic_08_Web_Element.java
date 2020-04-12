package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element {
	WebDriver driver;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {

		driver.get("https://demo.nopcommerce.com/");
		element = driver.findElement(By.xpath(".//input[@id='small-searchterms']"));

		element.clear();
		sleepInSeconds(2);

		element.sendKeys("Macbook Pro 2015");
		sleepInSeconds(2);

		element.sendKeys("Dell XPS 15");
		sleepInSeconds(2);

		String searchStoreValue = element.getAttribute("placeholder");
		System.out.println("Print: " + searchStoreValue);

		// get CSS Value (color, font, ...)
		WebElement searchBtn = driver.findElement(By.xpath("//*[@type='submit']"));
		String colorValue = searchBtn.getCssValue("background-color");
		System.out.println("Color value: " + colorValue);

		// get Tag Name
		String searchBtnTag = searchBtn.getTagName();
		System.out.println("TagName: " + searchBtnTag);
		
		// Kiem tra 1 element có hien thi len man hinh cho user thay hay ko
		//element.isDisplayed();
		
		// Kiem tra 1 element có tuong tac dc hay ko (vi co nhung element dang hien thi nhung o dang disable)
		//element.isEnabled();
		
		// Kiem tra 1 element co duoc chon hay chua (Radio button, checkbox)
		//element.isSelected();
		
		// AssertTrue (kiem tra dieu kien co la TRUE nhu mong oi hay ko), neu element do co hien thi tren GUI thi se tra ve True
		//Assert.assertTrue(element.isDisplayed());
		
		// Element nam trong Form thi co the dung ham submit()
		//element.submit();
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
