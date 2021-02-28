package pages;

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
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNumber() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement countryField = driver.findElement(By.name("user_country_id"));
		Select countrySelect = new Select(countryField);
		return countrySelect;
	}

	public Select getState() {
		WebElement stateField = this.driver.findElement(By.name("user_state_id"));
		Select stateSelect = new Select(stateField);
		return stateSelect;
	}

	public Select getCity() {
		WebElement cityField = this.driver.findElement(By.name("user_city"));
		Select citySelect = new Select(cityField);
		return citySelect;
	}

	public WebElement getSaveButton() {
		return driver.findElement(By.xpath("//*[@id=\\\"profileInfoFrm\\\"]/div[5]/div/fieldset/input"));
	}

	public WebElement getUploadImageBtn() {
		return driver.findElement(By.xpath("//a[@title=\"Uplaod\"]"));
	}

	public WebElement getRemoveButton() {
		return this.driver.findElement(By.xpath("//a[@title=\"Remove\"]"));
	}

	public WebElement getUpload() {
		return this.driver.findElement(By.xpath("//*[@type=\"file\"]"));
	}

	public void uploadPicture(String picture) {
		this.js.executeScript("arguments[0].click();", this.getUploadImageBtn());
		this.getUpload().sendKeys(picture);
	}

	public void removeProfilePhoto() {
		this.js.executeScript("arguments[0].click();", this.getRemoveButton());
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
		this.getCountry().selectByVisibleText(country);
		this.getState().selectByVisibleText(state);
		this.getCity().selectByVisibleText(city);

		this.getSaveButton().click();
	}
}
