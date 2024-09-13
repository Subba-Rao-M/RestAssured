package parsingJSONResponse;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class ParsingJSONResponseDataObject {
		@Test(priority=1)
		void testJSONResponse() {
			
		Response res = 
			when()
				.get("http://localhost:3000/student");
			
			JSONObject jo = new JSONObject(res.asString());
			System.out.println(jo);
			boolean status = false;
			for(int i=0; i<jo.getJSONArray("student").length();i++) { // If the students are grouped as Students
				String sName = jo.getJSONArray("student").getJSONObject(i).get("name").toString();
				System.out.println(sName);
				if(sName.equals("Test_User1")) {
					status = true;
					break;
				}
			}
			Assert.assertEquals(status, true);
			/*if(status==false) {
				System.out.println("User not found");
			}*/
			
		}
			
	

}
