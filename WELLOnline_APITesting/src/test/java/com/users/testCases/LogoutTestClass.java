package com.users.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;

public class LogoutTestClass extends TestBase{
	

	
	@Test
	public void logoutTest() throws IOException {
		
		res=
				given()
					.header("Authorization", header)
				       
					.when()
					.post("logout")
					.then()
					.statusCode(STATUS_200)
					.extract().response();
		
		//ExcelParserUtils.setCellData(loginUserfile_path, setDataSheet, 1, 0, "0");
		}

}


