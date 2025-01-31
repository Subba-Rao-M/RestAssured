package rsuc.apitesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //Copy above url and add staic and * to get all classes
import static org.hamcrest.Matchers.*; // Used to compare body values using equal To
//auto suggestion for static imports will not be shown

public class Test_01_APITestingBasics {

	public static void main(String[] args) {
		//Validate if Add place API is working
		/**
		 * given() - all input details, headers, query parameter and body details entered here
		 * when() - submit the api request - resource and http method
		 * then() - validate the response
		 */
		
		RestAssured.baseURI = "https://rahulshettyacademy.com"; //Add base url here
		given().log().all()
		.header("ContentType", "application/json")
		.queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "\"location\": {\r\n"
				+ "\"lat\": -38.383494,\r\n"
				+ "\"lng\": 33.427362\r\n"
				+ "},\r\n"
				+ "\"accuracy\": 50,\r\n"
				+ "\r\n"
				+ "\"name\": \"Frontline house\",\r\n"
				+ "\"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "\"address\": \"29, side layout, cohen 09\",\r\n"
				+ "\"types\": [\r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\"\r\n"
				+ "],\r\n"
				+ "\"website\": \"http://google.com\",\r\n"
				+ "\"language\": \"French-IN\"\r\n"
				+ "}")
		.when()
		.post("/maps/api/place/add/json") // Base uri and query params automatically added here
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.52 (Ubuntu)"));
		

	}

}
