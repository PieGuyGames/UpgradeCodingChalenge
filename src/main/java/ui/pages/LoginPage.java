package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;

public class LoginPage {

	private SeleniumOperations seleniumOperations;
	private static final String LOGIN_PAGE_URL = "https://www.credify.tech/portal/login";

	private static final By EMAIL_ADDRESS_FIELD = By.name("username");
	private static final By PASSWORD_FIELD = By.name("password");
	private static final By SIGN_IN_BUTTON = By.xpath("//*[text()='Sign in to your account']");
	
	public LoginPage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}
	
	public void navigateToPage() {
		seleniumOperations.navigateToUrl(LOGIN_PAGE_URL, EMAIL_ADDRESS_FIELD);
	}
	
	/**
	 * Fill out username and password
	 * @param applicant
	 */
	public void enterLoginDetails(Applicant applicant) {
		seleniumOperations.sendKeys(EMAIL_ADDRESS_FIELD, applicant.getEmailAddress());
		seleniumOperations.sendKeys(PASSWORD_FIELD, applicant.getPassword());
	}
	
	public void signInToAccount() {
		seleniumOperations.clickElement(SIGN_IN_BUTTON);
	}
}
