package parsingJSONResponse;

import static io.restassured.RestAssured.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class ParsingJSONResponseDataArray {
	
	@Test(priority=1)
	void testJSONResponse() {
		Response res = when().get("http://localhost:3000/student");

	    // Print the response to check its structure
	    System.out.println("Response: " + res.asString());

	    // If the response is a JSON array, use JSONArray instead
	    JSONArray jsonArray = new JSONArray(res.asString());

	    boolean status = false;
	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jo = jsonArray.getJSONObject(i);
	        String sName = jo.get("name").toString();
	        System.out.println(sName);
	        if (sName.equals("Test_User1")) {
	            status = true;
	            break;
	        }
	    }
	    Assert.assertEquals(status, true);
		
	}
}
