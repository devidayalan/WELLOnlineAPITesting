package com.autentication.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.authentication.utils.RegisterUtils;
import com.common.base.CustomDataProvider;
import com.common.base.TestBase;

import io.restassured.RestAssured;

public class RegisterAPITest extends TestBase {
	
	@BeforeClass
	public void setValues() {
		
		RestAssured.basePath = "register";
	}
	
	
	@Test
		public void testUserRegistration(){
			 res = given()
			 	.param("email", RegisterUtils.getEmail())
		       .param("organization", RegisterUtils.getOrganization()) 
		       .param("first_name", RegisterUtils.getFirstName())
		       .param("last_name", RegisterUtils.getLastName()) 
			.when()
					.post()
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
				.post()
		.then()
				.statusCode(STATUS_422)
				.body("message", equalTo(MESSAGE_422)).extract().response();
		
		}

}
