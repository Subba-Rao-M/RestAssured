package myLearningReference;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {
	
	int id;

	@Test(priority=4)
	void getUsers() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all(); // To log the data to console.
	}
	
	/*@Test
	void createUser() {
		
		HashMap data = new HashMap(); // Used to create data in key and value pair
		data.put("name", "Subba Rao");
		data.put("job", "Tester");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
		
	}*/
	
	
	@Test(priority=1)
	void createUser() {
		
		HashMap data = new HashMap(); // Used to create data in key and value pair
		data.put("name", "Subba Rao");
		data.put("job", "Tester");
		id= given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	}
	@Test(priority=2, dependsOnMethods= {"createUser"}) // Depends on Method will help to execute the sceipt only dependent method is passed
	void updateUser() {
		
		HashMap data = new HashMap(); // Used to create data in key and value pair
		data.put("name", "Subba Raa M");
		data.put("job", "Test Lead");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(201)
			.log().all();
		
	}
	
	@Test(priority=3, dependsOnMethods= {"createUser"}) // Depends on Method will help to execute the script only dependent method is passed
	void deleteUser() {
		when()// when there is no given dont add .
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
		
	}

}
