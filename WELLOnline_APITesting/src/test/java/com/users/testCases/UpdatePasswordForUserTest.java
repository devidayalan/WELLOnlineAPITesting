package com.users.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UpdatePasswordForUserTest extends TestBase {
	

	@Test
	public void updatePassword() throws IOException {
		userid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Userid", 2));
		
		if(password==null)
			password = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "password", 2);
		res=
					given()
						.header("Authorization", header)
						.pathParam("user_id", userid)
						.param("old_password", password)
					    .param("password", "initpass") 
						.when()
							.post("user/update/{user_id}/password")
						.then()
							.statusCode(STATUS_200)
							.extract().response();
			}
	

	}
