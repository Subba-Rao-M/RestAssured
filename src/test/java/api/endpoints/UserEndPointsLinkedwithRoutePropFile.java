package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsLinkedwithRoutePropFile {
	
	//method used to get the urls from routes.properties file
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // routes is properties file no need to specify the .properties extention
		return routes;
	}
	
	public static Response createUser(User payload){
		String create_user = getURL().getString("post_user");
		Response response =given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(create_user);
		
		return response;
		}
	
	
public static Response getUser(String userName){
	String get_user = getURL().getString("get_user");
		Response response =given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
		.when()
			.get(get_user);
		
		return response;
		}

public static Response updateUser(String userName, User payload){
	String update_user = getURL().getString("update_user");
	Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
	.when()
		.put(update_user);
	
	return response;
	}


public static Response deleteUser(String userName){
	String delete_user = getURL().getString("delete_user");
	Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
	.when()
		.delete(delete_user);
	
	return response;
	}
}
