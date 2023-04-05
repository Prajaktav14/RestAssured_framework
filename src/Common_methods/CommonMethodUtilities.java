package Common_methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonMethodUtilities {
	public static void evidenceFileCreator(String filename, String request, String response) throws IOException
	{
		File newTextFile= new File("D:\\RestAssuredEvidance\\" + filename + ".txt");
		if (newTextFile.createNewFile())
		{
		FileWriter dataWriter= new FileWriter(newTextFile);
		dataWriter.write("request body is :\n" + request + "\n\n");
		dataWriter.write("response body is :\n" +response);
		dataWriter.close();
		System.out.println("Request and Responsebody data saved in :" +newTextFile.getName());
	    }
		else
		{
			System.out.println(newTextFile.getName()+"Alraedy Exists plese take a backup of it");
		}
	}
}
