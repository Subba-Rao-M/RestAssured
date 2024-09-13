package SerializationDeserialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import differentWaysOfCreatingRequest.Students;

public class SerializationAndDeserialize {
	
	/***
	 * Body(json) --> Request --> Response(Json)
	 * Creating body using hashmap, pojo class, json or gson
	 * many times we will use pojo class to create the request body
	 * Serialization --> PoJO class object to JSON conversion process
	 * DeSerialization -- > JSON object to PoJO class object conversion process
	 * @throws JsonProcessingException 
	 * */
	
	@Test(priority=1, description=" Serialization ->Convert Pojo to JSON")
	void convertPojoToJson() throws JsonProcessingException {
		Students pojoData = new Students(); // data stored in object format
		
		pojoData.setName("Test_User6");
		pojoData.setLocation("USA");
		pojoData.setPhone("1199988999");
		String courseArray[] = {"C", "Java"};
		pojoData.setCourses(courseArray);
		//Convert Java object to JSON object using jackson library for new version of restassured remove from pom file
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoData);
		System.out.println(jsonData);
		
	}
	
	
	@Test(priority=2, description=" De-serialization ->Convert JSON to Pojo")
	void convertJsonToPojo() throws JsonProcessingException {
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Test_User6\",\r\n"
				+ "  \"location\" : \"USA\",\r\n"
				+ "  \"phone\" : \"1199988999\",\r\n"
				+ "  \"courses\" : [ \"C\", \"Java\" ]\r\n"
				+ "}"; 
		
		
		//Convert JSON data to Pojo object using jackson library
		ObjectMapper objectMapper = new ObjectMapper();
		Students stdPojo = objectMapper.readValue(jsonData, Students.class);
		String name = stdPojo.getName();
		String course1= stdPojo.getCourses()[0];
		String course2= stdPojo.getCourses()[1];
		System.out.println(name+"---------"+course1);
		
		
	}
	

}
