package com.common.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.authentication.utils.LoginUtils;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestBase {

	public static final int STATUS_200 = 200;
	public static final int STATUS_401 = 401;
	public static final int STATUS_422 = 422;
	public static final String MESSAGE_401 = "Invalid email or password.";
	public static final String MESSAGE_422 = "422 Unprocessable Entity";
	public static final String TOKEN = "token";
	public static final String LOGIN_INVALID_SHEET = "invalid_Login";
	public static final String loginUserfile_path = System.getProperty("user.dir")
			+ "/src/test/resources/TestData.xlsx";
	public static final String existngUserSheet = "Existing_users";
	public static final String emailSheet = "emails";
	public static final String tokenSheet = "verifyToken";
	public static final String setDataSheet = "SetToken";
	
	public static final String sheetName = "TestValues";
	public static int rowNumOne=1;
    public static int rowNumTwo=2;
    public static int rowNumThree=3;
    public static int rowNumFour=4;
    public static int rowNumFive=5;
    public static String email;
    public static String password;
    public String header;
    public LoginUtils loginUtil = new LoginUtils();
    public static Logger logger;
    public static ExtentTest test;
    public static ExtentTest testlog;
	public Response res;
	
	@BeforeClass
	public void setup() {

		RestAssured.baseURI = "https://test-v2-api.wellcertified.com/api/";
	
		 Object className = this.getClass().getName(); 
		 logger = LogManager.getLogger((String)className);
		 PropertyConfigurator.configure(System.getProperty("user.dir")
					+ "/src/test/resources/log4j.properties");
		 logger.setLevel(Level.DEBUG);
		
	}
	
	@AfterMethod
	public void printLog() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("methodname is"+methodName);
		
		long responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
		
		logger.info("Response Time: "+responsetime);
		logger.info(res.getDetailedCookies());
		logger.info("Verifies response from API::"+methodName+"::" + res.asString());

		
	}
	
	
}
