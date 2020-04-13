package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropdownList_Textbox_TextArea {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_DropdownList() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Select jobRole1 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		Assert.assertFalse(jobRole1.isMultiple());

		jobRole1.selectByVisibleText("Automation Testing");
		Assert.assertEquals(jobRole1.getFirstSelectedOption().getText(), "Automation Testing");
		sleepInSeconds(3);

		jobRole1.selectByValue("manual");
		Assert.assertEquals(jobRole1.getFirstSelectedOption().getText(), "Manual Testing");
		sleepInSeconds(3);

		jobRole1.selectByIndex(3);
		Assert.assertEquals(jobRole1.getFirstSelectedOption().getText(), "Mobile Testing");
		sleepInSeconds(3);

		int jobItem = jobRole1.getOptions().size();
		Assert.assertEquals(jobItem, 10);

	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
