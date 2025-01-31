package rsuc.apitesting;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import rsuc.files.PayLoad;

public class Test_03_APITestingUsingMocking {
	
	public static void main(String [] args) {
	
	JsonPath js = new JsonPath(PayLoad.CoursePrice()); //Call mock data from payload
	
	//Print number of courses returned by API
	
	int noOfCoureses = js.getInt("courses.size()"); //courses is an array and get its size
	System.out.println("Response Data  for courses : " +noOfCoureses);
	
	//Print purchase amount
	
	int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	System.out.println("Response Data  for purchase amount : " +purchaseAmount);
	
	//To get the first course title
	String firstTitle =js.get("courses[0].title"); // get or getstring can be used for string
	System.out.println("Response Data  for first course title: " +firstTitle);
	
	//Print all course titles and their prises
	
	for(int i=0; i< noOfCoureses; i++) {
		String title = js.get("courses["+i+"].title");
		System.out.println(title);
		int price = js.getInt("courses["+i+"].price");
		System.out.println(price);
	}
	
	//To get the number of copies sold for RPA
	
	for(int i=0; i< noOfCoureses; i++) {
		String title = js.get("courses["+i+"].title");
		if(title.equalsIgnoreCase("RPA")) {
		int copies = js.getInt("courses["+i+"].copies");
		System.out.println("RPA copies "+copies);
		break;
		}
	}
	
	//Verify total price is equal sum of all book copies sold
	int totalPrice = 0;
	for(int i=0; i< noOfCoureses; i++) {
		int copies = js.getInt("courses["+i+"].copies");
		int price = js.getInt("courses["+i+"].price");
		int BookTotalPrice = copies*price;
		System.out.println(BookTotalPrice);
		totalPrice+= BookTotalPrice;
		System.out.println(totalPrice);
		}
	
		Assert.assertEquals(totalPrice, purchaseAmount);
	
	
	
	}
}
