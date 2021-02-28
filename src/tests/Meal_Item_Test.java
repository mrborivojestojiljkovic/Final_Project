package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Meal_Item_Test extends BasicTest {

	@Test(priority = 7)
	public void addMealToCart() throws InterruptedException {
		this.driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.popUpPage.closeLocationForm();
		this.mealPage.addMealToCart("3");

		Assert.assertTrue(this.notificationPage.getNotificationMessage().contains("The Following Errors Occurred:"),
				"[Error - message doesn't exist]");
		Assert.assertTrue(this.notificationPage.getNotificationMessage().contains("Please Select Location"),
				"[Error - message doesn't exist]");
		this.notificationPage.notificationDisappears();
		Thread.sleep(2000);

		this.popUpPage.setLocation("City Center - Albany");
		Thread.sleep(5000);

		this.mealPage.addMealToCart("3");
		Thread.sleep(3000);

		String quant = this.notificationPage.getNotificationMessage();
		Assert.assertEquals(quant, "Meal Added To Cart", "[ERROR] Meal is not added to cart.");
		Thread.sleep(2000);
	}

	@Test(priority = 10)
	public void addMealToFavorite() throws Exception {
		this.driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.popUpPage.closeLocationForm();
		this.mealPage.addToFavourites();
		Thread.sleep(2000);
		Assert.assertTrue(this.notificationPage.getNotificationMessage().contains("Please login first"),
				"[ERROR]: Login failed");

		this.driver.get(baseUrl + "/guest-user/login-form");
		this.loginPage.login(this.email, this.password);

		this.driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		this.mealPage.addToFavourites();
		Thread.sleep(2000);

		Assert.assertTrue(
				this.notificationPage.getNotificationMessage().contains("Product has been added to your favorites."),
				"[ERROR]: Failed adding product as Favorite.");
	}

	@Test(priority = 15)
	public void clearCart() throws Exception {
		SoftAssert sa = new SoftAssert();
		this.driver.get(baseUrl + "/meals");
		this.popUpPage.closeLocationForm();
		this.popUpPage.setLocation("City Center - Albany");
		Thread.sleep(2000);

		File file = new File("data/Data.xlsx");
		FileInputStream fileInput = new FileInputStream(file);
		XSSFWorkbook fileXlsx = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = fileXlsx.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell1 = row.getCell(0);
			String url = cell1.getStringCellValue();
			String num = row.getCell(1).getRawValue();

			driver.get(url);
			Thread.sleep(2000);
			this.mealPage.addMealToCart(num);
			Thread.sleep(2000);
		}
		sa.assertAll();
		this.cartPage.clearAll();

		String emptyCart = this.notificationPage.getNotificationMessage();
		Assert.assertEquals(emptyCart, "All meals removed from the Cart successfully", "[ERROR] Cart is not empty.");
		fileInput.close();
		fileXlsx.close();
	}
}
