package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;

public class LastStepsPage {
	
	private SeleniumOperations seleniumOperations;
	
	private static final By EMAIL_ADDRESS_FIELD = By.name("username");
	private static final By PASSWORD_FIELD = By.name("password");
	private static final By AGREE_CHECKBOX = By.name("agreements");
	private static final By CHECK_YOUR_RATE_BUTTON = By.xpath("//button[@data-auto='submitPersonalInfo']");

	public LastStepsPage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}

	/**
	 * Fill out all  fields on personal info page
	 * @param applicant - applicant details
	 */
	public void enterPersonalInformation(Applicant applicant) {
		enterEmailAddress(applicant.getEmailAddress());
		enterPassword(applicant.getPassword());
		clickAgreeCheckBox();
	}
	
	public void enterEmailAddress(String emailAddress) {
		seleniumOperations.sendKeys(EMAIL_ADDRESS_FIELD, emailAddress);
	}
	
	public void enterPassword(String password) {
		seleniumOperations.sendKeys(PASSWORD_FIELD, password);
	}
	
	public void clickAgreeCheckBox() {
		seleniumOperations.clickCheckBox(AGREE_CHECKBOX);
	}
	
	public void clickeCheckRateButton() {
		seleniumOperations.clickElement(CHECK_YOUR_RATE_BUTTON);
	}
}
