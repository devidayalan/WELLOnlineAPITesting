package com.common.base;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

public class HelperMethods extends TestBase {
	public static long responsetime;
	public static String ZipCode = "20037";
	public static Response res;
	public static String fetchedID;
	public static String ProgramID;
	
  
	
	public static String getLabel(long responsetime) {
		
		if (responsetime <= 4000){
			
	    return "<span class='label outline info'>" + HelperMethods.responsetime + " Milliseconds" + "</span>";
		}
		
		else
		{
		return "<span class='label outline fatal'>" + HelperMethods.responsetime + " Milliseconds" + "</span>";
		}
	    
	    
	}
	
	/*
	 * public static void fetchingJSONResponse(String Url) { //testlog("Pass",
	 * "Authorization Token generated"+ "<br>" +header); Response res = given()
	 * .header("Authorization", header) .when().get(Url) .then()
	 * .statusCode(STATUS_200) .extract().response();
	 * 
	 * System.out.println(res.asString()); //testlog("Pass",
	 * "Response received from API"+ "<br>" +res.asString());
	 * 
	 * }
	 */
	
	public static void responsetimevalidation(String Url) {
		given().when()
				.get(Url)
				.then()
				.time(lessThan(50000L));
	}
	
	public static void filewriteID(String url) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fetchedID);
		bw.close();
	}
	
public static void filewriteResponse(String url, String response) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(response);
		bw.close();
	}
	

	public static void testlog(String log,String message){
		System.out.println(test);
		switch(log){
		
		case "Pass":
			test.log(Status.PASS, message);
			break;
			
		case "Info":
			test.log(Status.INFO, message);
			break;
			
		 default:
	     	
	     	System.out.println("Not Valid Input");
		}
		
		
	}
	
		
	public static String filereadID(String url) throws IOException{
		
		 FileReader inputFile = new FileReader(url);
		
	     //Instantiate the BufferedReader Class
	     BufferedReader bufferReader = new BufferedReader(inputFile);

	     //Variable to hold the one line data
	     
	     String text;
	     // Read file line by line and print on the console
	     while ((text = bufferReader.readLine()) != null)   {
	        
	          fetchedID=text;
	         //System.out.println(CommonMethod.ProgramID);   
	     }
	        
	      //Close the buffer reader
	        bufferReader.close();
	        return fetchedID;
	}
	

	
}
