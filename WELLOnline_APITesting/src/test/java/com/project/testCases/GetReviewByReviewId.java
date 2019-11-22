package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetReviewByReviewId extends TestBase {
	
	@Test
	public void getReviewByReviewId() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				    
				.when()
						.get("project-review/{project_id}/{review_id}")

				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
