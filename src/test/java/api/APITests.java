package api;


import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import api.data.LoanDetails;
import api.operations.RestAssuredOperations;
 
public class APITests {

	RestAssuredOperations restOperations = new RestAssuredOperations();
    
    /**
     * Retrieve loan details and verify result
     */
    @Test
    @DisplayName("Retrieve loan details")
    public void loanApplicationDetails() {
    	String loanAppUuid = "b8096ec7-2150-405f-84f5-ae99864b3e96";
    	LoanDetails loanDetails = restOperations.retrieveLoanDetails(loanAppUuid);
    	
    	assertThat(loanDetails.getLoanAppResumptionInfo().getProductType())
    		.describedAs("Verify product type is PERSONAL_LOAN")
    		.isEqualTo("PERSONAL_LOAN");
    }
    
    /**
     * Attempt to retrieve loan details with invalid UUID and verify return code
     */
    @Test
    @DisplayName("Retrieve loan details with invalid UUID")
    public void loanApplicationInvalidUUID() {
    	String invalidLoanAppUuid = "b8056ec7-2150-405f-84f5-ae99864b3e96";
    	int statusCode = restOperations.retrieveLoanDetailsBadUUID(invalidLoanAppUuid);
    	
    	assertThat(statusCode)
    		.describedAs("Ensure invalie uuid generate 404")
    		.isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
