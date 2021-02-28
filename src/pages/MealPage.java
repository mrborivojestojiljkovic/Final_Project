package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getAddToCartButton() {
		return this.driver.findElement(By.xpath("//*[@class=\"product-description\"]/div[3]/div[2]/a"));
	}

	public WebElement getQuantityInput() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public void addMealToCart(String num) throws InterruptedException {
		String quantity = String.valueOf(num);
		this.getQuantityInput().clear();
		this.getQuantityInput().sendKeys(quantity);
		Thread.sleep(2000);
		this.getAddToCartButton().click();
	}

	public void addToFavourites() {
		this.driver.findElement(By.className("favourite")).click();
	}
}