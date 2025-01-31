package rsuc.apitesting.pojos;

import java.util.List;

public class Courses {
	//private WebAutomation webAutomation;
	//Since the webautomation, api and mobile classes returning list of arrays values change the class name return type to list in all places
	//webautomation , api and mobile classes are used to get the sub nested nested array values in JSON
	//Give variable names same as in JSON request and response
	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}


	

}
