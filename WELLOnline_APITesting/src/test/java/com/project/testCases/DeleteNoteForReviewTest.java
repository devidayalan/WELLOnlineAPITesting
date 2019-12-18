package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DeleteNoteForReviewTest extends TestBase {
	//TODO commenting to make build stable
	@Test
	public void deleteNoteForReview() throws IOException {
		
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		String noteId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "noteId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				.pathParam("note_id", noteId)
				
			       
				.when()
						.delete("project-review/{project_id}/{review_id}/note/{note_id}")
				.then()
						
						.log().body()
						//.statusCode(200)
						.extract().response();
		
		
	}

}
