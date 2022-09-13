package data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Applicant {

	private final String loanAmount;
	private final String loanPurpose;
	private final String firstName;
	private final String lastName;
	private final String address;
	private final String city;
	private final String state;
	private final String zipCode;
	private final String dateOfBirth;
	private final String emailAddress;
	private final String password;
	private final String annualIncome;
	private final String additionalIncome;

	private Offer offer;

}
