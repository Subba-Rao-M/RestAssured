package rsuc.apitesting;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import rsuc.files.ReusableMethods;

import static io.restassured.RestAssured.*;

public class Test_04_StaticJSONDataTesting {
	
	/**
	 * Body method accepts data in string format
	 * Convert the content of json file to bytes
	 * convert byte data to string
	 * @throws IOException 
	 */
	
	@Test()
	public void addBook() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
			.header("Content-Type", "application/json")
			.body(new String(Files.readAllBytes(Paths.get("C:\\Learning Materials\\MyAutomationLearning\\apiTestingAndRestAssured\\RestAssured\\src\\test\\java\\rsuc\\files\\AddBook.json"))))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJSON(response);
		String id = js.get("ID");
		System.out.println(id);	
		deleteBook(id);
		
	}
	

	public void deleteBook(String id) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		JSONObject data = new JSONObject(); // data stored in object format
		data.put("ID", id);
		given()
			.header("Content-Type", "application/json")
			.body(data.toString())
		.when()
			.delete("/Library/DeleteBook.php")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("msg", equalTo("book is successfully deleted"));

	}
	

}
