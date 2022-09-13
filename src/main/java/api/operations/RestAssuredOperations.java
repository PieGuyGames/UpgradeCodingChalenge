package api.operations;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;

import api.data.LoanDetails;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utilities.StringGenerator;


public class RestAssuredOperations {

	StringGenerator generator = new StringGenerator();
	private final String loanApplicationResumptionInfoURL ="https://credapi.credify.tech/api/brfunnelorch/v2/resume/byLeadSecret";
	
	/**
	 * Make a POST call to retrieve loan details
	 * @param loanAppUuid
	 * @return Object containing loan details
	 */
	public LoanDetails retrieveLoanDetails(String loanAppUuid) {

		Map<String, Object> requestBody  = new HashMap<>();
		requestBody.put("loanAppUuid", loanAppUuid);
		requestBody.put("skipSideEffects",true);
		Response response = retrieveLoanAPICall(new Gson().toJson(requestBody));
		
		assertThat(response.getStatusCode()).describedAs("Ensure post request was successful").isEqualTo(HttpStatus.SC_OK);
		
		return  response.getBody().as(LoanDetails.class);
	}
	
	/**
	 * Make a POST call to attempt retrieval of loan details
	 * using an invalid UUID.
	 * @param loanAppUuid
	 * @return return code for the operation
	 */
	public int retrieveLoanDetailsBadUUID(String loanAppUuid) {

		Map<String, String> requestBody  = new HashMap<>();
		requestBody.put("loanAppUuid", loanAppUuid);
		requestBody.put("skipSideEffects","true");	
		return retrieveLoanAPICall(new Gson().toJson(requestBody)).getStatusCode();
			
	}
	
	/**
	 * POST call with common code used by other functions in this
	 * class. 
	 * @param requestBody - post call body contents
	 * @return rest assured response object
	 */
	private Response retrieveLoanAPICall(String requestBody) {
		
		Header header = new Header("x-cf-source-id", "coding-challenge");
		Header header2 = new Header("x-cf-corr-id:", generator.generateUUID());
		Headers headers = new Headers(header, header2);
	
		return given()
				.headers(headers)
				.contentType(ContentType.JSON)
				.body(requestBody)
			.when()
				.post(loanApplicationResumptionInfoURL);
	}
	
}
