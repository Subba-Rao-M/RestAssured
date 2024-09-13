package differentWaysOfCreatingRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostReqUsingExtJSONFile {
	
	/**
	 * *
	 * @throws FileNotFoundException *****
	 * 
	 */
	
	@Test
	void postUserUsingExtJsonFile() throws FileNotFoundException {
		
		File file = new File("C:\\MyAutomationLearning\\apiTesting\\src\\test\\java\\myLearningReference\\differentWaysOfCreatingRequest\\Students.json");
		
		FileReader fileReader = new FileReader(file);
		
		JSONTokener jt = new JSONTokener(fileReader);
		
		JSONObject data = new JSONObject(jt); // data stored in object format
		
		given()
			.contentType("application/json")
			.body(data.toString()) // Since the data in object format
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("Test_User7"))
			.body("location", equalTo("USA"))
			.body("phone", equalTo("1199988999"))
			.body("courses[0]", equalTo("C")) // Use JSON path reference to identify the objects in array
			.body("courses[1]", equalTo("Java"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
			
	}
	 
	

}
