package com.autentication.testCases;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.CustomDataProvider;
import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;




public class SignInAPITest extends TestBase {
	
	

	@Test (dataProvider = "ValidData",dataProviderClass = CustomDataProvider.class)
	public void testValidEmailPassword(String email, String password) throws IOException{
			res = given()
	       .param("email", email)
	       .param("password", password) 
		.when()
				.post("authenticate")
		.then()
				.statusCode(STATUS_200)
				.log().all()
				.body("$", hasKey(TOKEN)) 
				.body("any { it.key == 'token' }", is(notNullValue()))       
		        .extract().response();
		
				header = (res.path("token")).toString();
				
				
	  
	}
	
	@Test 
	public void writeTokenForAuthenticatedUser() throws IOException{
		username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2);
		password = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "password", 2);
		res = given()
			       .param("email", username)
			       .param("password", password) 
				.when()
						.post("authenticate")
				.then()
						.body("$", hasKey(TOKEN)) 
						.body("any { it.key == 'token' }", is(notNullValue())).        //authorization_token value is not null - has a value
						extract().response();
		
		header =(res.path("token")).toString();
		header = "Bearer "+header;
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 3, header);
				
	  
	}
	
	
	//@Test (dataProvider = "InvalidData",dataProviderClass = CustomDataProvider.class)
	public void testInvalidEmail(String email, String password) {
		
				res = given()
			       .param("email", email)
			       .param("password", password) 
				.when()
						.post("authenticate")
				.then()
						.statusCode(STATUS_401)
						.body("message", equalTo(MESSAGE_401))
						.body("success", equalTo(false)).extract().response();
				
			}
	
	

}
