package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_WebDriverWait_Part3_explicitlyWait {
	WebDriver driver;
	WebDriverWait explicitlyWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		explicitlyWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// 1 - Click vao START button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// 2 - Loading icon hien thi (bien mat sau 5s)

		// Cho trong vong 10s de Hello World text hien thi
		explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

		// Hello World text dc hien thi ~ Loading icon bien mat (02 s)
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	//@Test
	public void TC_02_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// 1 - Click vao START button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// 2 - Loading icon hien thi (bien mat sau 5s)
		explicitlyWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

		// Hello World text dc hien thi ~ Loading icon bien mat (2 s)
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	//@Test
	public void TC_03_ImplicitlyWait_Ajax_Loading_Fail() {
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		driver.findElement(By.xpath("//td[@title='Thursday, April 23, 2020']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Thursday, April 23, 2020']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "Thursday, April 23, 2020");
		
	}
	
	@Test
	public void TC_04_ExplicitlyWait_Ajax_Loading() {
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		WebElement selectedDateText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectedDateText.getText().trim(), "No Selected Dates to display.");
		
		driver.findElement(By.xpath("//td[@title='Thursday, April 23, 2020']")).click();
		
		// Wait for Loading icon invisible
		explicitlyWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		// wait for selected visible
		explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'rcSelected')]//a[text()='23']")));
		
		//Check cai item minh vua click no dc highligh (tuc la selected)
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'rcSelected')]//a[text()='23']")).isDisplayed());
		
		selectedDateText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectedDateText.getText().trim(), "Thursday, April 23, 2020");

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
