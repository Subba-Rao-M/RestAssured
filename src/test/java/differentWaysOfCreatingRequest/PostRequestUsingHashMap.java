package differentWaysOfCreatingRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class PostRequestUsingHashMap {
/* Post request using Hash Map
 * Uses key and value option to post the data using put
 * Useful only for small and simple JSON data 
 
 */
	
	@Test(priority=1)
	void postUserUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Test_User4");
		data.put("location", "France");
		data.put("phone", "9999999999");
		String courseArray[] = {"C", "C++"}; //when there are multiple array items for one key create the array
		data.put("cources", courseArray); // Assign the array value to key
		
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("Test_User4"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("9999999999"))
			.body("cources[0]", equalTo("C")) // Use JSON path reference to identify the objects in array
			.body("cources[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
			
	}

}
