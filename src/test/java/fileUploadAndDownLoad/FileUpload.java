package fileUploadAndDownLoad;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class FileUpload {
	
	/***
	 * java -jar file-upload-RestAPI.jar
	 * 
	 */
	@Test(priority=1)
	void singleFileUpload() {
		File myFile = new File("C:\\My_Files\\Testa1.txt");
		given()
			.multiPart("file", myFile) // Option to add file similar to postman
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName",equalTo("Testa1.txt"))
			.log().all();
		
	}
	
	@Test(priority=2)
	void multiFileUpload() {
		File myFile1 = new File("C:\\My_Files\\Test1.txt");
		File myFile2 = new File("C:\\My_Files\\Test2.txt");
		given()
			.multiPart("files", myFile1) 
			.multiPart("files", myFile2)// files should be used for multiple files
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("[1].fileName",equalTo("Test1.txt"))
			.body("[2].fileName",equalTo("Test2.txt"))
			.log().all();
		
	}
	
	@Test(priority=3)
	void multiFileUploadUsingArray() {
		File myFile3 = new File("C:\\My_Files\\Test3.txt");
		File myFile4 = new File("C:\\My_Files\\Test4.txt");
		File filearr[] = {myFile3, myFile4};
		given()
			.multiPart("files", filearr) // always it may not work based on API's depends on API designed
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("[3].fileName",equalTo("Test3.txt"))
			.body("[4].fileName",equalTo("Test4.txt"))
			.log().all();
		
	}
	
	@Test(priority=4)
	void testFileDownLoad() {
		
		given()
		.when()
			.get("http://localhost:8080/uploadFile/Testa1.txt")
		.then()
			.statusCode(200)
			.log().all();
	}
}
