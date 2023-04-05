package RequestRepository;

import java.io.IOException;
import java.util.ArrayList;

import Common_methods.Get_Data;

 public class PatchRequestRepository {
	public static String BaseURL()
	{
		String baseuri ="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource="api/users/2";
		return resource;
	}
	public static String Patch_Request_TC1() throws IOException
	{
		ArrayList<String> data =Get_Data.getDataExcel("Patch_Data", "TC1");
		String Name = data.get(2);
		String Job = data.get(3);
		String requestBody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestBody;
	}
}
