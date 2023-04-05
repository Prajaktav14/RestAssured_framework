package TestClass;
import java.io.IOException;
import java.time.LocalDate;

//import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_methods.CommonMethodUtilities;
import Common_methods.Common_methods_Put;
import RequestRepository.PutRequestRepository;
import io.restassured.path.json.JsonPath;


public class Put_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "" ;
	    int res_StatusCode = 0;
		String baseUri = PutRequestRepository.BaseURL();
		String resource = PutRequestRepository.resource();
		String requestBody = PutRequestRepository.Put_Request_TC1();

		for (int i=0; i<5; i++)
		{	
			res_StatusCode=Common_methods_Put.responseStatusCodeExtractor(baseUri, resource, requestBody);
			
			if (res_StatusCode == 200)
			{
				responseBody = Common_methods_Put.responseBodyExtractor(baseUri,resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
	          else
	        {
	        	  System.out.println("correct status code is not found in the iteration " + i);
	        }
		}
		
		CommonMethodUtilities.evidenceFileCreator("Put_Tc1",requestBody,responseBody);
		Assert.assertEquals(res_StatusCode, 200);
}
	
		public static void responseBodyValidator(String responseBody)
		{
			// create jsonPath object to extract responsebody parameters
			JsonPath jsp = new JsonPath(responseBody);

			// extract responsebody parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_updatedAt = jsp.getString("updatedAt");

			//System.out.println("name : " + res_name + "\njob : " + res_job + "\ncreatedAt : " + res_updatedAt);

			// validate responsebody parameter
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "zion resident");

			// extract date from createdAt parameter
						String actual_date = res_updatedAt.substring(0, 10);
						String current_date = LocalDate.now().toString(); // Create a date object
						Assert.assertEquals(actual_date, current_date);
						//System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

		}
}
	


