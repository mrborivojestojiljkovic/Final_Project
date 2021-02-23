package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		Thread.sleep(1500);
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

	public WebElement getInfoSaveButton() {
		return driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}

	public WebElement getUploadImage() throws InterruptedException {
		return driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div/div/div[2]/div/div[1]/div/a"));
	}

	public void uploadImage(String image) throws InterruptedException {

		js.executeScript("arguments[0].click();", this.getUploadImage());
		Thread.sleep(3000);
		WebElement upload = driver.findElement(By.xpath("//*[@id=\"form-upload\"]/input"));
		upload.sendKeys(image);
	}

	public void deleteImage() {
		WebElement delete = driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
		js.executeScript("arguments[0].click();", delete);
	}

	public void updateProfile(String firstName, String lastName, String address, String phoneNum, String zipCode,
			String country, String state, String city) throws InterruptedException {

		this.getFirstName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getLastName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getAddress().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getPhoneNumber().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getZipCode().sendKeys(Keys.CONTROL + "a", Keys.DELETE);

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhoneNumber().sendKeys(phoneNum);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry(country);
		this.getState(state);
		this.getCity(city);

		this.getInfoSaveButton().click();
	}
}