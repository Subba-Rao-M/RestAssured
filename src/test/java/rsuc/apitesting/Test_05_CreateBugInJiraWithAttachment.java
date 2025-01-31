package rsuc.apitesting;


import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test_05_CreateBugInJiraWithAttachment {
/**
 * Basic authentication is used in this script
 * and multipart is used to upload the attachment
 * https://www.base64encode.org/ use this to get encoding or decoding values
 * @param args
 */
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy-team.atlassian.net/";
		String createIssueResponse = 
				given()
					.header("Content-Type", "application/json")
					.header("Authorization",
									"Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")
					.body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n"
						+ "          \"key\": \"SCRUM\"\n" + "       },\n"
						+ "       \"summary\": \"Website menu links are not working- automation Rest Assured\",\n"
						+ "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
					.log().all()
				.when()
					.post("rest/api/3/issue")
				.then()
					.log().all()
					.assertThat()
					.statusCode(201)
					.contentType("application/json")
					.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);
		
		// Add attachment
		given()
			.pathParam("key", issueId)
			.header("X-Atlassian-Token", "no-check")
			.header("Authorization",
				"Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")
			.multiPart("file", new File("C:\\Learning Materials\\WebServices\\TestUploadFile.txt"))
			.log().all()
		.when()
			.post("rest/api/3/issue/{key}/attachments")
		.then()
			.log().all()
			.assertThat().statusCode(200);
		
		
		//Get the bug details:
		
		given()
			.pathParam("key", issueId)
			.header("Content-Type", "application/json")
			.header("Authorization",
							"Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")
		.when()
			.get("/rest/api/3/issue/{key}")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200);
		
	//Delete the bug :
		
		given()
			.pathParam("key", issueId)
			.header("Authorization",
							"Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")
		.when()
			.delete("/rest/api/3/issue/{key}")
		.then()
			.log().all()
			.assertThat()
			.statusCode(204);
		
		
		
	}

}
