package rsuc.apitesting;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import rsuc.apitesting.pojos.LoginRequest;
import rsuc.apitesting.pojos.LoginResponse;
import rsuc.apitesting.pojos.OrderDetail;
import rsuc.apitesting.pojos.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
/***
 * E2E flow:
 * Login - > Create product ->  Purchase the added product - > Delete the order - > Delete the product
 * End point –

			https://rahulshettyacademy.com/api/ecom/product/add-product

				Http Method - POST
				Form Data -

				productName:qwerty
				productAddedBy:{{userId}}
				productCategory:fashion
				productSubCategory:shirts
				productPrice:11500
				productDescription:Addias Originals
				productFor:women
				
				
				Delete Product :
				
				https://rahulshettyacademy.com/api/ecom/product/delete-product/{{productId}}
				
				Http Method  DELETE
relaxedHTTPSValidation - is used with requests if there is any restrictions for http proxy to access and download from url

 * 
 */
public class Test_11_EcommerceOrderTest {

	public static void main(String[] args) {
			RequestSpecification req=	new RequestSpecBuilder()
											.setBaseUri("https://rahulshettyacademy.com")
											.setContentType(ContentType.JSON)
											.build();
			
			
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUserEmail("subbaraw@gmail.com");
			loginRequest.setUserPassword("Span@1234");
			
				
			RequestSpecification reqLogin =given()
												.relaxedHTTPSValidation()
												.log()
												.all()
												.spec(req)
												.body(loginRequest);
			
			
			LoginResponse loginResponse = reqLogin
												.when()
													.post("/api/ecom/auth/login")
												.then()
													.log()
													.all()
													.extract()
													.response()
													.as(LoginResponse.class);
			
			System.out.println(loginResponse.getToken());
			String token = loginResponse.getToken();
			System.out.println(loginResponse.getUserId());
			String userId =loginResponse.getUserId();
			
			
			//Add Product service
			
			RequestSpecification addProductBaseReq=	new RequestSpecBuilder()
															.setBaseUri("https://rahulshettyacademy.com")
															.addHeader("authorization", token)
															.build();
			
			/**
			 * Observe values are not passed as json, in above req spec content type json removed
			 * in below body is passed as param values
			 * multipart is used for sending attachment
			 * Since small response direct validation done instead of creating pojo class
			 */
			
			RequestSpecification reqAddProduct = given()
													.log()
													.all()
													.spec(addProductBaseReq)
													.param("productName", "Adidas")
													.param("productAddedBy", userId)
													.param("productCategory", "fashion")
													.param("productSubCategory", "shirts")
													.param("productPrice", "11500")
													.param("productDescription", "Lenova")
													.param("productFor", "men")
													.multiPart("productImage",new File("‪C://Users//raooosub//Downloads//adidas.png"));
			
			String addProductResponse =reqAddProduct
												.when()
													.post("/api/ecom/product/add-product")
													.then()
													.log()
													.all()
													.extract()
													.response()
													.asString();
			
			JsonPath js = new JsonPath(addProductResponse);
			String productId =js.get("productId");
			
			
			//Create Order
			RequestSpecification createOrderBaseReq=	new RequestSpecBuilder()
																	.setBaseUri("https://rahulshettyacademy.com")
																	.addHeader("authorization", token)
																	.setContentType(ContentType.JSON)
																	.build();
			
			//Create order details and if more than one values create multiple objects
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setCountry("India");
			orderDetail.setProductOrderedId(productId);
			
			//Create the list of order details class to receive multiple values and add the above objects to it
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
			orderDetailList.add(orderDetail);	
			//Add the orderslist values to Orders class
			Orders orders = new Orders();
			orders.setOrders(orderDetailList);
			
			RequestSpecification createOrderReq=given()
													.log()
													.all()
													.spec(createOrderBaseReq)
													.body(orders);

			String responseAddOrder = createOrderReq
												.when()
													.post("/api/ecom/order/create-order")
												.then()
													.log()
													.all()
													.extract()
													.response()
													.asString();
		System.out.println(responseAddOrder);



		//Delete Product

		RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder()
																	.setBaseUri("https://rahulshettyacademy.com")
																	.addHeader("authorization", token)
																	.setContentType(ContentType.JSON)
																	.build();
		
		//product id is passed as path parameter and in url replaced with {} within "
		RequestSpecification deleteProdReq =given()
												.log()
												.all()
												.spec(deleteProdBaseReq)
												.pathParam("productId",productId);

		String deleteProductResponse = deleteProdReq
													.when()
														.delete("/api/ecom/product/delete-product/{productId}")
													.then()
														.log()
														.all()
														.extract()
														.response()
														.asString();

		JsonPath js1 = new JsonPath(deleteProductResponse);

		Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
	
	}

}
