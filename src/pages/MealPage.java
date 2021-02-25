package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getMealsInput() {
		return this.driver.findElement(By.linkText("Meals"));
	}

	public List<WebElement> getMeals() {
		return this.driver.findElements(By.id("listing"));
	}

	public void addToCart(int quantity) {
		String quant = String.valueOf(quantity);
		WebElement quantityField = this.driver.findElement(By.name("product_qty"));
		quantityField.clear();
		quantityField.sendKeys(quant);
		this.driver.findElement(By.className("js-proceedtoAddInCart")).click();
	}

	public WebElement getFavoriteButton() {
		return this.driver.findElement(By.className("itemfav"));
	}
}