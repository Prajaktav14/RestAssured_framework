package TestClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_methods.CommonMethodUtilities;
import Common_methods.Common_methods_Get;
import RequestRepository.GetRequestRepository;
import io.restassured.path.json.JsonPath;

public class Get_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "" ;
	    int res_StatusCode = 0;
		String baseUri = GetRequestRepository.BaseURL();
		String resource = GetRequestRepository.resource();
		String requestBody = GetRequestRepository.Get_Request_TC1();
		//System.out.println(requestBody);

		for (int i=0; i<5; i++)
		{	
			res_StatusCode=Common_methods_Get.responseStatusCodeExtractor(baseUri, resource, requestBody);
			
			if (res_StatusCode == 200)
			{
				responseBody = Common_methods_Get.responseBodyExtractor(baseUri,resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
	          else
	        {
	        	  System.out.println("correct status code is not found in the iteration " + i);
	        }
		}
		
		CommonMethodUtilities.evidenceFileCreator("Get_Tc1",requestBody,responseBody);
		Assert.assertEquals(res_StatusCode, 200);
	}
	
	public static void responseBodyValidator(String responseBody)
	{
        JsonPath jsp =new JsonPath(responseBody);
        
        int arrayLength =jsp.getInt("data.size()");
        //System.out.println(arrayLength);
		
		//Extract Responsebody parameters
		
		int ID []= {7, 8, 9, 10, 11, 12};
		String EMAIL []= {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
		String FNAME []= {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
		String LNAME []= {"Lawson", "Ferguson", "Funke","Fields","Edwards","Howell"};
		String AVATAR[]= {"https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg", "https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg", "https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg"};
		
		for(int i = 0; i<arrayLength; i++)
		{
			
			int resID=jsp.getInt("data["+i+"].id");
			String resEmail=jsp.getString("data["+i+"].email");
			String resFname=jsp.getString("data["+i+"].first_name");
			String resLname=jsp.getString("data["+i+"].last_name");
			String resAvatar=jsp.getString("data["+i+"].avatar");
			
//			System.out.println(resID);
//			System.out.println(resEmail);
//			System.out.println(resFname);
//			System.out.println(resLname);
//			System.out.println(resAvatar);
			
			Assert.assertEquals(resID, ID[i]);
			Assert.assertEquals(resEmail, EMAIL[i]);
			Assert.assertEquals(resFname, FNAME[i]);
			Assert.assertEquals(resLname, LNAME[i]);
			Assert.assertEquals(resAvatar, AVATAR[i]);
		}
		

	}

	}



	
	
