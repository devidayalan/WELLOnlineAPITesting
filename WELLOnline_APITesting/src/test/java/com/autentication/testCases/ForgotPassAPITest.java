package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;


public class ForgotPassAPITest extends TestBase {
	
	
  @Test
	public void testForgotPass() throws IOException{
		
		String email = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "Emails", 2);
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
