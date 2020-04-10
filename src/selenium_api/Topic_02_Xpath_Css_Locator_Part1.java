package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public void TC_02_LoginWithUserPassEmpty() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 04: Verify error message at 2 fields
		String userameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(userameEmptyMessage, "This is a required field.");

		String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordEmptyMessage, "This is a required field.");

	}

	@Test
	public void TC_03_LoginWithEmailInvalid() {

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
		String passwordEmptyMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(passwordEmptyMessage,
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_04_LoginWithPasswordLessThan6Charactors() {

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
		String passwordInccorrectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordInccorrectMessage,
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_06_CreateAnAccount() {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input into a invalid email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation" + randomEmail() + "@mailinator.com");

	}

	public int randomEmail() {
		Random rand = new Random();
		int number = rand.nextInt(999999);
		return number;
	}

	@Test
	public void TC_07_Id() throws Exception {

		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Tim single element: finndElement
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("automationfc.vn@gmail.com");
		emailTextbox.isDisplayed();
		Thread.sleep(3000);

	}

	@Test
	public void TC_08_Class() throws Exception {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.className("input-text required-entry validate-password")).sendKeys("123456");
		Thread.sleep(3000);

	}

	@Test
	public void TC_09_Name() throws Exception {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.name("login[username]")).sendKeys("automationfc.vn@gmail.com]");
		Thread.sleep(3000);

	}

	@Test
	public void TC_10_TagName() throws Exception {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Link number: " + links.size());

	}

	@Test
	public void TC_11_LinkText() throws Exception {
		// <a title="My Account"
		// href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// Tuyet doi: phai nhap du content la 'MY ACCOUNT' thi no moi tim dc
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed();
		Thread.sleep(3000);
	}

	@Test
	public void TC_12_PartialText() throws Exception {
		// <a title="My Account"
		// href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// Tuong doi: chi can nhap content la 'MY' hoac 'ACCOUNT' hoac 'Y ACCO' deu tim dc
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.linkText("MY")).isDisplayed();
		driver.findElement(By.linkText("ACCOUNT")).isDisplayed();
		driver.findElement(By.linkText("Y ACCO")).isDisplayed();
		Thread.sleep(3000);
	}
	
	
	@Test
	public void TC_13_Css() throws Exception {
		//<button id="send2" class="button" name="send" title="Login" type="submit">
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.cssSelector("#send2")).isDisplayed();
		driver.findElement(By.cssSelector("button[name='send']")).isDisplayed();
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_14_Xpath() throws Exception {
		//<button id="send2" class="button" name="send" title="Login" type="submit">
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed();
		driver.findElement(By.xpath("//button[@name='send']")).isDisplayed();
		
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
