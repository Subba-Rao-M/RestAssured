package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());//hashcode to generate unique id
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(8, 13));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		// Logs Generating
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void test_PostUser(){
		logger.info("Creating User");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
		logger.info("User is created");
	}
	@Test(priority=2)
	public void test_getUserByName() {
		logger.info("Reading user info");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
		logger.info("Read user details");
	}
	
	
	@Test(priority=3)
	public void test_updateUserByUserName() {
		logger.info("Updating user info");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload );
		response.then().log().all();
		//response.then().log().body().statusCode(200); //One more way of assertion
		Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
		logger.info("Updated user info");
		//checking data after update
		Response updatedResponse = UserEndPoints.getUser(this.userPayload.getUsername());
		updatedResponse.then().log().all();
		Assert.assertEquals(updatedResponse.getStatusCode(), 200, "Status code is not matching");
		logger.info("Reading updated user info");
	}
	
	@Test(priority=4)
	public void test_deleteUserByName() {
		logger.info("Deleting user info");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
		logger.info("Deleting user completed");
	}
	

}
