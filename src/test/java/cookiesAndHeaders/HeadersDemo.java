package cookiesAndHeaders;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadersDemo {
	/**********
	 * 
	 */
	
	@Test(priority=1)
	void verifyHeadersDemo() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and() // Optional to add and for multiple validations
			.header("Content-Encoding", "gzip")
			.header("Server", "gws")
			.log().all(); 
		
	}
	
	
	// How to capture header and its value
	
	@Test(priority=2)
	void testGetHeaderInfo() {
		Response res= given() // res has headers, cookie and body, etch all details
		.when()
			.get("https://www.google.com");
		
		//To get single header info
		
		String Header_Value1 = res.getHeader("Content-Type");
		System.out.println("Header Value rededived ---------->"+Header_Value1);
		
	}
	

	@Test(priority=3)
	void testGetAllHeadersInfo() {
		Response res= given() // res has headers, cookie and body, etch all details
		.when()
			.get("https://www.google.com");
		
		/*To get all header info
		Header -- > Single header name and value
		Headers -- > Multiple header name and values
		*/
		
		Headers headervalues = res.getHeaders();
		for(Header hd : headervalues) {
			System.out.println(hd.getName()+"------->"+hd.getValue());
		}
		
		
	}

}
