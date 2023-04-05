package Common_methods;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


public class Common_method_API {
	public static int responseStatusCodeExtractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int responseStatusCode = given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().statusCode();
		return responseStatusCode;
	}
		
		public static String responseBodyExtractor(String baseuri, String resource, String requestBody)
		{
			RestAssured.baseURI=baseuri;
			String responseBody = given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().response().asString();
			return responseBody;
	
		}
    
}
