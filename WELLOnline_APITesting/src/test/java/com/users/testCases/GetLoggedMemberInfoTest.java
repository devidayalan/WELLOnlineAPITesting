package com.users.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;


public class GetLoggedMemberInfoTest extends TestBase{
	
	
	@Test
	public void testUserMemberInfo() throws IOException{
		role = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "role", 2);
		res=
			given()
				.header("Authorization", header)
			       
				.when()
				.get("getLoggedMemberInfo")
				.then()
				.statusCode(STATUS_200)
				.assertThat().body("role[0]", Is.is(role))
				.body("email", equalTo(username))
				.extract().response();
		userid = res.path("id");
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 4, String.valueOf(userid));
		
	}
	

}
