package auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Annotation {
	WebDriver driver;

	@BeforeTest(alwaysRun = true)
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://leetcode.com/");
	}

	@Test(priority = 1, invocationCount = 1,enabled=true,groups = "Sanity Testing")
	public void getTitle() {
		String Title = "LeetCode - The World's Leading Online Programming Learning Platform";
		String ActualTitle = driver.getTitle();
		Assert.assertEquals(Title, ActualTitle);
	}

	@Test(priority = 2, description = "validate url",groups = "Sanity Testing")
	public void getUrl() {
		String Url = "https://leetcode.com/";
		String url = driver.getCurrentUrl();
		Assert.assertEquals(Url, url);

	}

	@Test(priority = 3,groups = "Sanity Testing")
	public void goToSignInPage() throws InterruptedException {

		WebElement login = driver.findElement(By.xpath("//span [text()='Sign in']"));

		Assert.assertTrue(login.isDisplayed());

		login.click();

		Thread.sleep(6000);
	}

	@Test(priority = 4, description = "SignIn Module",groups = "functional Testing")
	public void SignInFunct() throws InterruptedException {

		WebElement Username = driver.findElement(By.id("id_login"));
		Assert.assertTrue(Username.isDisplayed());
		Username.sendKeys("pranaygaikwadpatil@gmail.com");

		WebElement pass = driver.findElement(By.name("password"));
		Assert.assertTrue(pass.isDisplayed());
		pass.sendKeys("Pranay@8988");

		WebElement Signbtn = driver.findElement(By.xpath("//span [text()='Sign In']"));
		Assert.assertTrue(Signbtn.isDisplayed());
		Signbtn.click();
		Thread.sleep(5000);
		

	}

//	@Test(priority=5,dependsOnMethods = "SignInFunct")
//	public void SignOut() throws InterruptedException {
//		WebElement profile=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[4]/a"));
//		Select sc=new  Select(profile);
//		sc.selectByIndex(10);
//		///profile.click();
//		Thread.sleep(5000);
//		WebElement SignOut=driver.findElement(By.xpath("/html/body/div[8]/div/div/ul/li[11]/div/div/div"));
//		Assert.assertTrue(SignOut.isDisplayed());
//		SignOut.click();
//		Thread.sleep(6000);
//		String getTitle="LeetCode - The World's Leading Online Programming Learning Platform";
//		String actualTitle=driver.getTitle();
//		Assert.assertEquals(getTitle, actualTitle);
//	}

	@AfterTest(alwaysRun = true)
	public void TearUp() {
		driver.close();
	}

}
