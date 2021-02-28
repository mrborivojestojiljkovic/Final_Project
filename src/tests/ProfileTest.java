package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void EditProfileTest() throws InterruptedException {
		this.driver.get(baseUrl + "/guest-user/login-form");
		this.popUpPage.closeLocationForm();

		this.loginPage.login(this.email, this.password);

		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Login Successfull.",
				"[ERROR] Login failed.");
		Thread.sleep(2000);

		this.driver.navigate().to(this.baseUrl + "/member/profile");
		this.profilePage.updateProfileInformation("Borivoje", "Stojiljkovic", "Sindjelicev Trg", "123-4567-89", "12345",
				"India", "Delhi", "New Delhi");
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Setup Successful",
				"[ERROR] Profile update failed.");
		Thread.sleep(2000);

		this.authPage.logout();
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Logout Successful",
				"[ERROR] Logout failed.");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void uploadImage() throws IOException, InterruptedException {
		this.driver.get(this.baseUrl + "/guest-user/login-form");
		this.popUpPage.closeLocationForm();
		this.loginPage.login(this.email, this.password);
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Login Successfull.",
				"[ERROR] Login failed.");

		this.driver.get(this.baseUrl + "/member/profile");
		this.profilePage.uploadPicture("D:\\Image.jpg");
		Thread.sleep(3000);
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Profile photo updated successfully.",
				"[ERROR] Failed to update profile photo.");
		this.notificationPage.notificationDisappears();
		this.notificationPage.wait();

		this.profilePage.removeProfilePhoto();
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Profile photo removed successfully.",
				"[ERROR] Failed to remove profile photo.");
		this.notificationPage.wait();

		this.authPage.logout();
		Assert.assertEquals(this.notificationPage.getNotificationMessage(), "Logout successfull.",
				"[ERROR] Logout failed.");
	}
}
