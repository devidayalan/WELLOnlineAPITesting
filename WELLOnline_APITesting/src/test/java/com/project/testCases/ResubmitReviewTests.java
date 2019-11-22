package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class ResubmitReviewTests extends TestBase {
	
	@Test
	public void resubmitReview() throws IOException {
		
	
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				.formParam("note", GenerateRandomTestDataUtils.getTestString())
				
				.when()
						.post("project-review/{project_id}/{review_id}/resubmit")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
	}

}
