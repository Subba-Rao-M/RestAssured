package parsingXMLResponse;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * IN XML response start from initial tag which indicates Node
 * Traverse through each node by using node
 * Pass the index if more than one nodes in response 
 * Pet[0].status -- Index starts from 0
 * 
 * 
 * ***************/

import org.testng.annotations.Test;
public class ParsingXMLRDApproachOne {
		@Test(priority=1)
		void testJSONResponse() {
			
			given()
				.accept("application/xml")
			.when()
				.get("https://petstore.swagger.io/v2/pet/9223372036854759771")
			.then()
				.statusCode(200)
				.header("Content-Type", "application/xml")
				.body("Pet.status", equalTo("available"))
				.body("Pet.name", equalTo("Jimmy"))
				.log().all();
			
		}

}
