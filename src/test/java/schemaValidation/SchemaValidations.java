package schemaValidation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidations {
	
	/****
	 * Field Validations done
	 * Response validation - Validating data in response
	 * Schema Validation - Focus on type of data not the data
	 * Response should be there like how schema was defined
	 * Convert JSON to JSONShema to test this
	 */
	@Test(priority=1)
	void jsonSchemaValidation() {
		
		given()
		.when()
			.get("https://simple-books-api.glitch.me/books")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonShemaResource.json")) // This file should be under resourses folder to check under classpath
			.log().all();
		
	}
	
/*** Syntax
 * In Postman only json schema validation is supported not xml
 * xml schema validation has different versions based on development
 * XML response  has xml format and schema will be in .xsd format
 * In Json both schema and response will be in json format
 * Tool to convert xml to xsd convertsimple.com
 * Place the xsd file in resources folder
 *  **/
	
	void xmlSchemaValidation() {
		
		given()
		.when()
			.get("apiURL")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("XmlShemaResource.xsd")) // This file should be under resourses folder
			.log().all();
		
	}

}
