package apiChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	@Test
	void test_UpdateUser(ITestContext context) {
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().firstName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		//String bearerToken = (String) context.getAttribute("Bearer_Token");
		//int id=(int) context.getAttribute("user_id");
		String bearerToken = (String) context.getSuite().getAttribute("Bearer_Token");
		int id=(int) context.getSuite().getAttribute("user_id");
		given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
