package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.common.base.CustomDataProvider;
import com.common.base.TestBase;


public class ForgotPassAPITest extends TestBase {
	
	
	@Test(dataProvider = "emailId",dataProviderClass = CustomDataProvider.class)
	public void testForgotPass(String email){
			res =
				given()
				 	.pathParam("email", email)
			       
				.when()
						.get("forgotPass/{email}")
				.then()
						.statusCode(STATUS_200)
						.body("message", equalTo("success")).extract().response();
	}


}
