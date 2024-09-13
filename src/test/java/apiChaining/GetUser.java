package apiChaining;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;



public class GetUser {
	@Test
	void test_GetUser(ITestContext context) {
		//String bearerToken = (String) context.getAttribute("Bearer_Token");
		//int id=(int) context.getAttribute("user_id");
		String bearerToken = (String) context.getSuite().getAttribute("Bearer_Token");
		int id=(int) context.getSuite().getAttribute("user_id");
		
		
		given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
