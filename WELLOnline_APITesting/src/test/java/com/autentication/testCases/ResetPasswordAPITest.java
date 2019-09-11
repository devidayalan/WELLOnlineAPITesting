package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import com.common.base.TestBase;

public class ResetPasswordAPITest extends TestBase {
	


	//@Test (dataProvider = "verifyTokenData",dataProviderClass = CustomDataProvider.class)
	public void testForgotPassword(String verifyToken, String password){
		 res =
		given()
	       .param("verify_token", verifyToken)
	       .param("password", password) 
		.when()
				.post("password/set")
		.then()
				.statusCode(STATUS_200)
				.body("$", hasKey(TOKEN)).extract().response() ; 
	}
	

}
