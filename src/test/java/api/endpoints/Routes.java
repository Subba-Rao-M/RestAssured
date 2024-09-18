package api.endpoints;


/*****
 * Swagger URL --https://petstore.swagger.io/
 * 
 * Crate User - Post -  https://petstore.swagger.io/v2/user
 * Get User - Get - https://petstore.swagger.io/v2/user{username}
 * Update User - > Put - https://petstore.swagger.io/v2/user{username}
 * Delete User - > Delete - https://petstore.swagger.io/v2/user{username}
 * 
 */
public class Routes {
	
	//Route File contains only URLS no implementation
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User Module
	public static String create_user = base_url+"/user";
	public static String get_user = base_url+"/user/{username}";
	public static String update_user = base_url+"/user/{username}";
	public static String delete_user = base_url+"/user/{username}";
}
