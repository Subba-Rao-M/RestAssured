package pathAndQueryParameters;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParams {
	
	@Test
	void testPathAndQueryParams() {
		/***
		 * //https://reqres.in/api/users?page=2&id=5
		 * Path parameter has user defined variable with key and value reference
		 * Path parameter is added in when() with URL
		 * Query parameter are not variable where key value reference is not changed should be same as per document and only value can be mapped
		 * Query parameter is directly sent with request without adding it to URL in when()
		 * with single method cannot add multiple query and path parameter,each needs to be added separately
		 * *****/
		
		//https://reqres.in/api/users?page=2&id=5
		
		
		given()
			.pathParam("Path1", "api")
			.pathParam("Path2", "users")
			.queryParam("page", "2")
			.queryParams("id","5")
		.when()
			.get("https://reqres.in/{Path1}/{Path2}") // Only path parameters entered here and query parameter is directly sent with request
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
