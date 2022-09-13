package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;

public class BasicInformationPage {

	private SeleniumOperations seleniumOperations;
	
	private static final By FIRST_NAME_FIELD = By.name("borrowerFirstName");
	private static final By LAST_NAME_FIELD = By.name("borrowerLastName");
	private static final By HOME_ADDRESS_FIELD = By.name("borrowerStreet");
	
	private static final By CITY_FIELD = By.name("borrowerCity");
	private static final By STATE_FIELD = By.name("borrowerState");
	private static final By ZIP_CODE_FIELD = By.name("borrowerZipCode");
	
	private static final By DATE_OF_BIRTH_FIELD = By.name("borrowerDateOfBirth");
	private static final By CONTINUE_BUTTON = By.xpath("//button[@data-auto='continuePersonalInfo']");
	
	public BasicInformationPage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}
	
	/**
	 * Enter basic information for entire page
	 * @param applicant - applicant details
	 */
	public void enterBasicInformation(Applicant applicant) {
		enterFirstName(applicant.getFirstName());
		enterLastName(applicant.getLastName());
		enterAddress(applicant.getAddress());
		enterCity(applicant.getCity());
		enterState(applicant.getState());
		enterZipCode(applicant.getZipCode());
		enterDateOfBirth(applicant.getDateOfBirth());
	}
	
	public void enterFirstName(String firstName) {
		seleniumOperations.sendKeys(FIRST_NAME_FIELD, firstName);
	}
	
	public void enterLastName(String lastName) {
		seleniumOperations.sendKeys(LAST_NAME_FIELD, lastName);
	}
	
	public void enterAddress(String address) {
		seleniumOperations.sendKeys(HOME_ADDRESS_FIELD, address);
	}
	
	public void enterCity(String city) {
		seleniumOperations.sendKeys(CITY_FIELD, city);
	}
	
	public void enterState(String state) {
		seleniumOperations.sendKeys(STATE_FIELD, state);
	}
	
	public void enterZipCode(String zipCode) {
		seleniumOperations.sendKeys(ZIP_CODE_FIELD, zipCode);
	}
	
	public void enterDateOfBirth(String dob) {
		seleniumOperations.sendKeys(DATE_OF_BIRTH_FIELD, dob);
	}
	
	/**
	 * There appears to be a timing issue with this button so while it exists attempt
	 * to click it. 
	 */
	public void clickContinue() {
    	while (seleniumOperations.elementExists(FIRST_NAME_FIELD)) {
    		seleniumOperations.clickElement(CONTINUE_BUTTON);
		}
	}
}
