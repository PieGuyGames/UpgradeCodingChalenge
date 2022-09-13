package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;

public class IncomePage {

	private SeleniumOperations seleniumOperations;
	
	private static final By INDIVIDUAL_ANNUAL_INCOME_FIELD = By.name("borrowerIncome");
	private static final By ADDITIONAL_ANNUAL_INCOME_FIELD = By.name("borrowerAdditionalIncome");
	private static final By CONTINUE_BUTTON = By.xpath("//button[@data-auto='continuePersonalInfo']");
	
	public IncomePage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}
	
	/**
	 * Fill out all income information
	 * @param applicant - applicant details
	 */
	public void enterIncomeInformation(Applicant applicant) {
		enterIndividualAnnualIncome(applicant.getAnnualIncome());
		enterAdditionalAnnualIncome(applicant.getAdditionalIncome());
	}
	
	public void enterIndividualAnnualIncome(String income) {
		seleniumOperations.sendKeys(INDIVIDUAL_ANNUAL_INCOME_FIELD, income);
	}
	
	public void enterAdditionalAnnualIncome(String income) {
		seleniumOperations.sendKeys(ADDITIONAL_ANNUAL_INCOME_FIELD, income);
	}
	
	/**
	 * attempt to deal with failing click attempts. While an element on the same page as the button
	 * exists, keep clicking continue.
	 */
	public void clickContinue() {
    	while (seleniumOperations.elementExists(INDIVIDUAL_ANNUAL_INCOME_FIELD)) {
    		seleniumOperations.clickElement(CONTINUE_BUTTON);
		}
	}
}
