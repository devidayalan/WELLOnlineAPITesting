package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

@Test
public class DetachProjectMemberTest extends TestBase {
	
	public void detachProjectMember() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		String usrid = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "userid", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("project_id", projectId)
				.pathParam("user_id", usrid)
				
				.when()
						.post("project/member/detach/{project_id}/{user_id}")
				.then()
						
						.log().body()
						.statusCode(204)
						.extract().response();
		
	}

}
