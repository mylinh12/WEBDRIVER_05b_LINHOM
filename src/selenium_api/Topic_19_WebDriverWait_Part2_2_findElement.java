package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_WebDriverWait_Part2_2_findElement {
	// Online 14
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_findElement() {

		/*
		 * 1 - Neu nhu no tim thay 1 element thi no se lam gi? --> 1 matching node Tra loi: No se thao tac voi element nay
		 */
		driver.get("https://automationfc.github.io/multiple-fields/index.html");
		driver.findElement(By.xpath("//input[@id='first_45']")).sendKeys("Automation FC");
		sleepInSeconds(3);

		/*
		 * 2 - Neu nhu no tim thay nhieu hon 1 element thi no se lam gi? --> >= 2 matching nodes Tra loi: No se thao tac voi element dau tien
		 */
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		sleepInSeconds(3);

		/*
		 * 3 - Neu no ko tim thay element nao het thi no se lam gi? --> No matching nodes Tra loi: No se cho den khi het timeout cua implicit wait Trong thoi gian cho thi cu moi 0.5s no se tim lai element 1 lan
		 */

		/* 3.1 Neu nhu no tim thay Element trong thoi gian cho thi no se pass step nay va ko can cho het timeout */
		/*
		 * 3.2 Neu nhu no ko tim thay ma het timeout roi thi: - He thong se danh FAIL TC - va throw ra 1 exception: No Such Element
		 */
		driver.findElement(By.xpath("//input[@type='dasacccx']")).click();

	}

	@Test
	public void TC_02_findElements() {
		/*
		 * 1 - Neu nhu no tim thay 1 element thi no se lam gi? --> 1 matching node Tra loi: No se gan element nay vao trong cai list (co 1 element)
		 */
		driver.get("https://automationfc.github.io/multiple-fields/index.html");
		List<WebElement> firstNameTextbox = driver.findElements(By.xpath("//input[@id='first_45']"));
		System.out.println("Number of elements = " + firstNameTextbox.size());
		firstNameTextbox.get(0).sendKeys("Selenium Advanced");
		sleepInSeconds(3);

		/*
		 * 2 - Neu nhu no tim thay nhieu hon 1 element thi no se lam gi? --> >= n matching nodes Tra loi: No se gan element nay vao trong cai list (co n element)
		 */
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println("Number of elements = " + checkboxes.size());
		sleepInSeconds(3);

		// Thao tac voi tat ca cac checkboxes

		for (int i = 0; i < checkboxes.size(); i++) {
			checkboxes.get(i).click();
			sleepInSeconds(1);
			Assert.assertTrue(checkboxes.get(i).isSelected());
		}

		// for-each
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSeconds(1);
			Assert.assertFalse(checkbox.isSelected());
		}

		/*
		 * 3 - Neu no ko tim thay element nao het thi no se lam gi? --> No matching nodes Tra loi: No se cho den khi het timeout cua implicit wait Trong thoi gian cho thi cu moi 0.5s no se tim lai element 1 lan
		 */

		/* 3.1 Neu nhu no tim thay Element trong thoi gian cho thi no se pass step nay va ko can cho het timeout */
		/*
		 * 3.2 Neu nhu no ko tim thay ma het timeout roi thi: - se ko co element nao de gan vao trong list (nen list do = empty = 0) - va KO danh fail TC
		 */
		List<WebElement> addressTextbox = driver.findElements(By.xpath("//input[@type='dasacccx']"));
		System.out.println("Number of elements = " + addressTextbox.size());
		sleepInSeconds(3);
		
		// FAIL --> vi list ko co empty nao, ma minh lai lay no di xai cho chuyen khac, thi se bi fail
		addressTextbox.get(0).sendKeys("Automation");
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
