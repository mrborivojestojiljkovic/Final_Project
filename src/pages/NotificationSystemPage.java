package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getElementMessage() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getNotificationMessage() {
		return this.getElementMessage().getText();
	}

	public boolean notificationDisappears() {
		WebElement element = this.driver.findElement(By.xpath("//*[contains(@class, 'system_message')]"));
		return this.waiter.until(ExpectedConditions.attributeContains(element, "style", "display: none;"));
	}
}
