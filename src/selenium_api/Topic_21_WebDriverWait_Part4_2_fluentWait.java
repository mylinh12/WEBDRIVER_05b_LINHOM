package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_21_WebDriverWait_Part4_2_fluentWait {
	WebDriver driver;
	WebElement element;
	long timeout = 8000;
	long interval = 500;

	// Khai bao FluentWait co tham so la WebDriver
	FluentWait<WebDriver> fluentDriver;

	// Khai bao FluentWait co tham so la WebElement
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fluent_WebDriver_Fail() {
		driver.get("https://www.facebook.com/");

		fluentDriver = new FluentWait<WebDriver>(driver);

		// Timeout nay la Tong thoi gian check la 15s
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
				// Tan so moi 0.3s check 1 lan cho den khi het timeout 15s o tren
				.pollingEvery(300, TimeUnit.MILLISECONDS)
				// Neu gap exception la find ko thay element se bo qua
				.ignoring(NoSuchElementException.class);

		// Function<T, R>
		// T se lam tham so cua ham apply
		// R se lam kieu tra ve cua ham apply
		WebElement feelingLuckyButton = fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@name='Wrong_Locator']"));
			}
		});

		// neu tim thay thi click, ko tim thay thi thoi
		feelingLuckyButton.click();

	}

	// Tao fluentWait co tham so la WebElement
	@Test
	public void TC_02_Fluent_WebElement_Pass() {

		driver.get("https://automationfc.github.io/fluent-wait/");

		element = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

		fluentElement = new FluentWait<WebElement>(element);

		// Timeout nay la Tong thoi gian check la 15s
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
				// Tan so moi 0.1s check 1 lan cho den khi het timeout 15s o tren
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				// Neu gap exception la find ko thay element se bo qua
				.ignoring(NoSuchElementException.class);

		// Kiem tra dieu kien
		boolean status = fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				// Kiem tra dieu kien countdount = 00
				boolean flag = element.getText().endsWith("00");
				System.out.println("Time = " + element.getText());
				// return cho gia tri function applyl
				return flag;
			}
		});

		System.out.println("Status = " + status);
	}

	// Ap dung FluentWait vao thuc te
	@Test
	public void TC_03_Pass() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(), 'Start')]")).click();
		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(6, TimeUnit.SECONDS)
		.pollingEvery(500, TimeUnit.MILLISECONDS)
		.ignoring(NoSuchElementException.class);

		WebElement helloWorldText = fluentDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4"));
			}
		});

		Assert.assertEquals(helloWorldText.getText(), "Hello World!");

	}

	// Dung Fluent gon gang de hieu hon
	@Test
	public void TC_04_Custom_FluentWait_Element() {
		By helloWorldTextBy = By.xpath("//div[@id='finish']/h4");

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(), 'Start')]")).click();

		Assert.assertEquals(waitedElement(helloWorldTextBy).getText(), "Hello World!");
	}

	public WebElement waitedElement(By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(interval, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
