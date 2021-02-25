package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getUserId() {
		return driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccount() {
		return driver.findElement(By.linkText("My account"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.linkText("Logout"));
	}

	public void logout() {
		this.js.executeScript("arguments[0].click();", this.getUserId());
		this.waiter.until(ExpectedConditions.elementToBeClickable(getLogout()));
		this.js.executeScript("arguments[0].click();", this.getLogout());
	}
}