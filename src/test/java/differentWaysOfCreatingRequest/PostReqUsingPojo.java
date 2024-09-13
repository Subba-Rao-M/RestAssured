package differentWaysOfCreatingRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostReqUsingPojo {
	
	/*****
	 * Post request using POJO Class (Plain old java object)
	 * For every variable create getter and setter method
	 * Setter method will assign the value to variable
	 * Getter method will get the value of variable
	 * Most popular way of updating data
	 */
	@Test
	void postUserUsingOrgJson() {
		
		Students data = new Students(); // data stored in object format
		
		data.setName("Test_User6");
		data.setLocation("USA");
		data.setPhone("1199988999");
		String courseArray[] = {"C", "Java"};
		data.setCourses(courseArray);

		given()
			.contentType("application/json")
			.body(data) 
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("Test_User6"))
			.body("location", equalTo("USA"))
			.body("phone", equalTo("1199988999"))
			.body("courses[0]", equalTo("C")) // Use JSON path reference to identify the objects in array
			.body("courses[1]", equalTo("Java"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
			
	}
}
