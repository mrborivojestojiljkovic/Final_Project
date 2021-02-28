package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopUpPage extends BasicPage {

	public LocationPopUpPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getLocationButton() {
		WebElement locationButton = this.driver.findElement(By.xpath("//div[@class=\"location-selector\"]/a"));
		return locationButton;
	}

	public WebElement getCloseButton() {
		return this.driver.findElement(By.className("close-btn-white"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void getLocationForm() {
		this.getLocationButton().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();
		String value = this.getLocationItem(locationName).getAttribute("data-value");
		this.js.executeScript("arguments[0].value=arguments[1];", this.getLocationInput(), value);
		this.js.executeScript("arguments[0].click();", this.getSubmit());
	}

	public void closeLocationForm() {
		this.getCloseButton().click();
	}
}
