package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected SoftAssert sa;
	protected String baseUrl;
	protected String email;
	protected String password;
	protected LocationPopUpPage popUpPage;
	protected LoginPage loginPage;
	protected ProfilePage profilePage;
	protected NotificationSystemPage notificationPage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartPage;

	@BeforeClass
	public void basicSetup() {
		System.setProperty("webdriver.chrome.driver", "driver_lib\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 20);
		this.js = (JavascriptExecutor) driver;
		this.sa = new SoftAssert();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.baseUrl = "http://demo.yo-meals.com";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";
		this.popUpPage = new LocationPopUpPage(driver, waiter, js);
		this.loginPage = new LoginPage(driver, waiter, js);
		this.profilePage = new ProfilePage(driver, waiter, js);
		this.notificationPage = new NotificationSystemPage(driver, waiter, js);
		this.authPage = new AuthPage(driver, waiter, js);
		this.mealPage = new MealPage(driver, waiter, js);
		this.cartPage = new CartSummaryPage(driver, waiter, js);

	}

	@AfterMethod
	public void takesScreenshoot(ITestResult test) throws IOException {
		if (test.getStatus() == ITestResult.FAILURE) {
			File screenshoot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.png'").format(new Date());
			File save = new File("screenshots/" + fileName);
			FileHandler.copy(screenshoot, save);
		}
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	@AfterClass
	public void clean() {
		this.driver.quit();
	}
}