package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.base.CustomDataProvider;
import com.common.base.TestBase;

import io.restassured.RestAssured;

public class ResetPasswordAPITest extends TestBase {
	
	
	@BeforeClass
	public void setValues() {
		
		RestAssured.basePath = "password/set";
	}

	@Test (dataProvider = "verifyTokenData",dataProviderClass = CustomDataProvider.class)
	public void testForgotPassword(String verifyToken, String password){
		 res =
		given()
	       .param("verify_token", verifyToken)
	       .param("password", password) 
		.when()
				.post()
		.then()
				.statusCode(STATUS_200)
				.body("$", hasKey(TOKEN)).extract().response() ; 
}
	


}
