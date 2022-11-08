package auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameter {

	WebDriver driver;
@Parameters({"browser"})	
@BeforeTest(alwaysRun = true)
public void browserSetUp(String browser) {
	if(browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}else if (browser.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		
	}else if(browser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
	}else {
		System.out.println("No browser found");
	}
	driver.get("https://leetcode.com/accounts/login/");
}
@Parameters({"user","login"})
@Test(priority = 1)
public void LoginFunction(String user,String login) throws InterruptedException {
	Thread.sleep(3000);
	
	WebElement Username = driver.findElement(By.id("id_login"));
	Assert.assertTrue(Username.isDisplayed());
	Username.sendKeys(user);

	WebElement password = driver.findElement(By.name("password"));
	Assert.assertTrue(password.isDisplayed());
	password.sendKeys(login);

	WebElement Signbtn = driver.findElement(By.xpath("//span [text()='Sign In']"));
	Assert.assertTrue(Signbtn.isDisplayed());
	Signbtn.click();
//	Thread.sleep(5000);
	
}
@AfterTest()
public void TearUp() throws InterruptedException {
	Thread.sleep(5000);
	driver.close();
}
}
