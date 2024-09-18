package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload){
		
		Response response =given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.create_user);
		
		return response;
		}
	
	
public static Response getUser(String userName){
		
		Response response =given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		.when()
			.get(Routes.get_user);
		
		return response;
		}

public static Response updateUser(String userName, User payload){
	
	Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
	.when()
		.put(Routes.update_user);
	
	return response;
	}


public static Response deleteUser(String userName){
	
	Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
	.when()
		.delete(Routes.delete_user);
	
	return response;
	}
}
