package com.common.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.authentication.utils.LoginUtils;
import com.aventstack.extentreports.ExtentTest;
import com.common.utils.BaseUtils;
import com.common.utils.ExcelParserUtils;

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
	public static final String estimatefile_path = System.getProperty("user.dir")
			+ "/src/test/resources/EstimateTestData.xlsx";
	public static final String pdf_path = System.getProperty("user.dir")
			+ "/output";
	public static final String Portfolio_properties_upload_sqft = System.getProperty("user.dir")
			+ "/src/test/resources/upload_portfolio_sqft.xlsm";
	public static final String Portfolio_properties_upload_sqm = System.getProperty("user.dir")
			+ "/src/test/resources/upload_portfolio_sqm.xlsm";
	public static final String existngUserSheet = "Existing_users";
	public static final String UsersSheet = "Users";
	public static final String scoreCardSheet = "scorecard";
	public static final String portfolioSheet = "portfolio";
	public static final String estimateSheet = "Projects";
	public static final String emailSheet = "emails";
	public static final String tokenSheet = "verifyToken";
	public static final String emailListSheet = "email_list";
	public static final String estimateList = "setData";
	public static final String estimateSingleList = "single";
	public static final String estimateterm3 = "3term";
	public static final String estimateterm5= "5term";
	public static final String estimateV1Sheet = "NoDiscountV1";
	public static final String estimateV1DiscountSheet = "Portfolio";
	public static final String estimateV1CornerStoneSheet = "Cornerstone";
	public static final String estimateParams= "Parameters";
	public static final String estimateDiscount= "Discount";
	public static final String v1_projectSheet = "v1project";
	public static String username;
    public static String password;
    public static String role;
	public static final String sheetName = "TestValues";
	public static int rowNumOne=1;
    public static int rowNumTwo=2;
    public static int rowNumThree=3;
    public static int rowNumFour=4;
    public static int rowNumFive=5;
    public static String email;
    public String header;
    public String admin_Header;
    public String projectId;
    public String reviewId;
    public String docId;
    public String v1projectId;
    public LoginUtils loginUtil = new LoginUtils();
    public static Logger logger;
    public static ExtentTest test;
    public static ExtentTest testlog;
	public Response res;
	public BaseUtils dataUtil = new BaseUtils();
	public int userid;
	public String token;
	public String estimateId;
	public String v1EstimateId;
	public String projectsIdForEstimate;
	public String env;
	
	@BeforeClass
	public void setup() throws IOException {
		
		env = ExcelParserUtils.getSingleCellData(loginUserfile_path, sheetName, "ENV", 2);
		RestAssured.baseURI = env;
	
		 Object className = this.getClass().getName(); 
		 logger = LogManager.getLogger((String)className);
		 PropertyConfigurator.configure(System.getProperty("user.dir")
					+ "/src/test/resources/log4j.properties");
		 logger.setLevel(Level.DEBUG);
		 header = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Token", 2);
		 admin_Header = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Token", 3);
		
	}
	

	@AfterMethod 
	public void printLog() {
		long responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
		logger.info("Response Time: "+responsetime);
		logger.info(res.getDetailedCookies());
		logger.info("Verifies response from API::"+ res.asString());

		
	}
	
	
	public String trailcomma(String str) {
		String trailed = null ;
			if( str.indexOf(",") != -1 )
		 {
		    trailed =  str.replaceAll(",","");
		 }
		
		return trailed;
	}
	
}
