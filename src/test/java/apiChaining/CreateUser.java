package apiChaining;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;


public class CreateUser {
	
	@Test
	void test_CreateUser(ITestContext context) {
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().firstName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "28b8dfc1b4f11ae2fded540f41c5437afc5b5d1b54683dfdc034c03009a590e8";
		
		int id = given()
			.header("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		// Test Case Level -- when all classes are under same test case
		//context.setAttribute("user_id", id);
		//context.setAttribute("Bearer_Token", bearerToken);
		//System.out.println("Generated ID value is----->"+id);
		
		// to make below available for all suite test cases i.e when classes are destributed in different test cases
		// Since suite contains test cases below will work for both scenarios like global variables
		
		context.getSuite().setAttribute("user_id", id);
		context.getSuite().setAttribute("Bearer_Token", bearerToken);
		
	}
}
