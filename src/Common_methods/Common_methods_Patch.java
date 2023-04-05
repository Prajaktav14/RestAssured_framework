package Common_methods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Common_methods_Patch {
	public static int responseStatusCodeExtractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int responseStatusCode = given().header("Content-Type","application/json").body(requestBody).when().patch(resource).then().extract().statusCode();
		return responseStatusCode;
	}
		
		public static String responseBodyExtractor(String baseuri, String resource, String requestBody)
		{
			RestAssured.baseURI=baseuri;
			String responseBody = given().header("Content-Type","application/json").body(requestBody).when().patch(resource).then().extract().response().asString();
			return responseBody;
	
		}
}
