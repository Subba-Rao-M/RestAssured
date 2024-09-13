package parsingXMLResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLRDAppoachTwo {
	@Test(priority=1)
	void testJSONResponse() {
		
		Response res = given()
			.accept("application/xml")
		.when()
			.get("https://petstore.swagger.io/v2/pet/9223372036854762837");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml");
		String status = res.xmlPath().get("Pet.status").toString();
		Assert.assertEquals(status, "available");
		String name = res.xmlPath().get("Pet.name").toString();
		Assert.assertEquals(name, "Jimmy");
		
		//Another method
		/*
		XmlPath xmlObj = new XmlPath(res.asString()); // Convert entire response into string format use as string and to string is used to convert data to string
		List <String > values = xmlObj.getList("xmlNodeVlues.values to get values"); // List of values depends on data type
		Assert.assertEquals(values.size(), 10); // compare total number of values
		
		Verify the field is present in response
		boolean status = false;
		List <String > name = xmlObj.getList("xmlNodeVlues.pathtill.Name");
		for(String localName: name){
		System.out.Println("Value"+localName);
		if(localName == "testconditionvalue"){
		status = true;
		break;
		}
		}
		Assert.assertEquals(status, true);
		
		
		*/
	}
}
