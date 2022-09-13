package ui.pages;

import org.openqa.selenium.By;

import data.Applicant;
import data.Offer;
import ui.operations.SeleniumOperations;

public class LoanDetailsPage {

	private SeleniumOperations seleniumOperations;
	
	private static final By LOAN_AMOUNT =  By.xpath("//*[contains(@data-auto,'userLoanAmount')]");
	private static final By MONTHLY_PAYMENT = By.xpath("//*[contains(@data-auto,'defaultMonthlyPayment')]");
	private static final By TERM = By.xpath("//*[contains(@data-auto,'defaultLoanTerm')]");
	private static final By INTEREST_RATE = By.xpath("//*[contains(@data-auto,'defaultLoanInterestRate')]");
	private static final By APR = By.xpath("//*[contains(@data-auto,'defaultAPR')]");
	private static final By MENU = By.xpath("//*[contains(@aria-label,'Open Site Menu')]");
	private static final By SIGN_OUT = By.xpath("//*[text()='Sign Out']");
	
	public LoanDetailsPage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}

	/**
	 * Retrieve all offer details
	 * @param applicant - applicant details
	 */
	public void scrapeLoanDetailst(Applicant applicant) {
		// wait for page to load
		seleniumOperations.waitForElementToAppear(LOAN_AMOUNT);

		Offer offer = Offer.builder()
				.approvedLoanAmount(seleniumOperations.extractFieldText(LOAN_AMOUNT))
				.montlyPayments(seleniumOperations.extractFieldText(MONTHLY_PAYMENT))
				.term(seleniumOperations.extractFieldText(TERM))
				.interestRate(seleniumOperations.extractFieldText(INTEREST_RATE))
				.apr(seleniumOperations.extractFieldText(APR))
				.build();
		applicant.setOffer(offer);

	}
	
	public void signOutOfPage() {
		seleniumOperations.clickElement(MENU);
		seleniumOperations.clickElement(SIGN_OUT);
	}
}
