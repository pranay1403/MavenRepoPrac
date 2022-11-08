package auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SoftAsssert {
	WebDriver driver;
	@BeforeTest
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://leetcode.com/");
	}
	@Test(priority = 1, invocationCount = 1,enabled=true,groups = "Sanity Testing")
	public void getTitle() {
		String Title = "LeetCode - The World's Leading Online Programming Learning Platform";
		String ActualTitle = driver.getTitle();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(Title, ActualTitle);   //Soft assert(Verify assert)
		sa.assertAll();
		
	}

	@Test(priority = 2, description = "validate url",groups = "Sanity Testing")
	public void getUrl() {
		String Url = "https://leetcode.com/";
		String url = driver.getCurrentUrl();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(Url, url);
		
		sa.assertAll();  // When we using soft assert class this method is compulsory  
	}

	@Test(priority = 3,groups = "Sanity Testing")
	public void goToSignInPage() throws InterruptedException {

		WebElement login = driver.findElement(By.xpath("//span [text()='Sign in']"));
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(login.isDisplayed());
		
		login.click();

		Thread.sleep(6000);
	}

	@Test(priority = 4, description = "SignIn Module",groups = "functional Testing")
	public void SignInFunct() throws InterruptedException {

		WebElement Username = driver.findElement(By.id("id_login"));
		Assert.assertTrue(Username.isDisplayed());    				//Hard Assert
		Username.sendKeys("pranaygaikwadpatil@gmail.com");

		WebElement pass = driver.findElement(By.name("password"));
		Assert.assertTrue(pass.isDisplayed());
		pass.sendKeys("Pranay@8988");

		WebElement Signbtn = driver.findElement(By.xpath("//span [text()='Sign In']"));
		Assert.assertTrue(Signbtn.isDisplayed());
		Signbtn.click();
		Thread.sleep(5000);
		

	}
	@AfterTest
	public void TearUp() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
