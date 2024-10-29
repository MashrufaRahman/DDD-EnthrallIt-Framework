package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static common.CommonAction.*;

public class Login {
	WebDriver driver;

	public Login(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Login']")
	WebElement logo;

	@FindBy(xpath = "//a[text()='Login']")
	WebElement logInFromToolbar;

	@FindBy(xpath = "//input[@name='username']")
	WebElement email;
	@FindBy(xpath = "//input[@id = 'password']")
	WebElement password;
	@FindBy(css = "input.btn.btn-lg.px-5")
	WebElement loginButton;
	@FindBy(xpath = "//title[text()='Enthrall IT - Dashboard']")
	WebElement title;

	@FindBy(xpath = "//h1[text()='Dashboard']")
	WebElement dashboard;

	@FindBy(xpath = "//span[text()='Automation']")
	WebElement automation;

	public void validationLogoAndTitle() {
		elementDisplayed(logo);
		verifyTitle(driver, "Enthrall IT");
		pause(4000);
	}

	public void clickOnLogIn() {
		elementDisplayed(logInFromToolbar);
		clickElement(logInFromToolbar);
		pause(4000);
	}

	public void ValidationEmailId(String emailId) {
		elementDisplayed(email);
		inputText(email, emailId);
		pause(4000);
	}

	public void ValidationPassword(String pwd) {
		elementDisplayed(password);
		inputText(password, pwd);
		pause(4000);
	}

	public void ValideLogInButton() {
		elementEnabled(loginButton);
		// verifyTextOfTheWebElement(loginButton, "login");
		clickElement(loginButton);
		pause(4000);
	}

	public void landingOnDashboard() {
		pause(4000);
		verifyTitle(driver, "Enthrall IT - Dashboard");
		verifyCurrentUrl(driver, "https://enthrallit.com/dashboard/");
		validationOfHeader(dashboard, "Dashboard");
		pause(4000);
	}

}
