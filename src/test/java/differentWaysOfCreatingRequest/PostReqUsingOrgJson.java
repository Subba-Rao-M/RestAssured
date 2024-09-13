package differentWaysOfCreatingRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostReqUsingOrgJson {
	
	/*****
	 * Post request using org.json library
	 * In POM file add org.json library
	 */
	@Test
	void postUserUsingOrgJson() {
		
		JSONObject data = new JSONObject(); // data stored in object format
		
		data.put("name", "Test_User5");
		data.put("location", "Italy");
		data.put("phone", "9999988999");
		String courseArray[] = {"C#", "Java"}; //when there are multiple array items for one key create the array
		data.put("cources", courseArray); // Assign the array value to key
		given()
			.contentType("application/json")
			.body(data.toString()) // Since the data in object format
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("Test_User5"))
			.body("location", equalTo("Italy"))
			.body("phone", equalTo("9999988999"))
			.body("cources[0]", equalTo("C#")) // Use JSON path reference to identify the objects in array
			.body("cources[1]", equalTo("Java"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
			
	}
}
