package rsuc.apitesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import rsuc.apitesting.pojos.Api;
import rsuc.apitesting.pojos.GetCourse;
import rsuc.apitesting.pojos.WebAutomation;

import static io.restassured.RestAssured.*; 


public class Test_08_DeSerialization {
	/******
	 * Serialization - Converting java object into request body
	 * De-serialization - Converting response body back to java object
	 * 
	 * Advantages:
	 * Easy to parse and extract response(Json/xml) values if they are wrapped as java objects
	 * User friendly methods can be created which makes code readable
	 * 
	 * In Pojo class all variables should be private and methods should be public
	 * Getter and setter methods are present in pojo classes which will be used to set and get payload values
	 * Java object is constructed using pojo classes
	 * Pojo classes are created based on request and response payload
	 * Serilization giving values using setter method
	 * Deserialization uses get methods to get the values
	 * Using java object data is sent or received from pojo class
	 * Libraries required jackson, jackson2, gson or johnzon or JAXB for xml
	 * Create a pojo class for main json and separate one more json for nested jsons
	 * Inject the sub json class to main json class
	 * Example courses is main class and getcourses is subclass
	 */

	public static void main(String[] args) {
		//Pojo classes are created with class names GetCourse - >Courses - > API, Mobile and WebAutomation
		String[] courseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		String response = given()
								.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
								.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
								.formParams("grant_type", "client_credentials")
								.formParams("scope", "trust")
							.when()
								.log().all()
								.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		GetCourse gc = given()
							.queryParams("access_token", accessToken)
						.when()
							.log().all()
							.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		
		//Get the json main parameters
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		//Get the nested json array data
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		//Get the price course for selected course
		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

		// Get the course names of WebAutomation
		ArrayList<String> a = new ArrayList<String>(); //Create arraylist to store the courses received
		List<WebAutomation> w = gc.getCourses().getWebAutomation();
		for (int j = 0; j < w.size(); j++) {
			a.add(w.get(j).getCourseTitle());
		}
		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));

	}
}
