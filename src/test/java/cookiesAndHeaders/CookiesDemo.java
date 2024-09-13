package cookiesAndHeaders;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
public class CookiesDemo {
	/**********
	 * 
	 */
	
	@Test
	void verifyCookiesDemo() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.cookie("AEC", equalTo("AVYB7cqftjidn9n9MJ4WIfuQoomDE_4tpH2BOwVRz3aOQi7AQtnHBlrSMw; expires=Wed, 05-Mar-2025 12:10:27 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax"))
			.log().all(); // above will fail as cookie value is not constant and it changes for each request
		
	}
	
	
	// How to capture cookie and its value
	
	@Test(priority=2)
	void testGetCookiesInfo() {
		Response res= given() // res has headers, cookie and body, etch all details
		.when()
			.get("https://www.google.com");
		
		//To get single cookine info
		
		String Cookie_Value1 = res.getCookie("AEC");
		System.out.println("Cookie Value rededived ---------->"+Cookie_Value1);
		
	}
	

	@Test(priority=3)
	void testGetAllCookiesInfo() {
		Response res= given() // res has headers, cookie and body, etch all details
		.when()
			.get("https://www.google.com");
		
		//To get all cookies info
		
		Map < String, String > Cookie_Values = res.getCookies(); 
		for(String k : Cookie_Values.keySet()) {
			String Cookie_Value = res.getCookie(k);
			System.out.println("For Cookie   -->"+k+"  Value is ---------->"+Cookie_Value);
		}
		
		
	}

}
