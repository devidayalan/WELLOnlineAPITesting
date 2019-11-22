package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetAllReviewsByProjectId extends TestBase {
	
	@Test
	public void getAllReviewsByProjectId() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				    
				.when()
						.get("project-review/{project_id}/all")

				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
