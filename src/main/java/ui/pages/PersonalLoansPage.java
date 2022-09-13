package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;

public class PersonalLoansPage {

	private SeleniumOperations seleniumOperations;
	private static final String PERSONAL_LOANS_URL = "https://www.credify.tech/funnel/nonDMFunnel";
		
	private static final By DESIRED_AMOUNT_FIELD = By.name("desiredAmount");
	private static final By LOAN_PURPOSE_DROP_DOWN = By.name("loan-purpose");
	private static final By CHECK_YOUR_RATE_BUTTON = By.xpath("//button[@data-auto='CheckYourRate']");

	public PersonalLoansPage(SeleniumOperations seleniumOperatons) {
		this.seleniumOperations = seleniumOperatons;
	}
	
	public void navigateToPage() {
		seleniumOperations.navigateToUrl(PERSONAL_LOANS_URL, DESIRED_AMOUNT_FIELD);
	}
	/**
	 * Fill out peraonal loan info
	 * @param applicant
	 */
	public void enterGetStartedValues (Applicant applicant) {
		enterLoanAmount(applicant.getLoanAmount());
		selectLoanPurpose(applicant.getLoanPurpose());
	}
	
	public void enterLoanAmount(String  amount) {
		seleniumOperations.sendKeys(DESIRED_AMOUNT_FIELD, amount);
	}
	
	public void selectLoanPurpose(String purpose) {
		seleniumOperations.selectDropDown(LOAN_PURPOSE_DROP_DOWN, purpose);
	}
	
	public void checkYourRate() {
		seleniumOperations.clickElement(CHECK_YOUR_RATE_BUTTON);
	}
}
