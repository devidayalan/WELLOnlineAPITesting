package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.common.base.CustomDataProvider;
import com.common.base.TestBase;
import com.common.utils.GenerateRandomTestDataUtils;

public class RegisterAPITest extends TestBase {
	
	
	@Test
		public void testUserRegistration(){
			 res = given()
			 	.param("email", GenerateRandomTestDataUtils.getEmail())
		       .param("organization", GenerateRandomTestDataUtils.getOrganization()) 
		       .param("first_name", GenerateRandomTestDataUtils.getFirstName())
		       .param("last_name", GenerateRandomTestDataUtils.getLastName()) 
			.when()
					.post("register")
			.then()
					.statusCode(STATUS_200)
					.body("message", equalTo("success")).extract().response();
			
			
		}
		
		@Test (dataProvider = "UserData",dataProviderClass = CustomDataProvider.class)
		public void testExistingUserRegistration(String email, String orgtn, String fname, String lname) {
			 res = given()
			.param("email", email)
	       .param("organization", orgtn) 
	       .param("first_name", fname)
	       .param("last_name", lname) 
		.when()
				.post("register")
		.then()
				.statusCode(STATUS_422)
				.body("message", equalTo(MESSAGE_422)).extract().response();
		
		}
		
	
}
