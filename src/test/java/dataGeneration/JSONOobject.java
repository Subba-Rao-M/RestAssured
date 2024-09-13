package dataGeneration;

public class JSONOobject {
	
	/***
	 * JSON Object --> Starts and ends with {}
	 * JSON Array ---> starts and end with  []
	 * JSON Element --> Objects inside object or array
	 */
	/*JSON OBJECT -- whole thing is one JSON object inside that we have array and then array consists of multiple json objects
	{
		books:[ -- > Represents array
		       
		       {
		    	   book1 -- ONe more JSON object, multiple book objects
		       },
		       {
		    	   book2
		       },
		       {
		    	   book3
		       }
		       ]
	}
	
	Inside one JSON object we have 1 Books Array and 3 books objects
	If stats with JSON object
	JSONObject jsonobj = new JSONObject(res.asString());
	jsonobj.getJSONArray("books").getJSONObject(1).get("JSONTitle");
	
	if starts with json array
	
	JSONArray ja = new JSONArray(res.asString());
	ja.getJSONObject(0).get("Name");
	
	
	*/
	

}
