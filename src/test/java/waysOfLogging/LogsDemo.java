package waysOfLogging;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;


public class LogsDemo {
	@Test(priority=1)
	void testLogAll() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and() // Optional to add and for multiple validations
			.header("Content-Encoding", "gzip")
			.header("Server", "gws")
			//.log().all();  //--------> Prints entire response in console window
			//.log().body(); // ONly response body is printed
			//.log().cookies();
				.log().headers();
	}
}
