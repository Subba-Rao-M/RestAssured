package authorization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class Authorization {
	
	/**************
	 * Authentication - Check if the User is valid or not
	 * Authorization - Valid user has required permission or not to access the service
	 * Rest Assured Authentications:
	 * Basic
	 * Digest
	 * Preemptive
	 * Bearer Token-- > Passed as header or query param based on api documentation
	 * API Key -- > Passed as header or query param 
	 * Oauth 1 or 2.0
	 * All above uses different algorithms internally
	 */
	@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=3, description = " It is combination of basic and preemptive, an algorithm added above basic")
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=4, description = "Test Bearer Token")
		void testBearerToken() {
		
		String bearerToken = ""; // Paste value from github here
		
		given()
		.headers("Authorization", "Bearer "+bearerToken) // Bearer token value is passed as header	.
	.when()
		.get("https://api-github.com/user/repos")
	.then()
		.statusCode(200)
		.log().all();
			
		}
	
	void testOuthOne1AUthentication() {
		given()
		.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret") 
	.when()
		.get("URL")
	.then()
		.statusCode(200)
		.log().all();
			
	}
	
		
	@Test(priority=5, description = "Test Oauth Token2 authentication")
	void testOauth2Token() {	
	given()
		.auth().oauth2("Tokenfrom Github")
	.when()
		.get("https://api-github.com/user/repos")
	.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(priority=6, description = "Test API Key authentication and passed as query parameter")
	void testAPIKeyAuthentication() {	
	given()
		.queryParam("appid", "APIKeyvalue")
	.when()
		.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
	.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(priority=6, description = "Test API Key authentication and passed as query parameter")
	void testAPIKeyAuthMethod() {	
	given()
		.queryParam("appid", "APIKeyvalue")
		
		.pathParam("myParam", "/data/2.5/forecast/daily")
		.queryParam("q", "Delhi")
		.queryParam("units", "metric")
		.queryParam("cnt", 7)
	.when()
		.get("https://api.openweathermap.org/{myParam}")
	.then()
		.statusCode(200)
		.log().all();
		
	}

}
