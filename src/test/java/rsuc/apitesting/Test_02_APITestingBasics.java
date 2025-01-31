package rsuc.apitesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import rsuc.files.PayLoad;
import rsuc.files.ReusableMethods;

import static io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;

import org.testng.Assert; 

public class Test_02_APITestingBasics {

	public static void main(String[] args) {
		//Scenario Add Place - Update the place and get the place and verify updated address
		
		//Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com"; 
		String response = given().log().all()
		.header("ContentType", "application/json")
		.queryParam("key", "qaclick123")
		.body(PayLoad.AddPlace())
		.when()
		.post("/maps/api/place/add/json") 
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
		.extract().response().asString();
		System.out.println("Response Data **************************** Start");
		System.out.println(response);
		System.out.println("Response Data **************************** End");
		
		//Creating object for JSON path class which takes json as string parameter and converts to actual json
		
		JsonPath json = ReusableMethods.rawToJSON(response); // for parsing json
		String place_id = json.getString("place_id");
		System.out.println("Response Data  for place id : " +place_id);
		
		//Update address
		String newaddress = "70 winter walk, Africa";
		given().log().all()
		.header("ContentType", "application/json")
		.queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when()
		.put("/maps/api/place/update/json") 
		.then().log().all().assertThat()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getresponse = given().log().all()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", place_id)
		.when()
			.put("/maps/api/place/get/json") 
			.then().log().all().assertThat()
			.statusCode(200)
			.extract().response().asString();;
			//JsonPath js = new JsonPath(getresponse); // for parsing json
			JsonPath js = ReusableMethods.rawToJSON(getresponse);
			String getaddress = js.getString("address");
			System.out.println("Response Data  for place id : " +getaddress);
			/**if(getaddress.equals(newaddress)) {
				System.out.println("Test Passed");
			}
			else {
				System.out.println("Test Failed");
			}
			*/
			
			Assert.assertEquals(getaddress, newaddress);

	}

}
