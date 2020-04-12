package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_API {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//Khoi tao trinh duyet FireFox
		driver = new FirefoxDriver();
		
		//2. User nhap Url cua applilcation: https://tiki.vn/
		driver.get("https://tiki.vn/");
		
		// 3. User can click vao Email textbox
		driver.findElement(By.className("email")).click();
		
		//4. User get title cua trang
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		//5. User get pageSource cua Trang
		String pageSource = driver.getPageSource();
		
		// Tuong doi
		Assert.assertTrue(pageSource.contains("Thỏa sức mua sắm qua mạng hàng ngàn mặt hàng sách, điện"));
		
		//6. Get ra ID cua Tab/window hien tai
		driver.getWindowHandle();
		
		// Dung de cho cho Element xuat hien (de co the thao tac duoc)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Dung de cho cho page duoc load xong (de co the thao tac duoc)
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// Cho cho script duoc execute xong (Javascript Executer)
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		// Dung de fullscreen trinh duyet
		driver.manage().window().fullscreen();
		
		// Dung de phong to trinh duyet
		driver.manage().window().maximize();
		
		// Dung de test GUI (font/ size/ color/ ...)
		driver.manage().window().getSize();
		
		// User back lai trang truoc do
		driver.navigate().back();
		
		// User tai lai trang
		driver.navigate().refresh();
		
		// User di toi trang
		driver.navigate().forward();
		
		// Alert/ windown(Tab)/ Iframe(Frame)
		driver.switchTo().alert();
		
		driver.switchTo().window("");
		
		driver.switchTo().frame("");
		
		// Dong 1 cai tab duy nhat dang lam viec tren trinh duyet
		driver.close();
		
		// Dong toan bo trinh duyet (du chi co 1 tab hay nhieu tabs)
		driver.quit();
	}

	@Test
	public void TC_01_() {

	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
