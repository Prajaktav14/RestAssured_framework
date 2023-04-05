package TestClass;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_methods.CommonMethodUtilities;
import Common_methods.Common_method_API;
import RequestRepository.PostRequestRepository;
import io.restassured.path.json.JsonPath;

public class Post_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "" ;
	    int res_StatusCode = 0;
		String baseUri = PostRequestRepository.BaseURL();
		String resource = PostRequestRepository.resource();
		String requestBody = PostRequestRepository.Post_Request_TC1();

		for (int i=0; i<5; i++)
		{	
			res_StatusCode=Common_method_API.responseStatusCodeExtractor(baseUri, resource, requestBody);
			
			if (res_StatusCode == 201)
			{
				responseBody = Common_method_API.responseBodyExtractor(baseUri,resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
	          else
	        {
	        	  System.out.println("correct status code is not found in the iteration " + i);
	        }
		}
		
		CommonMethodUtilities.evidenceFileCreator("Post_Tc1",requestBody,responseBody);
		Assert.assertEquals(res_StatusCode, 201);
}
	
		public static void responseBodyValidator(String responseBody)
		{
			// create jsonPath object to extract responsebody parameters
			JsonPath jsp = new JsonPath(responseBody);

			// extract responsebody parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_id = jsp.getString("id");
			String res_createdAt = jsp.getString("createdAt");

			//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);

			// validate responsebody parameter
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "leader");
			Assert.assertNotNull(res_id, "assertion error , id parameter is null");

			// extract date from createdAt parameter
			String actual_date = res_createdAt.substring(0, 10);
			String current_date = LocalDate.now().toString(); // Create a date object
			Assert.assertEquals(actual_date, current_date);
			//System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

		}
}

		
	


