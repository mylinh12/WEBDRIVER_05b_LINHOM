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

public class Topic_02_Xpath_Css_Locator_Part1And2And3 {
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
		// Cach 1: Assert True (dieu kien) => tuc la mong muon ket qua tra ve la True,
		// neu dung la tra ve TRUE thi se PASS
		boolean status = driver.findElement(By.id("advice-required-entry-email")).getText()
				.equals("This is a required field.");
		System.out.println("Status: " + status);
		Assert.assertTrue(status);

		// Cach 2: Assert Equal (so sanh dieu kien thuc te voi dieu kien mong doi)
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");

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
		String passwordEmptyMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
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
		String passwordInccorrectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
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

		// Step 03: input into a invalid email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation_13@gmail.com");

		// Step 04: input into a password value
		driver.findElement(By.id("pass")).sendKeys("456456");

		// Step 05: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 06: Verify error message
		String passwordInvalidMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(passwordInvalidMessage, "Invalid login or password.");

	}

	@Test
	public void TC_05_LoginWithEmailAndPasswordValid() throws Exception {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: input valid value into Email
		driver.findElement(By.cssSelector("#email")).sendKeys("automation_13@gmail.com");

		// Step 04: input valid value into Password
		driver.findElement(By.id("pass")).sendKeys("123123");

		// Step 05: Click Login button
		driver.findElement(By.id("send2")).click();

		// Step 06: Verify error message
		String dashboardMsg = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(dashboardMsg, "MY DASHBOARD");

		String helloMsg = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(helloMsg, "Hello, Automation Testing!");

		String contactInfo = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println("Contact = " + contactInfo);
		Assert.assertTrue(contactInfo.contains("Automation Testing"));
		Assert.assertTrue(contactInfo.contains("automation_13@gmail.com"));

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		Thread.sleep(3000);

		// driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log In']")).isDisplayed();
		Thread.sleep(3000);
	}

	@Test
	public void TC_06_CreateAnAccount() throws Exception {

		// Step 01: Go to url: http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02: Click on 'My Account' link to navigate to Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: Click 'Create an account' button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 04: Input value into First Name
		String firstName = "Linh";
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		// Step 05: Input value into Middle Name/intial
		String midName = "My";
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(midName);

		// Step 06: Input value into Last Name
		String lastName = "Truong";
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

		// Step 07: input into a email (random Email)
		String emailRandom = "linhmy" + randomEmail() + "@mailinator.com";
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailRandom);

		// Step 08: input into a password
		String password = "123456";
		driver.findElement(By.cssSelector("#password")).sendKeys(password);

		// Step 09: input into a confirmation password
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);

		// Step 10: Click Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Step 11: Verify successful message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		// Step 12: Verify First Name, Last Name, Email
		String expectedResult = "Hello, " + firstName + " " + midName + " " + lastName + "!";
		driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),
				expectedResult);

		// Step 13: Click button - Logout
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Step 14: Verify system navigate to homepage successful
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).isDisplayed();
		Thread.sleep(3000);
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.className("validate-password")).isDisplayed();
		Thread.sleep(3000);

	}

	@Test
	public void TC_09_Name() throws Exception {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("login[username]")).isDisplayed();
		Thread.sleep(3000);

	}

	@Test
	public void TC_10_TagName() {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Link number: " + links.size());

	}

	@Test
	public void TC_11_LinkText() throws Exception {
		// <a title="My Account"
		// href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// Tuyet doi: phai nhap du content la 'MY ACCOUNT' thi no moi tim dc
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed();
		Thread.sleep(3000);
	}

	@Test
	public void TC_12_PartialText() throws Exception {
		// <a title="My Account"
		// href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// Tuong doi: chi can nhap content la 'MY' hoac 'ACCOUNT' hoac 'Y ACCO' deu tim
		// dc
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("MY")).isDisplayed();
		driver.findElement(By.partialLinkText("ACCOUNT")).isDisplayed();
		driver.findElement(By.partialLinkText("Y ACCO")).isDisplayed();
		Thread.sleep(3000);
	}

	@Test
	public void TC_13_Css() throws Exception {
		// <button id="send2" class="button" name="send" title="Login" type="submit">
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#send2")).isDisplayed();
		driver.findElement(By.cssSelector("button[name='send']")).isDisplayed();
		Thread.sleep(3000);
	}

	@Test
	public void TC_14_Xpath() throws Exception {
		// <button id="send2" class="button" name="send" title="Login" type="submit">
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed();
		driver.findElement(By.xpath("//button[@name='send']")).isDisplayed();

		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
