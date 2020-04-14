package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropdownList_Textbox_TextArea {
	WebDriver driver;
	String name, dob, address, city, pin, state, phone, email, password, customerId = null, newAddress, newCity;

	// BY ELEMENT VARIABLE
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dobTextbox = By.xpath("//input[@name='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By customerIDTextbox = By.xpath("//td[text()='Customer ID']//following-sibling::td");

	@BeforeClass
	public void beforeClass() {

//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// DATA TEST
		name = "Automation Test";
		dob = "1980-04-03";
		address = "123 Nguyen Ai Quoc";
		city = "Ho Chi Minh";
		state = "Cam Le";
		pin = "123456";
		phone = "0901232325";
		email = "LinhOng" + randomEmail() + "@mailinator.com";
		password = "123123";
		customerId = "94450";
		newAddress = "234 Le Duan";
		newCity = "Da Nang";

	}

	//@Test
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
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr254506");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Apatehu");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// INPUT DATA TO NEW CUSTOMER FORM
		driver.findElement(customerNameTextbox).sendKeys(name);
		sleepInSeconds(2);

		driver.findElement(dobTextbox).sendKeys(dob);
		sleepInSeconds(2);

		driver.findElement(addressTextArea).sendKeys(address);
		sleepInSeconds(2);

		driver.findElement(cityTextbox).sendKeys(city);
		sleepInSeconds(2);

		driver.findElement(stateTextbox).sendKeys(state);
		sleepInSeconds(2);

		driver.findElement(pinTextbox).sendKeys(pin);
		sleepInSeconds(2);

		driver.findElement(phoneTextbox).sendKeys(phone);
		sleepInSeconds(2);

		driver.findElement(emailTextbox).sendKeys(email);
		sleepInSeconds(2);

		driver.findElement(passwordTextbox).sendKeys(password);
		sleepInSeconds(2);

		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// GET DYNAMIC CUSTOMER ID
		customerId = driver.findElement(customerIDTextbox).getText();

		// VERIFY CREATE NEW CUSTOMER SUCCESS
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),
				email);

		// EDIT CUSTOMER
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		sleepInSeconds(2);

		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// CHECK 3 FIELDS (NAME/ GENDER/ DATE OF BIRTH) ARE DISABLE
		Assert.assertFalse(driver.findElement(customerNameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());

		// VERIFY 'CUSTOMER NAME' AND 'ADDRESS' FIELDS
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(addressTextArea).getText(), address);

		// INPUT NEW VALUE INTO 'ADDRESS'/ 'CITY' FIELDS
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(newAddress);
		sleepInSeconds(2);

		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(newCity);
		sleepInSeconds(2);

		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// VERIFY EDIT CUSTOMER
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//p")).getText(),
				"Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
				newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),
				newCity);

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
