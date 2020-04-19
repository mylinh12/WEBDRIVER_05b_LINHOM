package selenium_api;

import java.util.List;
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

public class Topic_13_UserInteractions {
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

//	@Test
	public void TC_01_HoverMouse() {
		driver.get("https://www.myntra.com/");
		WebElement discover = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"));

		action.moveToElement(discover).perform();
		sleepInSeconds(3);

		driver.findElement(By.xpath("//a[text()='Lacoste']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']//span[text()='Lacoste']")).isDisplayed());

	}

//	@Test
	public void TC_02_ClickAndHold_Block() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		System.out.println("Item number = " + allItems.size());

		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
		sleepInSeconds(3);

		List<WebElement> allItemsSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class, 'ui-selected')]"));
		System.out.println("Item number = " + allItemsSelected.size());

		Assert.assertEquals(allItemsSelected.size(), 4);

		// for each
		for (WebElement item : allItemsSelected) {
			System.out.println("O so: " + item.getText());
		}
	}

//	@Test
	public void TC_03_ClickAndHold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		action.keyDown(Keys.CONTROL).perform();

		action.click(allItems.get(0));
		action.click(allItems.get(3));
		action.click(allItems.get(7));
		action.click(allItems.get(9));
		action.click(allItems.get(11));

		action.keyUp(Keys.CONTROL).perform();
		sleepInSeconds(3);

		List<WebElement> allItemsSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class, 'ui-selected')]"));
		System.out.println("Item number = " + allItemsSelected.size());
		Assert.assertEquals(allItemsSelected.size(), 5);

		// for each
		for (WebElement item : allItemsSelected) {
			System.out.println("O so: " + item.getText());
		}

	}

//	@Test
	public void TC_04_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html#");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo']")).getText().equals("Hello Automation Guys!"));
	}

//	@Test
	public void TC_05_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSeconds(3);

		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')]"))).perform();
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class, 'context-menu-hover')]")).isDisplayed());
		action.click(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')]"))).perform();
		sleepInSeconds(3);

		driver.switchTo().alert().accept();
		sleepInSeconds(3);
	}

	@Test
	public void TC_06_DragAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		sleepInSeconds(3);

		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSeconds(3);

		Assert.assertTrue(targetCircle.getText().equals("You did great!"));

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
