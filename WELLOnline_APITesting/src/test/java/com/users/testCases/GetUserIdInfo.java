package com.users.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;


public class GetUserIdInfo extends TestBase {
	

	@Test
	public void getUserInfo() throws IOException{
		userid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Userid", 2));
		res =
		given()
		 	
		 	.pathParam("user_id", userid)
		 	.header("Authorization", header)
	       
		.when()
				.get("user/get/{user_id}")
		.then()
				.statusCode(STATUS_200)
				.log().all()
				.extract().response();
	}
	



}