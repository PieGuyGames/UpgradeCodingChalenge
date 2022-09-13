package data;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class Offer {
	private String approvedLoanAmount;
	private String montlyPayments;
	private String term;
	private String interestRate;
	private String apr;
}
