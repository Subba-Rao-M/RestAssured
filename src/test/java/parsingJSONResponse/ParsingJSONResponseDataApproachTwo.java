package parsingJSONResponse;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class ParsingJSONResponseDataApproachTwo {
	@Test(priority=1)
	void testJSONResponse() {
		
		Response res = given()
			.contentType("application/json")
		.when()
			.get("https://petstore.swagger.io/v2/pet/10");
		
		
		// Based on res data testng assertions can be used and this is better approach to traverse through json body
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getContentType(), "application/json");
		String name = res.jsonPath().get("name").toString();
		Assert.assertEquals(name, "doggie");
		String status = res.jsonPath().get("status").toString();
		Assert.assertEquals(status, "string");
	}

}
