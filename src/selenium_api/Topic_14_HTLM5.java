package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_HTLM5 {
	private WebDriver driver;
	private JavascriptExecutor javascriptExecutor;
	Actions action;
	private String ROOT_FOLDER = System.getProperty("user.dir");
	String javascriptPath = ROOT_FOLDER + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = ROOT_FOLDER + "\\dragAndDrop\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Drag_And_Drop_JS_Jquery() throws InterruptedException, IOException {

		// Trang web HTML5, va cung la trang ma nguoi ta da inject(tiem) JS va2 JQuery vao trang web roi
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		// Get ra noi dung cua file JS
		String java_script = readJavascriptFile(javascriptPath);

		// Get ra noi dung cua file JS + inject Jquery lib to application (Neu trang web cua nguoi ta chua co JS/Jquer thi se dung them 2 lenh duoi de inject (tiem) JS va JQuery vao)
		// String jqueryscript = readJavascriptFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
	}

	@Test
	public void TC_02_DragAndDrop_Actions() {
		// Day la mot vi du chung minh Selenium ko support HTML5, nen doan lenh duoi nay se khong chay dc
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		WebElement source = driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='column-b']"));

		action.dragAndDrop(source, target).perform();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

	}

	@Test
	public void TC_03_DragAndDrop_Offset() throws AWTException {
		// Neu ma chay khong dc , thi co the do man hinh window da chinh Scale, Scale nen de che do Recommend
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSeconds(3);		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSeconds(3);		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
	}

	// Read file js: https://gist.github.com/daominhdam/de0629d1048e9c9848ebb93d717769fd
	public String readJavascriptFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		// Cac Object duoi nay dung thu vien 'awt' nha (la thu vien do hoa): Robot, InputEvent.
		// Cac Object sau dung thu vien Selenium (openqa): Dimension, Point.
		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
