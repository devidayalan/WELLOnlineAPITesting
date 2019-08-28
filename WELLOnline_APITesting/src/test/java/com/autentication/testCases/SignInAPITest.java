package com.autentication.testCases;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.base.CustomDataProvider;
import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

import io.restassured.RestAssured;




public class SignInAPITest extends TestBase {
	
	
	@BeforeClass
	public void setValues() {
		
		RestAssured.basePath = "authenticate";
	}

	
	@Test (dataProvider = "ValidData",dataProviderClass = CustomDataProvider.class)
	public void testValidEmailPassword(String email, String password) throws IOException{
			res = given()
	       .param("email", email)
	       .param("password", password) 
		.when()
				.post()
		.then()
				.statusCode(STATUS_200)
				.log().all()
				.body("$", hasKey(TOKEN)) 
				.body("any { it.key == 'token' }", is(notNullValue())).        //authorization_token value is not null - has a value
		        extract().response();
		
				header = "TOKEN_ " +(res.path("token")).toString();
				ExcelParserUtils.setCellData(loginUserfile_path, setDataSheet, 0, 1, header);
				
	  
	}
	
	
	@Test (dataProvider = "InvalidData",dataProviderClass = CustomDataProvider.class)
	public void testInvalidEmail(String email, String password) {
		
				res = given()
			       .param("email", email)
			       .param("password", password) 
				.when()
						.post()
				.then()
						.statusCode(STATUS_401)
						.body("message", equalTo(MESSAGE_401))
						.body("success", equalTo(false)).extract().response();
				
			}
	

}
