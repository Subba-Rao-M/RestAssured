package rsuc.apitesting;

import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import rsuc.files.PayLoad;
import rsuc.files.ReusableMethods;

import static io.restassured.RestAssured.*;

public class Test_03_DynamicJSONTesting {
	
	
	
	@Test(priority= 1, dataProvider="addBookData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
			.header("Content-Type", "application/json")
			.body(PayLoad.addBook(isbn, aisle))
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
	
	@DataProvider(name="addBookData")
	public Object[][] getBook() {
		return new Object[][]  {{"azaaa", "4001"}, {"azaab", "4002"}, {"azaac", "4003"}};
	}
}
