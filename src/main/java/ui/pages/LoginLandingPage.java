package ui.pages;

import org.openqa.selenium.By;

import ui.operations.SeleniumOperations;

public class LoginLandingPage {

	private SeleniumOperations seleniumOperations;
	
	private static final By LOAN_AMOUNT =  By.xpath("//*[contains(@data-auto,'userLoanAmount')]");
	private static final By MONTHLY_PAYMENT = By.xpath("//*[contains(@data-auto,'defaultMonthlyPayment')]");
	private static final By TERM = By.xpath("//*[contains(@data-auto,'defaultLoanTerm')]");
	private static final By INTEREST_RATE = By.xpath("//*[contains(@data-auto,'defaultLoanInterestRate')]");
	private static final By APR = By.xpath("//*[contains(@data-auto,'defaultAPR')]");
	
	public LoginLandingPage(SeleniumOperations seleniumOperations) {
		this.seleniumOperations = seleniumOperations;
	}
	
	public String getLoanAmount() {
		return seleniumOperations.extractFieldText(LOAN_AMOUNT);
	}
	
	public String getMonthlyPayment() {
		return seleniumOperations.extractFieldText(MONTHLY_PAYMENT);
	}
	
	public String getTerm() {
		return seleniumOperations.extractFieldText(TERM);
	}
	
	public String getInterestRate() {
		return seleniumOperations.extractFieldText(INTEREST_RATE);
	}
	public String getApr() {
		return seleniumOperations.extractFieldText(APR);
	}
	
}
