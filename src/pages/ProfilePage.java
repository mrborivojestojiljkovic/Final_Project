package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getFirstName() {
		return driver.findElement(By.xpath("//*[@name='user_first_name']"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.xpath("//*[@name='user_last_name']"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.xpath("//*[@name='user_address']"));
	}

	public WebElement getPhoneNumber() {
		return driver.findElement(By.xpath("//*[@name='user_phone']"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.xpath("//*[@name='user_zip']"));
	}

	public void getCountry(String country) throws InterruptedException {
		WebElement countryField = driver.findElement(By.xpath("//*[@id=\"user_country_id\"]"));
		Select countrySelect = new Select(countryField);
		countrySelect.selectByVisibleText(country);
		Thread.sleep(2000);
	}

	public void getState(String state) throws InterruptedException {
		WebElement stateField = driver.findElement(By.xpath("//*[@id=\"user_state_id\"]"));
		Select stateSelect = new Select(stateField);
		stateSelect.selectByVisibleText(state);
		Thread.sleep(2000);
	}

	public void getCity(String city) throws InterruptedException {
		WebElement cityField = driver.findElement(By.xpath("//*[@id=\"user_city\"]"));
		Select citySelect = new Select(cityField);
		citySelect.selectByVisibleText(city);
		Thread.sleep(2000);
	}

	public WebElement getSaveButton() {
		return driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}

	public WebElement getUploadImageBtn() throws InterruptedException {
		return driver.findElement(By.className("ion-camera"));
	}

	public void uploadImage(String image) throws InterruptedException, IOException {
		js.executeScript("arguments[0].click();", this.getUploadImageBtn());
		WebElement uploadImage = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String imagePath = new File(image).getCanonicalPath();
		uploadImage.sendKeys(imagePath);
	}

	public void removeImage() {
		WebElement remove = this.driver.findElement(By.className("remove"));
		this.js.executeScript("arguments[0].click()", remove);
	}

	public void updateProfileInformation(String firstName, String lastName, String address, String phoneNum,
			String zipCode, String country, String state, String city) throws InterruptedException {

		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhoneNumber().clear();
		this.getZipCode().clear();

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhoneNumber().sendKeys(phoneNum);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry(country);
		this.getState(state);
		this.getCity(city);

		this.getSaveButton().click();
	}
}