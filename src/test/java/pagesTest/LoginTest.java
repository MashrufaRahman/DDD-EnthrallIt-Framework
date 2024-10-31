package pagesTest;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class LoginTest extends BaseClass {

	@Test
	public void loginTest() {
		login.validationLogoAndTitle();
		login.clickOnLogIn();
		login.ValidationEmailId("Kaylith1@gmail.com");
		login.ValidationPassword("Abcd12345%");
		login.ValideLogInButton();
		login.landingOnDashboard();

	}

}
