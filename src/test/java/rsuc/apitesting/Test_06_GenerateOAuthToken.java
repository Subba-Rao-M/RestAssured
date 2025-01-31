package rsuc.apitesting;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class Test_06_GenerateOAuthToken {

	public static void main(String[] args) {
		/**
		 * To display user information first there will be authentication first In
		 * network you can see one api call but in back end it may be calling 3 more
		 * services to get details for above service All the services called in back end
		 * also will have authentication in background Authentication will not be shared
		 * to all services in backend by compromising customer info Oauth is a framework
		 * or process to handle authorizatiopn Separate oauth server will be maintained
		 * to handle authorization Authorization Server api will give authorization
		 * token Backend server will make call to get authorization token as internal
		 * family member To grant access different ways: client credential token,
		 * password grant and authorization token grant In client credential grant type,
		 * they will generate 2 parameters client id and client secret With above 2
		 * details authorization can be checked and grant token to access the back end
		 * services For password, user name and password us used to generate token
		 * 
		 * All secure information should go through post call only and not get call
		 * 
		 * In postman give post url, enter form parameters client id, client secret,
		 * grant type and scope access_token, token type, expiry time and refresh token
		 * is generated access token is used to make actual api call and in query
		 * parameter above token is used to get details access token is sent in form and
		 * query parameter based on projects
		 * 
		 * 
		 */

	String response = given()
						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
						.formParams("grant_type", "client_credentials")
						.formParams("scope", "trust")
					.when()
						.log().all()
						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
							.asString();
		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);

		String r2 = 	given()
							.queryParams("access_token", accessToken)
						.when()
							.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
								.asString();
		System.out.println(r2);
		System.out.println("Execution - End");

	}

}
