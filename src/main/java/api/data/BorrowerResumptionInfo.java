package api.data;

import java.util.HashMap;
import java.util.Map;

public class BorrowerResumptionInfo {

	private String firstName;
	private String maskedEmail;
	private Boolean ssnRequired;
	private String state;
	private String email;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMaskedEmail() {
		return maskedEmail;
	}

	public void setMaskedEmail(String maskedEmail) {
		this.maskedEmail = maskedEmail;
	}

	public Boolean getSsnRequired() {
		return ssnRequired;
	}

	public void setSsnRequired(Boolean ssnRequired) {
		this.ssnRequired = ssnRequired;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}