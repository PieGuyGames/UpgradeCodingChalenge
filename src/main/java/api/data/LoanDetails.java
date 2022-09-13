package api.data;

import java.util.ArrayList;
import java.util.List;

public class LoanDetails {

	private LoanAppResumptionInfo loanAppResumptionInfo;
	private List<Object> offers = new ArrayList<Object>();
	private Object selectedOffer;
	private List<Object> requiredAgreements = new ArrayList<Object>();
	private List<String> resetOptions = new ArrayList<String>();
	private Object originalLoanApp;

	public LoanAppResumptionInfo getLoanAppResumptionInfo() {
		return loanAppResumptionInfo;
	}

	public void setLoanAppResumptionInfo(LoanAppResumptionInfo loanAppResumptionInfo) {
		this.loanAppResumptionInfo = loanAppResumptionInfo;
	}

	public List<Object> getOffers() {
		return offers;
	}

	public void setOffers(List<Object> offers) {
		this.offers = offers;
	}

	public Object getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(Object selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public List<Object> getRequiredAgreements() {
		return requiredAgreements;
	}

	public void setRequiredAgreements(List<Object> requiredAgreements) {
		this.requiredAgreements = requiredAgreements;
	}

	public List<String> getResetOptions() {
		return resetOptions;
	}

	public void setResetOptions(List<String> resetOptions) {
		this.resetOptions = resetOptions;
	}

	public Object getOriginalLoanApp() {
		return originalLoanApp;
	}

	public void setOriginalLoanApp(Object originalLoanApp) {
		this.originalLoanApp = originalLoanApp;
	}
}
