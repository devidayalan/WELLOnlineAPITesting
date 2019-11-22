package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetAllNotesByProjectId extends TestBase {
	
	@Test
	public void getAllNotesByProjectId() throws IOException {
		
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("project_id", projectId)
			       
				.when()
						.get("project-note/{project_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
