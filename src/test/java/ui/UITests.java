package ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import data.Applicant;
import ui.operations.SeleniumOperations;
import ui.pages.BasicInformationPage;
import ui.pages.IncomePage;
import ui.pages.LastStepsPage;
import ui.pages.LoanDetailsPage;
import ui.pages.LoginLandingPage;
import ui.pages.LoginPage;
import ui.pages.PersonalLoansPage;
import utilities.StringGenerator;

public class UITests {
	
	Log logger = LogFactory.getLog(this.getClass());
	StringGenerator stringGenerator = new StringGenerator();
	
	SeleniumOperations seleniumOperations;
	PersonalLoansPage personalLoansPage;
	BasicInformationPage basicInformationPage;
	IncomePage incomePage;
	LastStepsPage lastStepsPage;
	LoanDetailsPage loanDetailsPage;
	LoginLandingPage loginLandingPage;
	
	LoginPage loginPage;
	
    @BeforeEach
    public void setup() {
    	seleniumOperations = new SeleniumOperations();
    	
    	personalLoansPage = new PersonalLoansPage(seleniumOperations);
    	basicInformationPage = new BasicInformationPage(seleniumOperations);
    	incomePage = new IncomePage(seleniumOperations);
    	lastStepsPage = new LastStepsPage(seleniumOperations);
    	loanDetailsPage = new LoanDetailsPage(seleniumOperations);
    	loginLandingPage = new LoginLandingPage(seleniumOperations);
    	
    	loginPage = new LoginPage(seleniumOperations);
    }
    
    @AfterEach
    public void teardown() {
    	seleniumOperations.close();
    }
    
    /**
     * End to End test - user applies for loan which includes creating an account.
     * The loan application is processed and the user is presented with the result. The test
     * then logs the user out, logs them back in and and ensures the values on the applcation
     * results page match the values that appear when the user logs in with his/her new
     * account,. 
     */
    @Test
    @DisplayName("Apply for a personal loan")
    public void applyForPersonalLoan() {
    	
    	StringGenerator generator = new StringGenerator();
    	
    	Applicant applicant = Applicant.builder()
    			.loanAmount("2,000")
    			.loanPurpose(generator.getLoanPurpose())
    			.firstName(generator.generateName())
    			.lastName(generator.generateName())
    			.address(generator.generateAddress())
    			.city(generator.generateName())
    			.state(generator.getRandomUsState())
    			.zipCode(generator.generateZipCode())
    			.dateOfBirth(generator.generateRandomDate())
    			.emailAddress(generator.generateEmail())
    			.password(generator.generatePassword(8))
    			.annualIncome(generator.generateIncome(120000))
    			.additionalIncome(generator.generateIncome(5000))
    			.build();
    			
    	personalLoansPage.navigateToPage();
    	personalLoansPage.enterGetStartedValues(applicant);
    	personalLoansPage.checkYourRate();
    	
    	// Handle page that occasionally pops up asking customer to consider fundera
    	By funderaContinueLink = By.xpath("//*[text()='No thanks, I would like to continue with Upgrade']");
    	if (seleniumOperations.elementExists(funderaContinueLink)) {
    		seleniumOperations.clickElement(funderaContinueLink);
    	}

    	basicInformationPage.enterBasicInformation(applicant);
    	basicInformationPage.clickContinue();
    	
    	incomePage.enterIncomeInformation(applicant);
    	incomePage.clickContinue();
    	
    	lastStepsPage.enterPersonalInformation(applicant);
    	logger.warn("Set break point, press Check Rate button and manually Handle CAPTCHA");
    	lastStepsPage.clickeCheckRateButton();

    	loanDetailsPage.scrapeLoanDetailst(applicant);
    	loanDetailsPage.signOutOfPage();
	
    	loginPage.navigateToPage();
    	loginPage.enterLoginDetails(applicant);
    	loginPage.signInToAccount();
    	
    	// Do final assertions
    	assertThat(loginLandingPage.getLoanAmount()).describedAs("Verify Loan Amount").isEqualTo(applicant.getOffer().getApprovedLoanAmount());
    	assertThat(loginLandingPage.getApr()).describedAs("Verify APR value").isEqualTo(applicant.getOffer().getApr());
    	assertThat(loginLandingPage.getTerm()).describedAs("Verify Term").isEqualTo(applicant.getOffer().getTerm());
    	assertThat(loginLandingPage.getMonthlyPayment()).describedAs("Verify Monthly Payment").isEqualTo(applicant.getOffer().getMontlyPayments());
    }
}
