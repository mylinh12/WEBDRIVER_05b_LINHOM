package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Popup_Iframe {
	// Online 14
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Popup() {
		driver.get("https://www.javacodegeeks.com/");

		// Muon popup hien tro lai thi cai browser cua minh nho CLEAR CACHE di truoc nha
		// Neu hien popup thi close popup di
		if (isElementDisplayed("//div[@data-title='YouTube Popup FPT']")) {
			System.out.println("Go to if statement");
			driver.findElement(By.xpath("//a[text()='Close Popup']/parent::div")).click();
			sleepInSeconds(3);
		}

		// check popup un-displayed
		Assert.assertFalse(driver.findElement(By.xpath("//div[@data-title='YouTube Popup FPT']")).isDisplayed());
		sleepInSeconds(3);

		driver.findElement(By.xpath("//a[text()='Android']")).click();
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Android']")).isDisplayed());
		sleepInSeconds(3);

	}

	@Test
	public void TC_02_Iframe() {
		driver.get("https://kyna.vn/");

		// Chuyen qua iframe roaiiiii
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));

		// Thao tac cac element trong iframe tren dc roi ne
		String kynaTextTitle = driver.findElement(By.xpath("//a[@title='Kyna.vn']")).getText();
		System.out.println(kynaTextTitle);

		// Get likes number
		String likeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div//following-sibling::div")).getText();
		System.out.println("Like number: " + likeNumber);

		// Chuyen qua trong homePage lai (tuc la thoat khoi iframe, tuc la back ve trang thai MainPage/HomePage)
		driver.switchTo().defaultContent();

		// Thao tac voi WebChat iframe
		//driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']//iframe")));
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")).sendKeys("Automation FC");
		sleepInSeconds(3);
		
		action.sendKeys(driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")), Keys.ENTER).perform();
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//form[@ng-submit='editUserInfo()']")).isDisplayed());

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Java");
		sleepInSeconds(3);

		driver.findElement(By.cssSelector(".search-button")).click();
		sleepInSeconds(5);

	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(String locator) {
		try {
			// Truong hop 1: Element hien thi tren GUI + co trong DOM
			// Truong hop 2: Element ko hien thi tren GUI + co trong DOM
			// Truong hop 3: Element ko hien thi tren GUI + ko co trong DOM (truong hop 3: se fail code khi thuc hien findElement)
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
