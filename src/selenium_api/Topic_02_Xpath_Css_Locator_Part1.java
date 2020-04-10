package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator_Part1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginWithUserPassEmpty() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 04: Verify error message at 2 fields
		String userameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText().trim();
		Assert.assertEquals(userameEmptyMessage, "This is a required field.");

		String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText().trim();
		Assert.assertEquals(passwordEmptyMessage, "This is a required field.");

	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input into a invalid email
		driver.findElement(By.cssSelector("#email")).sendKeys("dsdasdfds@dsfssd");

		// Step 04: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 05: Verify error message
		String passwordEmptyMessage = driver.findElement(By.id("advice-validate-email-email")).getText().trim();
		Assert.assertEquals(passwordEmptyMessage,
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Charactors() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input into a invalid email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation@mailinator.com");

		// Step 04: input into a password value
		driver.findElement(By.id("pass")).sendKeys("123");

		// Step 05: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 06: Verify error message
		String passwordInccorrectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText().trim();
		Assert.assertEquals(passwordInccorrectMessage,
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_LoginWithPasswordInvalid() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input into a correct email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation_13@gmail.com");

		// Step 04: input into a password with invalid value
		driver.findElement(By.id("pass")).sendKeys("456456");

		// Step 05: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 06: Verify error message
		String passwordInvalidErrorMessage = driver
				.findElement(By.xpath("//html[@id='top']//li[@class='error-msg']//span")).getText().trim();
		Assert.assertEquals(passwordInvalidErrorMessage, "Invalid login or password.");

	}

	//@Test
	public void TC_05_LoginWithEmailAndPasswordValid() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input into a correct email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation_13@gmail.com");

		// Step 04: input into a password with invalid value
		driver.findElement(By.id("pass")).sendKeys("123123");

		// Step 05: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 06_1: Verify 01
		String pageTitle = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText().trim();
		Assert.assertEquals(pageTitle, "MY DASHBOARD");

		// Step 06_2: Verify 02
		String welcomeMessage = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText().trim();
		Assert.assertEquals(welcomeMessage, "Hello, Automation Testing!");

		// Step 06_3: Verify 03
		String contactName = driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(), 'Automation Testing')]"))
				.getText().trim();
		Assert.assertEquals(contactName, "Automation Testing");

		// Step 06_4: Verify 04
				String contactEmail = driver
						.findElement(By.xpath("//div[@class='box-content']//p[contains(text(), 'automation_13@gmail.com')]"))
						.getText().trim();
				Assert.assertEquals(contactEmail, "automation_13@gmail.com");

	}

	@Test
	public void TC_06_CreateAnAccount() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		// driver.findElement(By.xpath("//div[@class='footer']//a[@title='My
		// Account']")).click();

		// Step 03: input into a invalid email
		// driver.findElement(By.cssSelector("#email")).sendKeys("automation" +
		// randomEmail() + "@mailinator.com");

	}

	public int randomEmail() {
		Random rand = new Random();
		int number = rand.nextInt(999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
