package parsingJSONResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

public class ParsingJSONResponseDataApproachOne {
	@Test(priority=1)
	void testJSONResponse() {
		
		given()
			.contentType("application/json")
		.when()
			.get("https://petstore.swagger.io/v2/pet/10")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("status", equalTo("available"))
			.body("name", equalTo("doggie"))
			.log().all();
		
	}

}
